package com.esme.meetme.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.esme.meetme.domain.User;
import com.esme.meetme.domain.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Slf4j
@RequestMapping( "/api/v1" )
@RestController
@Api("Api de site de rencontre")
public class Controller {

    private UserService userService;
    private ObjectMapper objectMapper;

    public Controller(UserService userService, ObjectMapper objectMapper){
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @RequestMapping ( value = "/users" , method = RequestMethod. GET )
    public ResponseEntity<List<User>> getUsers() {
        log.info("Users list provided");
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping ( value = "/users/{id}" , method = RequestMethod. GET )
    public ResponseEntity<User> getUserById( @PathVariable(value = "id") UUID id) {
        try {
            return new ResponseEntity<User>(userService.findUser(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
        }
    }

    @RequestMapping ( value = "/users/{id}" , method = RequestMethod. PUT )
    public ResponseEntity<User> replaceUser(
            @PathVariable(value = "id") UUID id,
            @RequestBody User user) {
        user.setId(id);
        user = userService.replaceUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping ( value = "/users/{id}" , method = RequestMethod. DELETE )
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") UUID id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping ( value = "/users" , method = RequestMethod. POST )
    public ResponseEntity<User> createUser(
            @ApiParam(value = "User object stored in database table", required = true)
            @RequestBody User user) throws NotFoundException {
        user = userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH, consumes = "application/json-patch+json")
    public ResponseEntity<String> patchUse(
            @PathVariable(value = "id") UUID id,
            @RequestBody JsonPatch patch)  {
        try {
            userService.patchUser(applyPatchToCustomer(patch, userService.findUser(id)));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (JsonPatchException | JsonProcessingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private User applyPatchToCustomer(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }
}

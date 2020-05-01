package com.esme.meetme.application;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping( "/api/v1" )
@RestController
public class MeetMeController {

    private Data<Users> users = new Data<Users>();

    public MeetMeController () throws Exception {
        // Test user
        Users user = new Users();
        user.id = UUID.randomUUID();
        user.created = new Date();
        user.position.longitude = 12.9;
        user.position.latitude = 52.3;
        user.email = "emma@gmail.com";
        user.gender = 1;
        user.attraction = 2;
        user.coverSize = 10;
        user.name = "Emma";
        user.city = "Paris";
        user.birthday = new Date();
        user.description = "Salut c'est emma la bg, match pour plus d'infos";
        user.pictures.add("ezrfuheir.png");
        user.pictures.add("ehfhrjfli.png");

        this.users.add(user);
    }

    @RequestMapping ( value = "/users" , method = RequestMethod. GET )
    public ResponseEntity<List<Users>> getUsers () {
        return new ResponseEntity<List<Users>>(this.users.getAll(), HttpStatus. OK ) ;
    }

    @RequestMapping ( value = "/user/{id}" , method = RequestMethod. GET )
    public ResponseEntity<JSONObject> getUser (@PathVariable String id) {
        JSONObject message = new JSONObject();
        try{
            message.put("status", "ok");
            message.put("content", this.users.find("id", id));
        }catch(Exception e){
            message.put("status", "error");
            message.put("content", e.getMessage());
        }
        return new ResponseEntity<JSONObject>(message, HttpStatus. OK ) ;
    }

    @RequestMapping ( value = "/user/{id}" , method = RequestMethod. PUT )
    public ResponseEntity<JSONObject> putUser (@PathVariable String id, @RequestBody Users user) {
        JSONObject message = new JSONObject();
        try{
            this.users.replace("id", id, user);
            message.put("status", "ok");
            message.put("content", "User updated");
        }catch(Exception e){
            message.put("status", "error");
            message.put("content", e.getMessage());
        }
        return new ResponseEntity<JSONObject>(message, HttpStatus. OK ) ;
    }

    @RequestMapping ( value = "/user/{id}" , method = RequestMethod. PATCH )
    public ResponseEntity<JSONObject> patchUser (@PathVariable String id, @RequestBody Operation<Object> op) {
        JSONObject message = new JSONObject();
        try{
            this.users.patch("id", id, op);
            message.put("status", "ok");
            message.put("content", "Path updated");
        }catch(Exception e){
            message.put("status", "error");
            message.put("content", e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<JSONObject>(message, HttpStatus. OK ) ;
    }

    @RequestMapping ( value = "/user/{id}" , method = RequestMethod. DELETE )
    public ResponseEntity<JSONObject> deleteUser (@PathVariable String id) {
        JSONObject message = new JSONObject();
        try{
            this.users.delete("id", id);
            message.put("status", "ok");
            message.put("content", "User removed");
        }catch(Exception e){
            message.put("status", "error");
            message.put("content", e.getMessage());
        }
        return new ResponseEntity<JSONObject>(message, HttpStatus. OK ) ;
    }

    @RequestMapping ( value = "/users" , method = RequestMethod. POST )
    public ResponseEntity<JSONObject> createUsers ( @RequestBody Users newUser ){
        JSONObject message = new JSONObject();
        try{
            this.users.add(newUser);
            message.put("status", "ok");
            message.put("content", "User added");
        }catch(Exception e){
            message.put("status", "error");
            message.put("content", e.getMessage());
        }
        return new ResponseEntity<JSONObject>(message, HttpStatus. OK ) ;
    }
}

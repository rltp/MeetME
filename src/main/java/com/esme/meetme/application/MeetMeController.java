package com.esme.meetme.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping( "/api/v1" )
@RestController
public class MeetMeController {

    private List<Users> users = Arrays.asList() ;

    public MeetMeController () {

    }



    @RequestMapping ( value = "/users" , method = RequestMethod. GET )
    public ResponseEntity<List<Users>> getUsers () {
        return new ResponseEntity<List<Users>>(this.users, HttpStatus. OK ) ;
    }

    @RequestMapping ( value = "/users" , method = RequestMethod. POST )
    public ResponseEntity<String> postUsers (
            @ApiParam(value="User Name", required=true)
            @RequestBody Users newUser
    ) {
        try{
            this.users.add(newUser);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus. OK ) ;
        }
        return new ResponseEntity<String>("User added", HttpStatus. OK ) ;
    }
}

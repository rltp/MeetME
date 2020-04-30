package com.esme.meetme.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping( "/api/v1" )
@RestController
public class MeetMeController {

    private ArrayList<Users> users = new ArrayList<Users>();;

    public MeetMeController () {
        // Test user
        Users user = new Users();
        user.id = UUID.randomUUID();
        user.created = new Date();
        user.position = new Location();
        user.position.longitude = 12.9;
        user.position.latitude = 52.3;
        user.gender = 1;
        user.attraction = 2;
        user.coverSize = 10;
        user.name = "Emma";
        user.city = "Paris";
        user.birthday = new Date();
        user.description = "Salut c'est emma la bg, match pour plus d'infos";
        user.pictures = new ArrayList<String>();
        user.pictures.add("ezrfuheir.png");
        user.pictures.add("ehfhrjfli.png");

        this.users.add(user);
    }

    @RequestMapping ( value = "/users" , method = RequestMethod. GET )
    public ResponseEntity<List<Users>> getUsers () {
        return new ResponseEntity<List<Users>>(this.users, HttpStatus. OK ) ;
    }

    @RequestMapping ( value = "/users" , method = RequestMethod. POST )
    public ResponseEntity<String> createUsers ( @RequestParam(value = "newUser") Users newUser ) {
        try{
            this.users.add(newUser);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus. OK ) ;
        }
        return new ResponseEntity<String>("User added", HttpStatus. OK ) ;
    }
}

package com.esme.meetme.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping( "/api/v1" )
@RestController
public class MeetMeController {
    @RequestMapping ( value = "/users" , method = RequestMethod. GET )
    public ResponseEntity<List<String>> getUsers () {
        List<String> strings = Arrays. asList ( "Rodolphe" , "Julien" , "Raphael" ) ;
        return new ResponseEntity<List<String>>(strings , HttpStatus. OK ) ;
    }
}

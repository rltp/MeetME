package com.esme.meetme.application;

import org.springframework.core.serializer.Serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

class location{
    public Double longitude;
    public Double latitude;
}

public class Users implements Serializer {
    public UUID id;
    public Date created;
    public location position;

    public String name;
    public String city;
    public Date birthday;
    public String description;
    public ArrayList<String> pictures;
}

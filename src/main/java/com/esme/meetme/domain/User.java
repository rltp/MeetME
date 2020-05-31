package com.esme.meetme.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User implements Serializable{
    public UUID id;

    public String name;
    public String city;
    public Date birthday;
    public String description;

    public Settings settings;

    public List<Picture> pictures;
    public List<Match> matchs;
}


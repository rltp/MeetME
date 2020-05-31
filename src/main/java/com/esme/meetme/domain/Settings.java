package com.esme.meetme.domain;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Settings {

    public UUID id;

    public Date created;
    public String email;
    public int gender;

    public int attraction;
    public int coverSize;

    public double longitude;
    public double latitude;

    public int ageMin;
    public int ageMax;

}

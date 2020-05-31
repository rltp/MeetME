package com.esme.meetme.domain;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    public UUID id;
    public Date date;
    public String path;
    public Boolean face;
}
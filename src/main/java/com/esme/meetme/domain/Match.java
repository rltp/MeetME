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

public class Match {
    public UUID id;
    public Date date;
    public UUID user;
    public Boolean result;
}
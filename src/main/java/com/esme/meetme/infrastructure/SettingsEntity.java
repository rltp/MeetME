package com.esme.meetme.infrastructure;

import com.esme.meetme.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="SETTINGS_ENTITY")
public class SettingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "CREATED", length = 50, nullable = false)
    private Date created;
    @Column(name = "EMAIl", length = 50, nullable = false)
    private String email;

    @Column(name = "GENDER", length = 50, nullable = false)
    private int gender;
    @Column(name = "ATTRACTION", length = 50, nullable = false)
    private int attraction;
    @Column(name = "COVERSIZE", length = 50, nullable = false)
    private int coverSize;

    @Column(name = "LONGITUDE", length = 50, nullable = false)
    private double longitude;
    @Column(name = "LATITUDE", length = 50, nullable = false)
    private double latitude;

    @Column(name = "AGEMIN", length = 50, nullable = false)
    private int ageMin;
    @Column(name = "AGEMAX", length = 50, nullable = false)
    private int ageMax;


    @OneToOne
    private UserEntity userEntity;
}

package com.esme.meetme.infrastructure;

import com.esme.meetme.domain.Settings;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="USER_ENTITY")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(name = "NAME", length = 50, nullable = false)
    private String name;
    @Column(name = "CITY", length = 50, nullable = false)
    private String city;
    @Column(name = "BIRTHDAY", length = 50, nullable = false)
    private Date birthday;
    @Column(name = "DESC", length = 50, nullable = false)
    private String description;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private SettingsEntity settingsEntity;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<PictureEntity> picturesEntities;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<MatchEntity> matchEntities;
}

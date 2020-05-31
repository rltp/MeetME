package com.esme.meetme.infrastructure;

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
@Table(name ="PICTURE_ENTITY")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "DATE", length = 50, nullable = false)
    private Date date;
    @Column(name = "PATH", nullable = false)
    private String path;
    @Column(name = "FACE", nullable = false)
    private Boolean face;

    @ManyToOne
    private UserEntity userEntity;
}

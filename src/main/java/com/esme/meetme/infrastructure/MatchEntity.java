package com.esme.meetme.infrastructure;

import javax.persistence.*;

import lombok.*;

import java.util.UUID;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="MATCH_ENTITY")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "DATE", length = 50, nullable = false)
    private Date date;
    @Column(name = "USER", length = 50, nullable = false)
    private UUID user;
    @Column(name = "RESULT", length = 50, nullable = false)
    private Boolean result;

    @ManyToOne
    private UserEntity userEntity;
}

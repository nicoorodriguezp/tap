package com.tap.licencias.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Licence {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user")
    private User user;
    @ManyToOne
    @JoinColumn(nullable = false, name = "state")
    private LicenceState state;
    @Column(nullable = false)
    private boolean lastOne;
    @Column(name = "lastUpdate", nullable = false)
    private Date lastUpdate;



}

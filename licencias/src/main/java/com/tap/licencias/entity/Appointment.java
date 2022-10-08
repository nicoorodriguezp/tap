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
public class Appointment {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user")
    private User user;
    @ManyToOne
    @JoinColumn(nullable = false, name = "place")
    private Place place;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String hour;

}

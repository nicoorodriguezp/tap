package com.tap.licencias.entity;

import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "position")
    private Position position;
    @ManyToOne
    @JoinColumn(nullable = true, name = "workplace")
    private Place workplace;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private boolean inProcess;
    @Column(nullable = false)
    private String password;

    @Override
    public String toString(){
        String full_name = this.name + " " + this.lastName;
        return full_name;
    }

}

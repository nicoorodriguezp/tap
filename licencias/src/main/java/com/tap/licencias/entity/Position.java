package com.tap.licencias.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Position {
    @Id
    @javax.persistence.Id
    private Integer id;
    @Column(nullable = false)
    private String name;

    @Override
    public String toString(){
        return this.name;
    }
}

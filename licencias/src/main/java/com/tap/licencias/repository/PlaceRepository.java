package com.tap.licencias.repository;

import com.tap.licencias.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Integer> {

    @Query(value = "SELECT p FROM Place p")
    ArrayList<Place> getPlaces();

    @Query(value = "SELECT p FROM Place p WHERE p.address = ?1")
    Place findByAddress(String address);
}

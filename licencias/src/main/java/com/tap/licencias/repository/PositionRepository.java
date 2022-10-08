package com.tap.licencias.repository;

import com.tap.licencias.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    @Query(value = "SELECT p FROM Position p")
    ArrayList<Position> getPositions();
}

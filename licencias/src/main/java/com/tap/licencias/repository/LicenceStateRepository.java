package com.tap.licencias.repository;

import com.tap.licencias.entity.LicenceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface LicenceStateRepository extends JpaRepository<LicenceState, Integer> {
    @Query(value = "SELECT s FROM LicenceState s")
    ArrayList<LicenceState> getStates();
}

package com.tap.licencias.repository;

import com.tap.licencias.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
// https://www.baeldung.com/spring-data-jpa-query
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query(value = "SELECT COUNT(a) FROM Appointment a WHERE a.place.id = ?1 and a.date = ?2 and a.hour = ?3")
    int getAppointmentCountByPlaceDateHour(Integer idPlace, Date date, String hour);

    @Query(value = "SELECT a FROM Appointment a WHERE a.user.id = ?1")
    ArrayList<Appointment> getAppointmentsByUser(Integer idUser);
}

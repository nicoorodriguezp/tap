package com.tap.licencias.service;

import com.tap.licencias.dao.regular_user.UserDAO;
import com.tap.licencias.entity.*;
import com.tap.licencias.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserServiceTest implements UserDAO {

    @Autowired protected PlaceRepository placeRepository;
    @Autowired protected UserRepository userRepository;
    @Autowired protected  AppointmentRepository appointmentRepository;
    @Autowired protected  PositionRepository positionRepository;
    @Autowired protected  LicenceRepository licenceRepository;
    @Autowired protected  LicenceStateRepository licenceStateRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(Integer idUser){try {return userRepository.findById(idUser).get();} catch (Exception e){System.out.println(e);return null;}}

    @Override
    public boolean checkPlaceAvailabilityDateHour(Date date, String hour, Integer idPlace) {

        int appointments = appointmentRepository.getAppointmentCountByPlaceDateHour(idPlace, date, hour);

        if(appointments >= 10){
            System.out.println("Ya no hay espacio disponible.");
            return false;
        }

        return true;
    }

    @Override
    public void createAppointment(Appointment appointment) {
       appointmentRepository.save(appointment);
    }

    @Override
    public ArrayList<Licence> getLicences(Integer idUser) { try{ return licenceRepository.getByUserID(idUser);} catch (Exception e){System.out.println(e);return null;}}

    @Override
    public ArrayList<LicenceState> getLicenceStates(){try {return licenceStateRepository.getStates();} catch (Exception e){System.out.println(e);return null;}}

    @Override
    public ArrayList<Position> getPositions(){try {return (ArrayList<Position>)positionRepository.findAll();} catch (Exception e){System.out.println(e); return null;}}

    @Override
    public ArrayList<Place> getPlaces() {try {return (ArrayList<Place>)placeRepository.findAll();} catch (Exception e){System.out.println(e); return null;}}

    @Override
    public Licence getLastLicence(Integer idUser) {try{return licenceRepository.getLastLicence(idUser);} catch(Exception e){System.out.println(e);return null;}}

    @Override
    public Licence getLicenceInProcess(Integer idUser, Integer InitialState, Integer AwaitingPrintingState, Integer ReadyState, Integer ToBeWithdrawn) {
        try{
            return licenceRepository.getLicenceInProcess(idUser, InitialState, AwaitingPrintingState, ReadyState, ToBeWithdrawn);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Appointment> getAppointments(Integer idUser) {
        try{
            return appointmentRepository.getAppointmentsByUser(idUser);
        }catch (Exception e){
            return null;
        }
    }

    public Position getPositionByID(int idPosition) {
        return positionRepository.findById(idPosition).get();
    }

}

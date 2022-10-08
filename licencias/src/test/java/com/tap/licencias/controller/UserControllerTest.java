package com.tap.licencias.controller;

import com.tap.licencias.dao.regular_user.UserDAO;
import com.tap.licencias.entity.*;
import com.tap.licencias.exception.DAOException;
import com.tap.licencias.exception.LoginUserException;
import com.tap.licencias.service.UserServiceTest;
import com.tap.licencias.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class UserControllerTest implements UserDAO {

    @Qualifier("userServiceTest")
    @Autowired
    UserServiceTest userService;

    @Override
    public void createUser(User user) {

        userService.createUser(user);
    }

    @Override
    public User getUser(Integer idUser) throws DAOException, LoginUserException {
        return userService.getUser(idUser);
    }

    @Override
    public boolean checkPlaceAvailabilityDateHour(Date date, String hour, Integer idPlace) {
        return userService.checkPlaceAvailabilityDateHour(date, hour, idPlace);
    }

    @Override
    public void createAppointment(Appointment appointment) {
        userService.createAppointment(appointment);
    }

    @Override
    public ArrayList<Licence> getLicences(Integer idUser) {
        return userService.getLicences(idUser);
    }

    @Override
    public ArrayList<Place> getPlaces() {return userService.getPlaces();}

    @Override
    public ArrayList<Position> getPositions() {return userService.getPositions();}

    @Override
    public ArrayList<LicenceState> getLicenceStates() {return userService.getLicenceStates(); }

    @Override
    public Licence getLastLicence(Integer idUser) { return userService.getLastLicence(idUser);}

    @Override
    public Licence getLicenceInProcess(Integer idUser, Integer InitialState, Integer AwaitingPrintingState, Integer ReadyState, Integer ToBeWithdrawn) {
        return userService.getLicenceInProcess(idUser, InitialState, AwaitingPrintingState, ReadyState, ToBeWithdrawn);
    }

    @Override
    public ArrayList<Appointment> getAppointments(Integer idUser) {
        return userService.getAppointments(idUser);
    }

    public Position getPositionByID(int idPosition) {
        return userService.getPositionByID(idPosition);
    }
}

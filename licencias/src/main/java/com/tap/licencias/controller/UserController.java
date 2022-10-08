package com.tap.licencias.controller;

import com.tap.licencias.dao.regular_user.UserDAO;
import com.tap.licencias.exception.DAOException;
import com.tap.licencias.exception.LoginUserException;
import com.tap.licencias.entity.Appointment;
import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import com.tap.licencias.entity.User;
import com.tap.licencias.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class UserController implements UserDAO {

    @Autowired UserService userService;

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
}

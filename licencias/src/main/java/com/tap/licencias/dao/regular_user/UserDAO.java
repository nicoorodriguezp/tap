package com.tap.licencias.dao.regular_user;

import com.tap.licencias.exception.DAOException;
import com.tap.licencias.exception.LoginUserException;
import com.tap.licencias.entity.Appointment;
import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import com.tap.licencias.entity.User;

import java.util.ArrayList;
import java.util.Date;

public interface UserDAO {

    public void createUser(User user);

    public User getUser(Integer idUser) throws DAOException, LoginUserException;

    public boolean checkPlaceAvailabilityDateHour(Date date, String hour, Integer idPlace);

    public void createAppointment(Appointment appointment);

    public ArrayList<Appointment> getAppointments(Integer idUser);

    public ArrayList<Licence> getLicences(Integer idUser);

    public ArrayList<Place> getPlaces();

    public ArrayList<Position> getPositions();

    public ArrayList<LicenceState> getLicenceStates();

    public Licence getLastLicence(Integer idUser);

    public Licence getLicenceInProcess(Integer idUser, Integer InitialState, Integer AwaitingPrintingState, Integer ReadyState, Integer ToBeWithdrawn);

}

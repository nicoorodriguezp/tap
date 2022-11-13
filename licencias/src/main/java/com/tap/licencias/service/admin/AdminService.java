package com.tap.licencias.service.admin;

import com.tap.licencias.dao.admin.AdminDAO;
import com.tap.licencias.entity.*;
import com.tap.licencias.repository.PlaceRepository;
import com.tap.licencias.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;


@Service
public class AdminService extends EmployeeService implements AdminDAO {

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>> Workplaces <<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    public void createPlace(Place place) {
        placeRepository.save(place);
    }

    @Override
    public void updatePlace(Place place) {
        placeRepository.save(place);
    }

    @Override
    public void deletePlace(Place place) {
        placeRepository.delete(place);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>> Positions <<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    public void createPosition(Position position) { positionRepository.save(position);}

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>> Licence State <<<<<<<<<<<<<<<<<<<<<<<
    @Override
    public void createLicenceState(LicenceState licenceState) {licenceStateRepository.save(licenceState); }

    @Override
    public ArrayList<Licence> getLicencesStateDatesBetween(Date startDate, Date endDate, LicenceState licenceState) {
        return licenceRepository.getLicencesStateDatesBetween(startDate, endDate, licenceState);
    }

    @Override
    public ArrayList<Appointment> getAllAppointments() {
        return (ArrayList<Appointment>) appointmentRepository.findAll();
    }

    @Override
    public ArrayList<Licence> getAllLicences() {
        return (ArrayList<Licence>) licenceRepository.findAll();
    }


}

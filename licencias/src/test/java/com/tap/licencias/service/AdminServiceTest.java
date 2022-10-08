package com.tap.licencias.service;

import com.tap.licencias.dao.admin.AdminDAO;
import com.tap.licencias.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class AdminServiceTest extends EmployeeServiceTest implements AdminDAO {

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

    public Place getPlaceByAdress(String address) {
        return placeRepository.findByAddress(address);
    }

    public void deleteTestRecords() {

        appointmentRepository.delete(appointmentRepository.getAppointmentsByUser(99999999).get(0));
        System.out.println("Appointment Test deleted.");
        licenceRepository.deleteById(getLastLicence(99999999).getId());
        System.out.println("Licence Test deleted.");
        userRepository.deleteById(99999999);
        System.out.println("User Test deleted.");
        placeRepository.deleteById(getPlaceByAdress("TEST PLACE").getId());
        System.out.println("Place Test deleted.");
        positionRepository.deleteById(99);
        System.out.println("Position Test deleted.");
        licenceStateRepository.deleteById(99);
        System.out.println("State Test deleted.");

        System.out.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>> Successful Deleting Test Records - COMPLETE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n\n");

    }
}

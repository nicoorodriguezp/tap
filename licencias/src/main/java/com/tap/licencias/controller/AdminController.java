package com.tap.licencias.controller;

import com.tap.licencias.dao.admin.AdminDAO;
import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import com.tap.licencias.entity.User;
import com.tap.licencias.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class AdminController implements AdminDAO {

    @Autowired
    private AdminService adminService;

    @Override
    public void updateUser(User user) { adminService.updateUser(user);}

    @Override
    public ArrayList<User> getUsers() {
        return adminService.getUsers();
    }

    @Override
    public void createPlace(Place place) {
        adminService.createPlace(place);
    }

    @Override
    public void updatePlace(Place place) { adminService.updatePlace(place);}

    @Override
    public void deletePlace(Place place) {
        adminService.deletePlace(place);
    }

    @Override
    public void createPosition(Position position) {adminService.createPosition(position);}

    @Override
    public void createLicenceState(LicenceState licenceState) {adminService.createLicenceState(licenceState);}


}

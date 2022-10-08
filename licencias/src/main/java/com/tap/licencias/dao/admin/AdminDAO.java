package com.tap.licencias.dao.admin;

import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import com.tap.licencias.entity.User;

import java.util.ArrayList;

public interface AdminDAO {

    public void updateUser(User user);
    public ArrayList<User> getUsers();


    public void createPlace(Place item);
    public void updatePlace(Place item);
    public void deletePlace(Place item);

    public void createPosition(Position position);

    public void createLicenceState(LicenceState licenceState);

}

package com.tap.licencias.dao.admin;

import com.tap.licencias.entity.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public interface AdminDAO {

    public void updateUser(User user);
    public ArrayList<User> getUsers();


    public void createPlace(Place item);
    public void updatePlace(Place item);
    public void deletePlace(Place item);

    public void createPosition(Position position);

    public void createLicenceState(LicenceState licenceState);

    public ArrayList<Licence> getLicencesStateDatesBetween(Date startDate, Date endDate, LicenceState licenceState);
}

package com.tap.licencias;


import com.tap.licencias.controller.AdminControllerTest;
import com.tap.licencias.controller.UserControllerTest;
import com.tap.licencias.entity.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

public class TestUtils {


    public static User createUser(AdminControllerTest ac){

        User user = new User();
        user.setId(99999999);
        user.setName("TEST");
        user.setLastName("TEST");
        user.setPassword("TEST");
        user.setInProcess(false);
        user.setPosition(createPosition());
        user.setWorkplace(createPlace(ac));

        return user;

    }

    public static Place createPlace(AdminControllerTest ac) {
        Place place = new Place();
        try{place.setId(ac.getPlaceByAddress("TEST PLACE").getId());}catch(Exception e){}
        place.setAddress("TEST PLACE");

        return place;
    }
    public static Position createPosition(){
        Position p = new Position();
        p.setId(99);
        p.setName("TEST POSITION");

        return p;
    }

    public static Licence createLicence(User u, UserControllerTest uc){
        Licence lc = new Licence();
        try{ lc.setId(uc.getLastLicence(u.getId()).getId()); }catch (Exception e){} //en caso de que quiera mostrar y que no borre los registros de test.
        lc.setUser(u);
        lc.setLastOne(true);
        lc.setState(createState());
        lc.setLastUpdate(Date.valueOf(LocalDate.now()));

        return lc;
    }

    public static LicenceState createState(){
        LicenceState ls = new LicenceState();
        ls.setId(99);
        ls.setName("TEST STATE");

        return ls;
    }

}

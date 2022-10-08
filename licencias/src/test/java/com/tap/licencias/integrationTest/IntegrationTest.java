package com.tap.licencias.integrationTest;

import com.tap.licencias.TestUtils;

import com.tap.licencias.controller.AdminControllerTest;
import com.tap.licencias.controller.EmployeeControllerTest;
import com.tap.licencias.controller.UserControllerTest;
import com.tap.licencias.entity.Appointment;
import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.User;
import com.tap.licencias.exception.DAOException;
import com.tap.licencias.exception.LoginUserException;
import fxml.AlertController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
@RunWith(SpringRunner.class)
@DataJpaTest
public class IntegrationTest {

    AdminControllerTest ac;
    EmployeeControllerTest ec;
    UserControllerTest uc;

    public IntegrationTest(AdminControllerTest ac, EmployeeControllerTest ec, UserControllerTest uc){

        this.ac = ac;
        this.ec = ec;
        this.uc = uc;

    }

    @DisplayName("Appointment successfully set")
    @Test
    public void createAppointmentTest(){
        try {

         System.out.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>> INICIANDO TEST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n\n");

         User userTest = TestUtils.createUser(ac);

         ac.createPlace(userTest.getWorkplace());   // address = "TEST PLACE"
            System.out.println("Successful testing: create/update place");

         ac.createPosition(userTest.getPosition()); // idPosition = 99
            System.out.println("Successful testing: create/update position");

         userTest.setWorkplace(ac.getPlaceByAddress("TEST PLACE"));
            System.out.println("Successful testing: get place");

         userTest.setPosition(uc.getPositionByID(99));
            System.out.println("Successful testing: get position");

         uc.createUser(userTest); // idUser     = 99999999
            System.out.println("Successful testing: create/update user");

         Licence licenceTest = TestUtils.createLicence(userTest, uc);// idLicence      = 99
         ac.createLicenceState(licenceTest.getState());          // idLicenceState = 99
            System.out.println("Successful testing: create/update licence state");

         ec.updateLicenceState(licenceTest); // Update exists licence or create a new one with the state set
            System.out.println("Successful testing: create licence / update licence");

        Appointment app = new Appointment();
        app.setDate(Date.valueOf(LocalDate.now()));
        app.setHour("99:99");
        app.setPlace(userTest.getWorkplace());
        app.setUser(uc.getUser(99999999));
            System.out.println("Successful testing: get user");
        uc.createAppointment(app);
        System.out.println("Successful testing: create appointment");

        Assert.assertNotEquals(uc.getAppointments(userTest.getId()), null);
            System.out.println("Successful testing: get appointments");

        System.out.println("Successful testing - COMPLETE");
        System.out.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>> Successful testing - COMPLETE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n\n");

        ac.deleteTestRecords();

        } catch (DAOException e) {
            System.out.println("Hubo un error DAO: " + e);
        } catch (LoginUserException e) {
            System.out.println("Hubo un error login: " + e);
        } catch (Exception e){
            System.out.println("Hubo un error no identificado: " + e);
            throw e;
        }

    }


}

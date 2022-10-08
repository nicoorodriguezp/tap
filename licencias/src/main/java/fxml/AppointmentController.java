/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.tap.licencias.entity.Appointment;
import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.Place;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
@Component
public class AppointmentController extends Controller implements Initializable {

    @FXML
    private Label userNameLabel;
    @FXML
    private DatePicker datePicker = new DatePicker(LocalDate.now());
    @FXML
    private ComboBox<Place> workplaceCB;
    @FXML
    private ComboBox<String> timeCB;
    @FXML
    private Label appointmentTitle;

    private ArrayList<Place> workplaces = new ArrayList<Place>();


    public void init(MainController m, ArrayList<Place> workplaces){
        this.m = m;

        userNameLabel.setText(m.user.toString());

        setWorkplaces(workplaces);
        setHours();

        if(m.user.isInProcess()){
            appointmentTitle.setText("Solicitar turno para retirar la licencia.");
        }

    }


    @FXML
    private void saveAppointment(ActionEvent event) {

        if(datePicker.getValue() != null && workplaceCB.getValue() != null && timeCB.getValue() != null){


            if(m.user.isInProcess()){
                Licence l = m.uc.getLicenceInProcess(m.user.getId(),InitialState, AwaitingPrintingState, ReadySate, ToBeWithdrawnState);

                if(l.getState().getId() == ReadySate && checkPlaceAvailabilityDateHour()){
                    createAppointment();
                    ArrayList<LicenceState> states = m.uc.getLicenceStates();
                    l.setState(states.get(ToBeWithdrawnState-1));
                    m.ec.updateLicenceState(l);
                }else{
                    if(l.getState().getId() != ToBeWithdrawnState){
                        m.showAlert("La licencia aun no esta lista para ser retirada. El turno no se registró.", 1);
                    }else{
                        m.showAlert("Usted ya solicitó un turno para ir a retirar la licencia.", 1);
                    }

                }

            }else{

                createAppointment();
            }



        }else{
            m.showAlert("Debes elegir todos los campos", 2);
        }


    }

    private void createAppointment(){
        Appointment a = new Appointment();
        a.setDate(Date.valueOf(datePicker.getValue()));
        a.setHour(timeCB.valueProperty().getValue());
        a.setPlace(workplaceCB.valueProperty().getValue());
        a.setUser(m.user);
        m.uc.createAppointment(a);

        updateLicences();


        m.showAlert("Su turno ha sido registrado.", 4);
    }

    private void updateLicences() {

        Licence lastOne = m.uc.getLastLicence(m.user.getId());

        if(!m.user.isInProcess()){

            ArrayList<LicenceState> states = m.uc.getLicenceStates();
            Licence newLicence = new Licence();
            newLicence.setLastOne(true);
            newLicence.setState(states.get(InitialState-1));
            newLicence.setUser(m.user);
            newLicence.setLastUpdate(Date.valueOf(LocalDate.now()));

            m.ec.updateLicenceState(newLicence);
            if(lastOne != null){
                lastOne.setLastOne(false);
                m.ec.updateLicenceState(lastOne);
            }

            m.user.setInProcess(true);
            m.ac.updateUser(m.user);

        }

    }

    private boolean checkPlaceAvailabilityDateHour(){
        return m.uc.checkPlaceAvailabilityDateHour(Date.valueOf(datePicker.getValue()),timeCB.valueProperty().getValue(), workplaceCB.valueProperty().getValue().getId());
    }






    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();
    }


    private void setWorkplaces(ArrayList<Place> workplaces) {

        // empieza en 1 para sacar el primero que es el que usan los usuarios regulares.
        for(int w = 1; w < workplaces.size(); w++){
            this.workplaces.add(workplaces.get(w));
        }

        workplaceCB.setItems(FXCollections.observableArrayList(this.workplaces));


    }

    private void setHours() {
        ArrayList<String> hours = new ArrayList<>();
        int max_hour = 18;

        for (int h = 9; h<=18; h++){
            hours.add( h + ":00");
            hours.add( h + ":30");
        }

        timeCB.setItems(FXCollections.observableArrayList(hours));

    }




}

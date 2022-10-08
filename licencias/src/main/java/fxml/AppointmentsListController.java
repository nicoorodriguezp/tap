/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.tap.licencias.entity.Appointment;
import com.tap.licencias.entity.Licence;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
public class AppointmentsListController extends Controller implements Initializable {

    @FXML
    private TableView<Appointment> appointmentsTable;
    @FXML
    private Label userNameLabel;
    @FXML
    private TableColumn<Appointment, Integer> idAppointmentColumn;
    @FXML
    private TableColumn<Appointment, Date> dateColumn;
    @FXML
    private TableColumn<Appointment, String> timeColumn;
    @FXML
    private TableColumn<Appointment, String> addressColumn;

    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ObservableList<Appointment> appointmentsInTable = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idAppointmentColumn.setCellValueFactory(appointment -> new SimpleObjectProperty(appointment.getValue().getId()));
        dateColumn.setCellValueFactory(appointment -> new SimpleObjectProperty(appointment.getValue().getDate()));
        timeColumn.setCellValueFactory(appointment -> new SimpleObjectProperty(appointment.getValue().getHour()));
        addressColumn.setCellValueFactory(appointment -> new SimpleObjectProperty(appointment.getValue().getPlace().getAddress()));
    }    

    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void showAppointmentPanel(ActionEvent event) {
        this.m.showAppointmentPanel();
    }

    @FXML
    private void refreshData(MouseEvent event) {
        getAppointments();
        RefreshAppointmentsTable();
        this.m.showAlert("Se sincronizaron los datos con la Base de Datos correctamente.", 4);
        System.out.println("Se sincronizaron los datos con la base de datos.");
    }

    public void init(MainController mainController) {
        this.m = mainController;
        this.userNameLabel.setText(m.user.toString());

        getAppointments();
        RefreshAppointmentsTable();
    }

    private void RefreshAppointmentsTable() {

        appointmentsInTable.clear();
        appointmentsInTable.addAll(appointments);
        appointmentsTable.setItems(appointmentsInTable);

    }

    private void getAppointments() {

        this.appointments.clear();
        appointments.addAll(m.uc.getAppointments(m.user.getId()));

        if(appointments.isEmpty()){
            this.m.showAlert("No se encontraron turnos para mostrar.", 1);
        }

    }


}

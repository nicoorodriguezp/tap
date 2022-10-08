/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
@Component
public class LicencePanelController extends Controller implements Initializable {

    @FXML
    private Button saveButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private ComboBox<LicenceState> stateCB;
    @FXML
    private Label labelLicenceUpdated;
    @FXML
    private Label labelLicencenotUpdated;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    private Licence selectedLicence;
    private LicenceListController licenceListController;

    public void init(MainController mainController, Licence selectedLicence, LicenceListController licenceListController, ArrayList<LicenceState> licenceStates) {

        this.m = mainController;
        this.selectedLicence = selectedLicence;
        this.licenceListController = licenceListController;
        userNameLabel.setText(m.user.toString());

        stateCB.setItems(FXCollections.observableArrayList(licenceStates));
        stateCB.valueProperty().setValue(selectedLicence.getState());
        

    }

    @FXML
    private void saveLicence(ActionEvent event) {

        selectedLicence.setState(stateCB.valueProperty().getValue());
        selectedLicence.setLastUpdate(Date.valueOf(LocalDate.now()));

        if(selectedLicence.getState().getId().equals(DeliveredState) || selectedLicence.getState().getId().equals(DeniedState)){
            User driver = selectedLicence.getUser();
            driver.setInProcess(false);
            m.ac.updateUser(driver);
            String msg = "Se dio por terminado el tramite como " +selectedLicence.getState().toString()+ " para el conductor: " + selectedLicence.getUser().toString();
            m.showAlert(msg, 4);

            //solo para test con el mismo usuario
            if(selectedLicence.getUser().equals(m.user)){
                this.m.user = driver;
            }
        }

        m.ec.updateLicenceState(selectedLicence);
        m.showAlert("El estado de la licencia se ha actualizado correctamente.", 4);

        labelLicencenotUpdated.setVisible(false);
        labelLicenceUpdated.setVisible(true);
        licenceListController.refreshLicenceTable();

    }

    @FXML
    private void showLicences(ActionEvent event) {
        Button button = (Button) event.getSource();
        Scene scene = button.getScene();
        m.showLicencesInProcess(scene);
    }
}

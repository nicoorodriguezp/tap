/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.tap.licencias.entity.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
@Component
public class LocationPanelController extends Controller implements Initializable {

    @FXML
    private Button createButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private TextField addressTF;


    private Place selectedPlace;
    @FXML
    private Label labelPlaceUpdated;
    @FXML
    private Label labelPlacenotUpdated;


    public void init(MainController mainController, Place selectedPlace){

        this.m = mainController;
        this.selectedPlace = selectedPlace;


        userNameLabel.setText(this.m.user.toString());

        if(selectedPlace != null){
            addressTF.setText(selectedPlace.getAddress());
            saveButton.setVisible(true);
            createButton.setVisible(false);
        }else{
            saveButton.setVisible(false);
            createButton.setVisible(true);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createHeadquarter(ActionEvent event) {
        selectedPlace = new Place();
        savePlace();
        m.showAlert("Se creó la sede correctamente.", 4);
    }

    @FXML
    private void saveHeadquarter(ActionEvent event) {
        savePlace();
        m.showAlert("Se actualizó la sede correctamente.", 4);
    }

    private void savePlace(){
        if(!addressTF.getText().equals("")){
            selectedPlace.setAddress(addressTF.getText());
            m.ac.updatePlace(selectedPlace);

            labelPlacenotUpdated.setVisible(false);
            labelPlaceUpdated.setVisible(true);

        }else{
            this.m.showAlert("Debe indicar una dirección", 2);
        }



    }

    @FXML
    private void showHome(ActionEvent event) {
        this.m.showPlaceList();
    }
    
}

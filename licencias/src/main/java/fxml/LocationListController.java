/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
@Component
public class LocationListController extends Controller implements Initializable {

    @FXML
    private Label userNameLabel;
    @FXML
    private TableView<Place> placeTable;
    @FXML
    private Button newHeadquarterButton;
    @FXML
    private Button changeHeadquarterButton;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchButton;
    @FXML
    private TableColumn<Place, Integer> idPlaceColumn;
    @FXML
    private TableColumn<Place, String> addressColumn;

    private Place selectedPlace;
    private ArrayList<Place> places = new ArrayList<>();
    private ObservableList<Place> placesInTable = FXCollections.observableArrayList();

    public void init(MainController mainController){
        this.m = mainController;
        userNameLabel.setText(m.user.toString());

        getPlaces();
        refreshPlaceTable();

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idPlaceColumn.setCellValueFactory(place -> new SimpleObjectProperty(place.getValue().getId()));
        addressColumn.setCellValueFactory(place -> new SimpleObjectProperty(place.getValue().getAddress()));

        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            refreshPlaceTable();
        });
    }

    private void refreshPlaceTable() {
        placesInTable.clear();

        if (searchTF.getText().isEmpty()) {
            placesInTable.addAll(places);
            placeTable.setItems(placesInTable);

        } else {

            for (Place place : places) {
                if (place.getAddress().contains(searchTF.getText())) {
                    placesInTable.addAll(place);
                }
            }
            placeTable.setItems(placesInTable);
        }
    }

    private void getPlaces() {
        places.clear();
        ArrayList<Place> workplaces = this.m.uc.getPlaces();
        for(int w = 1; w < workplaces.size(); w++){
            this.places.add(workplaces.get(w));
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        m.showHome();
    }

    @FXML
    private void getSelected(MouseEvent event) {
        try {
            TablePosition pos = placeTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            this.selectedPlace = placeTable.getItems().get(row);
        } catch (Exception e) {
            System.out.println("No hay nada seleccionado.");
        }
    }

    @FXML
    private void createHeadquarter(ActionEvent event) {
        this.m.showPlace(null);
    }

    @FXML
    private void modifyHeadquarters(ActionEvent event) {
        if (selectedPlace != null) {
            this.m.showPlace(selectedPlace);
        } else {
            this.m.showAlert("Debe elegir que sede desea modificar.", 1);
        }

    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void refreshData(MouseEvent event) {
        getPlaces();
        refreshPlaceTable();
        this.m.showAlert("Se sincronizaron los datos con la Base de Datos correctamente.", 4);
        System.out.println("Se sincronizaron los datos con la base de datos.");
    }
    
}

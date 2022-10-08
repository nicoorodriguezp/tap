/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.LicenceState;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
public class LicenceListController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private TableView<Licence> licenceTable;
    @FXML
    private TableColumn<Licence, Integer> idUserColumn;
    @FXML
    private TableColumn<Licence, String> nameColumn;
    @FXML
    private TableColumn<Licence, String> lastnameColumn;
    @FXML
    private TableColumn<Licence, LicenceState> stateColumn;
    @FXML
    private TableColumn<Licence, Boolean> lastOneColumn;
    @FXML
    private TableColumn<Licence, Integer> idLicenciaColumn;
    @FXML
    private TableColumn<Licence, Date> lastUpdateColumn;

    @FXML
    private Button updateLicenceButton;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchButton;

    private boolean isDriver;
    private ArrayList<Licence> licences = new ArrayList<>();
    private ObservableList<Licence> licencesInTable = FXCollections.observableArrayList();
    private Licence selectedLicence;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idUserColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getUser().getId()));
        nameColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getUser().getName()));
        lastnameColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getUser().getLastName()));
        stateColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getState().getName()));
        lastOneColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().isLastOne()));
        idLicenciaColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getId()));
        lastUpdateColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getLastUpdate()));

        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            refreshLicenceTable();
        });
    }

    void refreshLicenceTable() {
        licencesInTable.clear();

        if (searchTF.getText().isEmpty()) {
            licencesInTable.addAll(licences);
            licenceTable.setItems(licencesInTable);

        } else {

            for (Licence licence : licences) {
                if (licence.getUser().getId().toString().contains(searchTF.getText())) {
                    licencesInTable.addAll(licence);
                }
            }
            licenceTable.setItems(licencesInTable);
        }

    }


    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();
    }

    @FXML
    private void getSelected(MouseEvent event) {
        try {
            TablePosition pos = licenceTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            this.selectedLicence = licenceTable.getItems().get(row);
        } catch (Exception e) {
            System.out.println("No hay nada seleccionado.");
        }
    }

    @FXML
    private void modifyLicence(ActionEvent event) {
        if (selectedLicence != null) {
            this.m.showLicencePanel(selectedLicence, this);
        } else {
            this.m.showAlert("Debe elegir que usuario desea modificar.", 1);
        }
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void refreshData(MouseEvent event) {
        getLicences();
        refreshLicenceTable();
        this.m.showAlert("Se sincronizaron los datos con la Base de Datos correctamente.", 4);
        System.out.println("Se sincronizaron los datos con la base de datos.");
    }

    public void init(MainController mainController, boolean b) {
        this.m = mainController;
        this.isDriver = b;
        userNameLabel.setText(m.user.toString());
        getLicences();
        refreshLicenceTable();

    }

    private void getLicences(){

        this.licences.clear();

        // Si es un usuario regular entonces que muestre las licencias a su nombre, otherwise que muestre todas las licencias en proceso.
        if(isDriver){
            updateLicenceButton.setVisible(false);
            updateLicenceButton.setDisable(true);
            licences.addAll(m.uc.getLicences(m.user.getId()));
        }else{
            licences.addAll(m.ec.getLicencesInProcess(DeniedState, DeliveredState));
        }

        if(licences.isEmpty()){
            this.m.showAlert("No se encontraron licencias para mostrar.", 1);
        }

    }





}

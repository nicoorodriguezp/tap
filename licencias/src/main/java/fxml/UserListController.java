/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.tap.licencias.controller.AdminController;
import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import com.tap.licencias.entity.User;
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
public class UserListController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> idUserColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> lastnameColumn;
    @FXML
    private TableColumn<User, Position> positionColumn;
    @FXML
    private TableColumn<User, Boolean> inProcessColumn;
    @FXML
    private TableColumn<User, Place> workplaceColumn;
    @FXML
    private Button newEmployeeButton;
    @FXML
    private Button changeEmployeeButton;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchButton;

    private ArrayList<User> users = new ArrayList<>();
    private ObservableList<User> usersInTable = FXCollections.observableArrayList();
    private User selectedUser;

    public void init(MainController mainController){
        this.m = mainController;
        this.userNameLabel.setText(m.user.toString());

        getUsers();
        refreshUserTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        idUserColumn.setCellValueFactory(user -> new SimpleObjectProperty(user.getValue().getId()));
        nameColumn.setCellValueFactory(user -> new SimpleObjectProperty(user.getValue().getName()));
        lastnameColumn.setCellValueFactory(user -> new SimpleObjectProperty(user.getValue().getLastName()));
        positionColumn.setCellValueFactory(user -> new SimpleObjectProperty(user.getValue().getPosition().getName()));
        workplaceColumn.setCellValueFactory(user -> new SimpleObjectProperty(user.getValue().getWorkplace().getAddress()));
        inProcessColumn.setCellValueFactory(user -> new SimpleObjectProperty(user.getValue().isInProcess()));

        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            refreshUserTable();
        });


    }

    void refreshUserTable() {
        usersInTable.clear();

        if (searchTF.getText().isEmpty()) {
            usersInTable.addAll(users);
            userTable.setItems(usersInTable);

        } else {

            for (User user : users) {
                if (user.getName().contains(searchTF.getText())) {
                    usersInTable.addAll(user);
                }
            }
            userTable.setItems(usersInTable);
        }

    }

    private void getUsers() {
        users.clear();
        users.addAll(this.m.ac.getUsers());
    }

    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();
    }

    @FXML
    private void getSelected(MouseEvent event) {
        try {
            TablePosition pos = userTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            this.selectedUser = userTable.getItems().get(row);
        } catch (Exception e) {
            System.out.println("No hay nada seleccionado.");
        }
    }

    @FXML
    private void createUserPanel(ActionEvent event) {
        this.m.showUserPanel(false,null, this);
    }

    @FXML
    private void modifyUserPanel(ActionEvent event) {
        if (selectedUser != null) {
            this.m.showUserPanel(true, selectedUser, this);
        } else {
            this.m.showAlert("Debe elegir que usuario desea modificar.", 1);
        }
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void refreshData(MouseEvent event) {
        getUsers();
        refreshUserTable();
        this.m.showAlert("Se sincronizaron los datos con la Base de Datos correctamente.", 4);
        System.out.println("Se sincronizaron los datos con la base de datos.");
    }
    
}

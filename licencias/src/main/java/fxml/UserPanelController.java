/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import com.tap.licencias.entity.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
@Component
public class UserPanelController extends Controller implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField dniTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private ComboBox<Place> workplaceCB;
    @FXML
    private ComboBox<Position> positionCB;
    @FXML
    private Button saveButton;
    @FXML
    private Label labelUserUpdated;
    @FXML
    private Label labelUsernotUpdated;
    @FXML
    private Label userNameLabel;

    private boolean update;
    private boolean create_from_login =  false;

    private User selectedUser;

    private UserListController ulc;
    @FXML
    private Label labelWorkplace;
    @FXML
    private Label labelPosition;



    public void init(MainController m, ArrayList<Place> workplaces,  ArrayList<Position> positions){

        this.m = m;
        this.userNameLabel.setText("Nuevo Usuario");
        create_from_login = true;

        // Seteo los CB para crear un usuario regular
        workplaceCB.setEditable(false);
        positionCB.setEditable(false);
        workplaceCB.setVisible(false);
        positionCB.setVisible(false);
        labelWorkplace.setVisible(false);
        labelPosition.setVisible(false);
        workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
        positionCB.setItems(FXCollections.observableArrayList(positions));
        workplaceCB.valueProperty().setValue(workplaces.get(IsNotEmployeePlace-1));
        positionCB.valueProperty().setValue(positions.get(RegularUserPosition-1));

        setDniTF();

    }


    /**
     * @param ulc :Recibe este parametro para actualizar la tabla una vez que se hace alguna modificacion o alta de
     *            usuario.
     * */
    public void init(MainController m, Boolean update, ArrayList<Place> workplaces,
                     ArrayList<Position> positions, User selectedUser, UserListController ulc) {
        this.m = m;
        this.ulc = ulc;
        this.update = update;
        this.selectedUser = selectedUser;
        this.userNameLabel.setText(m.user.toString());

        workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
        positionCB.setItems(FXCollections.observableArrayList(positions));



        if (update) {
            if (m.user.getPosition().getId() != AdminPosition) {
                // Si no es un admin, entonces deshabilitar estas cosas de la interfaz
                workplaceCB.setEditable(false);
                positionCB.setEditable(false);
            }
            // Cargo los datos del usuario seleccionado.
            if (this.selectedUser != null) {
                dniTF.setText(String.valueOf(selectedUser.getId()));
                usernameTF.setText(selectedUser.getName());
                lastnameTF.setText(selectedUser.getLastName());
                passwordTF.setText(selectedUser.getPassword());
                workplaceCB.valueProperty().setValue(selectedUser.getWorkplace());
                positionCB.valueProperty().setValue(selectedUser.getPosition());
            }

        }



        setDniTF();

    }

    private void setDniTF(){
        // Fuerzo a que el contenido del tf sea solo numerico.
        dniTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    dniTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveUser(ActionEvent event) {
        if (checkUserData()) {

            if(create_from_login){createUser();} else {manageUserFromInside();}

            labelUsernotUpdated.setVisible(false);
            labelUserUpdated.setVisible(true);

        }

    }

    private void manageUserFromInside() {

        if (!update) {

            createUser();
//          m.showAlert("Se agrego un " + positionCB.getSelectionModel().getSelectedItem() + " al workplace: "
//                        + workplaceCB.valueProperty().getValue(), 1);
        } else {
            this.selectedUser.setId(Integer.valueOf(dniTF.getText()));
            this.selectedUser.setName(usernameTF.getText());
            this.selectedUser.setLastName(lastnameTF.getText());
            this.selectedUser.setPosition(positionCB.valueProperty().getValue());
            this.selectedUser.setWorkplace(workplaceCB.valueProperty().getValue());
            this.selectedUser.setPassword(passwordTF.getText());
            m.ac.updateUser(this.selectedUser);
            m.showAlert("Se actualizaron los datos del usuario: " + selectedUser.getName(), 4);
        }

//        ulc.refreshUserTable();
    }

    private boolean checkUserData() {

        if (dniTF.getText().isEmpty()) {
            m.showAlert("Debe introducir la identificación del usuario.", 1);
            return false;
        } else if (usernameTF.getText().isEmpty()) {
            m.showAlert("El nombre del usuario está vacío.", 1);
            return false;
        } else if (lastnameTF.getText().isEmpty()) {
            m.showAlert("El apellido del usuario está vacío.", 1);
            return false;
        } else if (passwordTF.getText().isEmpty()) {
            m.showAlert("La contraseña del usuario está vacía.", 1);
            return false;
        } else if (workplaceCB.valueProperty().getValue() == null) {
            m.showAlert("Debe seleccionar el lugar de trabajo del usuario.", 1);
            return false;
        } else if (positionCB.valueProperty().getValue() == null) {
            m.showAlert("Debe seleccionar el puesto de trabajo del usuario.", 1);
            return false;
        }

        return true;

    }

    private void createUser(){
        User user= new User();
        user.setId(Integer.valueOf(dniTF.getText()));
        user.setName(usernameTF.getText());
        user.setLastName(lastnameTF.getText());
        user.setInProcess(false);
        user.setPosition(positionCB.valueProperty().getValue());
        user.setWorkplace(workplaceCB.valueProperty().getValue());
        user.setPassword(passwordTF.getText());
        m.uc.createUser(user);
        m.showAlert("Se creo el usuario correctamente.", 4);
    }

    @FXML
    private void goBack(ActionEvent event) {
        if(create_from_login){
            m.showLogin(m.appTitle);
        }else{
            Button button = (Button) event.getSource();
            Scene scene = button.getScene();
            m.showUserListPanel(scene);
        }
    }
}

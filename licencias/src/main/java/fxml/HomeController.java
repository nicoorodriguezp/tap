/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
public class HomeController extends Controller implements Initializable {

    @FXML
    private Label userNameLabel;
    @FXML
    private ImageView workplaceIcon;
    @FXML
    private Pane adminPane;
    @FXML
    private Pane employeePane;
    @FXML
    private Button updateLicenceButton;
    @FXML
    private ImageView icon;

    private Button button;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    public void init(MainController m) {
        this.m = m;
        this.userNameLabel.setText(m.user.toString());

        if (m.user.getPosition().getId() == RegularUserPosition) {
            this.workplaceIcon.setImage(
                    new Image(getClass().getResourceAsStream("/static/icons/driver_filled.png")));
        } else {
            this.workplaceIcon.setImage(
                    new Image(getClass().getResourceAsStream("/static/icons/employee.png")));
        }

        initButtons();

    }

    private void initButtons() {

        if (m.user.getPosition().getId() != AdminPosition) {
            adminPane.setVisible(false);

            if (m.user.getPosition().getId() != EmployeePosition) {
                this.employeePane.setVisible(false);
                System.out.println("It's a driver.");
                icon.setVisible(true);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showLogin(ActionEvent event) {
        m.user = null;
        m.showLogin(m.appTitle);
    }

    @FXML
    private void showLicencias(ActionEvent event) {

        this.button = (Button) event.getSource();
        this.scene = button.getScene();
        this.m.showDriverLicences(scene);
    }

    @FXML
    private void showAppointmentPanel(ActionEvent event) {
        this.m.showAppointmentPanel();
    }


    @FXML
    private void showPlacesPanel(ActionEvent event) {
        this.m.showPlaceList();
    }

    @FXML
    private void showEmployeesPanel(ActionEvent event) {
        this.button = (Button) event.getSource();
        this.scene = button.getScene();
        this.m.showUserListPanel(scene);
    }

    @FXML
    private void showLicencesInProcessPanel(ActionEvent event) {
        this.button = (Button) event.getSource();
        this.scene = button.getScene();
        this.m.showLicencesInProcess(scene);
    }

    @FXML
    private void showAppointmentListPanel(ActionEvent event) {

        this.button = (Button) event.getSource();
        this.scene = button.getScene();
        this.m.showAppointmentsList(scene);

    }

    @FXML
    private void showReport(ActionEvent event) {

        this.button = (Button) event.getSource();
        this.scene = button.getScene();
        this.m.showReportGenerator(scene);
    }
    
}

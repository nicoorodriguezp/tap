package fxml;

import java.io.IOException;


import com.tap.licencias.controller.AdminController;
import com.tap.licencias.controller.EmployeeController;
import com.tap.licencias.controller.UserController;
import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    protected AdminController ac;
    @Autowired
    protected EmployeeController ec;
    @Autowired
    protected UserController uc;

    public User user;
    public Stage stage;
    public FXMLLoader fxmlLoader;

    public Parent parent;

    protected String appTitle;

    public MainController(Stage stage, ApplicationContext applicationContext, AdminController ac, EmployeeController ec, UserController uc) {
        this.ac = ac;
        this.ec = ec;
        this.uc = uc;

        this.stage = stage;
        this.applicationContext = applicationContext;
    }

    protected void getFXML(String name) {
        try {
            this.fxmlLoader = null;
            this.fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/" + name + ".fxml"));
            this.parent = this.fxmlLoader.load();


        } catch (IOException e) {
            System.out.println(e);
            showAlert("No se pudo cargar la interfaz grafica: " + name, 2);
        }
    }

    public void showLogin(String appTitle) {

        getFXML("Login");
        this.appTitle = appTitle;

        Controller controller = this.fxmlLoader.getController();
        controller.init(this);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle(appTitle);

        this.stage.show();
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>> User Panels <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void showUserPanelFromLogin() {

        getFXML("User");

        UserPanelController controller = this.fxmlLoader.getController();
        controller.init(this, uc.getPlaces(), uc.getPositions());

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Create User Panel");

        this.stage.show();
    }

    protected void showUserPanel(Boolean update, User selectedUser, UserListController ulc) {


        getFXML("User");

        UserPanelController controller = this.fxmlLoader.getController();
        controller.init(this, update, uc.getPlaces(), uc.getPositions(), selectedUser, ulc);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Create User Panel");

        this.stage.show();

    }


    protected void showUserListPanel(Scene scene){

        getFXML("UserList");

        UserListController controller = this.fxmlLoader.<UserListController>getController();
        controller.init(this);

        scene.setRoot(parent);
        this.stage.setScene(scene);
        this.stage.setTitle("Usuarios");

        this.stage.show();
    }

    protected void showDriverLicences(Scene scene) {

        getFXML("LicenceList");

        LicenceListController controller = this.fxmlLoader.<LicenceListController>getController();
        controller.init(this, true);

        scene.setRoot(parent);
        this.stage.setScene(scene);
        this.stage.setTitle("Licencias");

        this.stage.show();

    }

    protected void showAppointmentPanel(){
        getFXML("Appointment");

        AppointmentController controller = this.fxmlLoader.<AppointmentController>getController();
        controller.init(this, uc.getPlaces());

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Solicitar Turno");

        this.stage.show();
    }

    protected void showLicencesInProcess(Scene scene){
        getFXML("LicenceList");

        LicenceListController controller = this.fxmlLoader.<LicenceListController>getController();
        controller.init(this, false);

        scene.setRoot(parent);
        this.stage.setScene(scene);
        this.stage.setTitle("Licencias");

        this.stage.show();
    }


    public void showLicencePanel(Licence selectedLicence, LicenceListController licenceListController) {

        getFXML("Licence");

        LicencePanelController controller = this.fxmlLoader.<LicencePanelController>getController();
        controller.init(this, selectedLicence, licenceListController, uc.getLicenceStates());

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Actualizar Licencia");

        this.stage.show();


    }


    protected void showPlace(Place selectedPlace){
        getFXML("Location");

        LocationPanelController controller = this.fxmlLoader.<LocationPanelController>getController();
        controller.init(this, selectedPlace);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Sede");

        this.stage.show();
    }

    protected void showPlaceList(){
        getFXML("LocationList");

        LocationListController controller = this.fxmlLoader.<LocationListController>getController();
        controller.init(this);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("lista de Sedes");

        this.stage.show();
    }

    protected void showHome() {

        getFXML("Home");
        HomeController controller = this.fxmlLoader.<HomeController>getController();
        controller.init(this);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Home Panel - " + user.toString());
        this.stage.show();

    }


    protected void showReportGenerator(Scene scene){

        getFXML("ReportGenerator");

        ReportGeneratorController controller = this.fxmlLoader.<ReportGeneratorController>getController();
        controller.init(this, uc.getLicenceStates(), ac);

        scene.setRoot(parent);
        this.stage.setScene(scene);
        this.stage.setTitle("Generar Reporte");

        this.stage.show();


    }

    protected void showStatics(Scene scene){
        getFXML("Statics");

        StaticsController controller = this.fxmlLoader.<StaticsController>getController();
        controller.init(this, ac.getAllAppointments(), uc.getPlaces(), uc.getLicenceStates(), ac.getAllLicences());

        scene.setRoot(parent);
        this.stage.setScene(scene);
        this.stage.setTitle("Generar Reporte");

        this.stage.show();

    }


    /**
     * @param alertDesc : Contenido de la alerta. Mensaje a mostrar.
     * @param type      :(0) Alerta -- (1) Informacion -- (2) Error -- (3) Database
     *                  Error -- (4) Success
     */
    public void showAlert(String alertDesc, int type) {

        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Alert.fxml"));
            Parent alert = (Parent) fxmlLoader.load();

            AlertController controller = fxmlLoader.<AlertController>getController();
            controller.init(alertDesc, type);

            Stage alertStage = new Stage();
            alertStage.setScene(new Scene(alert, 500, 250));
            alertStage.initStyle(StageStyle.UNDECORATED);
            alertStage.setTitle("");

            alertStage.show();

        } catch (IOException e) {
        }
    }


    public void showAppointmentsList(Scene scene) {
        getFXML("AppointmentsList");

        AppointmentsListController controller = this.fxmlLoader.<AppointmentsListController>getController();
        controller.init(this);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Tus turnos");

        this.stage.show();

    }
}

package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import com.tap.licencias.exception.DAOException;
import com.tap.licencias.exception.LoginUserException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

@Component
public class LoginController extends Controller implements Initializable {

    @FXML
    private TextField dniTF;
    @FXML
    private Button loginButton;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button createUserButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void init(MainController mainController) {
        this.m = mainController;
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

    private void initDatabase(){

        Place place = new Place();
        place.setId(IsNotEmployeePlace);
        place.setAddress("NO ES EMPLEADO");
        m.ac.createPlace(place);

        place.setId(2);
        place.setAddress("Av. Cabildo 3067 - Comuna 13");
        m.ac.createPlace(place);

        place.setId(3);
        place.setAddress("Beruti 3325 - Comuna 14");
        m.ac.createPlace(place);

        Position p = new Position();
        p.setId(AdminPosition);
        p.setName("Admin");
        m.ac.createPosition(p);

        p.setId(EmployeePosition);
        p.setName("Employee");
        m.ac.createPosition(p);

        p.setId(RegularUserPosition);
        p.setName("User");
        m.ac.createPosition(p);

        LicenceState ls = new LicenceState();
        ls.setId(InitialState);
        ls.setName("Initial");
        m.ac.createLicenceState(ls);

        ls.setId(AwaitingPrintingState);
        ls.setName("Awaiting Printing");
        m.ac.createLicenceState(ls);

        ls.setId(ReadySate);
        ls.setName("Ready");
        m.ac.createLicenceState(ls);

        ls.setId(DeliveredState);
        ls.setName("Delivered");
        m.ac.createLicenceState(ls);

        ls.setId(DeniedState);
        ls.setName("Denied");
        m.ac.createLicenceState(ls);

    }

    @FXML
    private void login(ActionEvent event) {


        if(dniTF.getText().trim() != "" && passwordTF.getText().trim() != ""){
            try {
                this.m.user = m.uc.getUser(Integer.parseInt(dniTF.getText()));
                if(this.m.user != null){
                    if (this.m.user.getPassword().trim().equals(passwordTF.getText().trim())) {
                        m.showHome();
                    } else {
                        m.showAlert("La contraseña es incorrecta", 2);
                    }
                }else{
                    m.showAlert("No existe un usuario con ese DNI", 2);
                }

            }
            catch (LoginUserException e){
                m.showAlert(e.getMessage(), 2);
                e.printStackTrace();
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        }else{
            m.showAlert("Primero ingrese los datos.", 1);
        }



    }

    @FXML
    private void showCreateUser(ActionEvent event) {
        m.showUserPanelFromLogin();
    }

}

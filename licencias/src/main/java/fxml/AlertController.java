package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class AlertController implements Initializable {

    @FXML
    private Label alertDescLabel;
    @FXML
    private ImageView iconAlert;

    /**
     * Muestra una interfaz con un mesaje determinado segun un tipo de mensaje.
     * 
     * @param
     * alertDesc        : Contenido de la alerta. Mensaje a mostrar.
     * 
     * 
     * @param type      :
     * 
     *                  (0) Alerta --
     *                  (1) Informacion --
     *                  (2) Error --
     *                  (3) Database Error --
     *                  (4) Success
     */
    public void init(String alertDesc, int type) {

        alertDescLabel.setText(alertDesc);

        switch (type) {
            case 0:
                // Alert
                iconAlert.setImage(
                        new Image(getClass().getResourceAsStream("/static/icons/alertIcon.png")));
                break;

            case 1:
                // Info
                iconAlert.setImage(
                        new Image(getClass().getResourceAsStream("/static/icons/infoIcon.png")));
                break;

            case 2:
                // Error
                iconAlert.setImage(
                        new Image(getClass().getResourceAsStream("/static/icons/errorIcon.png")));
                break;

            case 3:
                // Database Error
                iconAlert.setImage(
                        new Image(getClass().getResourceAsStream("/static/icons/databaseErrorIcon.png")));
                break;
            case 4:
                // Success
                iconAlert.setImage(
                        new Image(getClass().getResourceAsStream("/static/icons/successIcon.png")));
                break;

            default:
                iconAlert.setImage(
                        new Image(getClass().getResourceAsStream("/static/icons/alertIcon.png")));
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stageClose = (Stage) iconAlert.getScene().getWindow();
        stageClose.close();
    }

}

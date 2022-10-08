package com.tap.licencias.exception;

import fxml.AlertController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class BusinessMessage extends Exception {

    public Stage stage;
    public FXMLLoader fxmlLoader;

    public BusinessMessage(String message, int type) {

        showMessage(message, type);

    }

    public void showMessage(String message, int type) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/message.fxml"));
            Parent p = (Parent) fxmlLoader.load();

            AlertController controller = this.fxmlLoader.getController();
            controller.init(message, type);

            Stage messageStage = new Stage();
            messageStage.setScene(new Scene(p, 500, 250));
            messageStage.initStyle(StageStyle.UNDECORATED);
            messageStage.setTitle("");

            messageStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

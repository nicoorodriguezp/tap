package com.tap.licencias;

import com.tap.licencias.controller.AdminController;
import com.tap.licencias.controller.EmployeeController;
import com.tap.licencias.controller.UserController;
import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.Place;
import com.tap.licencias.entity.Position;
import fxml.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<JavaFxMain.StageReadyEvent> {

    @Autowired
    protected AdminController ac;
    @Autowired
    protected EmployeeController ec;
    @Autowired
    protected UserController uc;


    @Override
    public void onApplicationEvent(JavaFxMain.StageReadyEvent event) {

        MainController m = new MainController(event.getStage(), event.applicationContext(), ac, ec, uc);

        m.showLogin("Sistema de Gestion de Insumos");



    }


}

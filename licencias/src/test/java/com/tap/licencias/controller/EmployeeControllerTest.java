package com.tap.licencias.controller;

import com.tap.licencias.dao.employee.EmployeeDAO;
import com.tap.licencias.entity.Licence;
import com.tap.licencias.service.EmployeeServiceTest;
import com.tap.licencias.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class EmployeeControllerTest implements EmployeeDAO {

    @Qualifier("employeeServiceTest")
    @Autowired
    EmployeeServiceTest employeeService;

    @Override
    public void updateLicenceState(Licence licence) {employeeService.updateLicenceState(licence);}

    @Override
    public ArrayList<Licence> getLicencesInProcess(Integer DeniedState, Integer DeliveredState) {
        return employeeService.getLicencesInProcess(DeniedState, DeliveredState);
    }
}

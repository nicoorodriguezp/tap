package com.tap.licencias.service;

import com.tap.licencias.dao.employee.EmployeeDAO;
import com.tap.licencias.entity.Licence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeServiceTest extends UserServiceTest implements EmployeeDAO {


    @Override
    public void updateLicenceState(Licence licence) {
        licenceRepository.save(licence);
    }

    @Override
    public ArrayList<Licence> getLicencesInProcess(Integer DeniedState, Integer DeliveredState) {
        return licenceRepository.getLicencesInProcess(DeniedState, DeliveredState);
    }
}

package com.tap.licencias.dao.employee;

import com.tap.licencias.entity.Licence;

import java.util.ArrayList;

public interface EmployeeDAO {

    void updateLicenceState(Licence licence);

    ArrayList<Licence> getLicencesInProcess(Integer deniedState, Integer deliveredState);


}

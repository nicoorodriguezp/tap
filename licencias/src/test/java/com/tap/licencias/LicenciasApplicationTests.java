package com.tap.licencias;


import com.tap.licencias.controller.AdminControllerTest;
import com.tap.licencias.controller.EmployeeControllerTest;
import com.tap.licencias.controller.UserControllerTest;
import com.tap.licencias.integrationTest.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class LicenciasApplicationTests {

	@Autowired
	protected AdminControllerTest ac;

	@Autowired
	protected EmployeeControllerTest ec;

	@Autowired
	protected UserControllerTest uc;

	@Test
	void contextLoads() {
		IntegrationTest test = new IntegrationTest(ac, ec, uc);
		test.createAppointmentTest();
	}


}

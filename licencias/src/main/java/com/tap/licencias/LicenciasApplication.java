package com.tap.licencias;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*

	Main Spring boot App
	
*/
@EnableJpaRepositories
@SpringBootApplication
public class LicenciasApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxMain.class, args);
	}

}

package fxml;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Component
public class Controller {

    protected MainController m;

    // User Positions
    protected final int AdminPosition = 1;
    protected final int EmployeePosition = 2;
    protected final int RegularUserPosition = 3;

    // Licence States
    protected final Integer InitialState = 1;
    protected final Integer AwaitingPrintingState = 2;
    protected final Integer ReadySate = 3;
    protected final Integer DeliveredState = 4;
    protected final Integer DeniedState = 5;
    protected final Integer ToBeWithdrawnState = 6;


    // Places

    protected final Integer IsNotEmployeePlace = 1;

    public void init(MainController mainController) {
        this.m = mainController;
    }

    protected String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.tap.licencias.entity.Appointment;
import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.LicenceState;
import com.tap.licencias.entity.Place;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class StaticsController
 *
 * @author Nicolas Gaston Rodriguez Perez
 */
public class StaticsController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private PieChart headquartersPie;
    @FXML
    private StackedBarChart<String, Number> statesBarChart;
    @FXML
    private Label cant_appointments;
    @FXML
    private Label cant_delivered;
    private int cant_turnos = 0;
    private int cant_entregados = 0;

    private MainController m;


    public void init(MainController m, ArrayList<Appointment> appointments, ArrayList<Place> places, ArrayList<LicenceState> states, ArrayList<Licence> licences ){

        this.m = m;
        this.userNameLabel.setText(m.user.toString());

        ArrayList<PlaceStatic> placesStatic = new ArrayList<>();
        for(int i=0; i<places.size(); i++){
            PlaceStatic ps = new PlaceStatic();
            ps.setAppointment(places.get(i));
            placesStatic.add(ps);
        }

        for(int a = 0; a<appointments.size(); a++){
            for(int i=0; i<places.size(); i++){
                if(appointments.get(a).getPlace().getId().equals(placesStatic.get(i).getPlace().getId())){
                    placesStatic.get(i).addAmount();
                    break;
                }
            }
            cant_turnos = cant_turnos + 1;

        }

        cant_appointments.setText(String.valueOf(cant_turnos));

        ArrayList<PieChart.Data> dataPie = new ArrayList<>();
        for(int i=0; i<places.size(); i++){
            if(!places.get(i).getId().equals(IsNotEmployeePlace)){
                dataPie.add(new PieChart.Data(placesStatic.get(i).getPlace().getAddress(), placesStatic.get(i).getAmount()));
            }
        }
        for(int i=0; i<dataPie.size(); i++){
            System.out.println(dataPie.get(i));
        }


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(dataPie);
        headquartersPie.setData(pieChartData);
        headquartersPie.setTitle("Sedes x Cantidad de Turnos");


        CategoryAxis xAxis = new CategoryAxis();

        ArrayList<String> statesName = new ArrayList<>();
        ArrayList<StateStatic> stateStatics = new ArrayList<>();
        for(int s = 0; s<states.size(); s++){
            statesName.add(states.get(s).getName());
            StateStatic ss = new StateStatic();
            ss.setState(states.get(s));
            stateStatics.add(ss);
        }

        // actualizo la cantidad de licencias por estado
        for(int l = 0; l<licences.size(); l++){
            for(int s = 0; s<states.size(); s++){
                if(licences.get(l).getState().getId().equals(states.get(s).getId())){
                    stateStatics.get(s).addAmount();
                    break;
                }
            }

            if (licences.get(l).getState().getId().equals(DeliveredState)){
                cant_entregados = cant_entregados + 1;
            }

        }

        cant_delivered.setText(String.valueOf(cant_entregados));


        xAxis.setCategories(FXCollections.observableArrayList(statesName));
        xAxis.setLabel("Estado de Licencia");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cantidad de Licencias");

//        statesBarChart = new StackedBarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("2022");

        for(int ss = 0; ss< stateStatics.size(); ss++){
            series1.getData().add(new XYChart.Data<>(stateStatics.get(ss).getState().getName(), stateStatics.get(ss).getAmount()));
        }

        statesBarChart.getData().addAll(series1);
        statesBarChart.setStyle("-fx-font: 15 arial;");



    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();
    }
    
}

package fxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;


import com.tap.licencias.controller.AdminController;
import com.tap.licencias.entity.Licence;
import com.tap.licencias.entity.LicenceState;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

public class ReportGeneratorController extends Controller implements Initializable {

    @FXML
    private ImageView dateIcon;
    @FXML
    private DatePicker datePicker = new DatePicker(LocalDate.now());
    @FXML
    private DatePicker datePickerEnd = new DatePicker(LocalDate.now());;
    @FXML
    private Button generateButton;
    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;

    @FXML
    private Button generateExcel;
    @FXML
    private TableColumn<Licence, String> nameColumn;
    @FXML
    private TableColumn<Licence, Integer> stateColumn;

    ObservableList<Licence> licencesReport = FXCollections.observableArrayList();

    @FXML
    private ComboBox<LicenceState> estadosCB;
    @FXML
    private TableColumn<Licence, Integer> idLicenciaColumn;
    @FXML
    private TableColumn<Licence, String> lastNameColumn;
    @FXML
    private TableColumn<Licence, Integer> idDriverColumn;
    @FXML
    private TableColumn<Licence, Date> updateDateColumn;
    @FXML
    private TableView<Licence> reportTable;

    public void init(MainController m, ArrayList<LicenceState> licenceStates, AdminController ac) {

        this.m = m;
        this.userNameLabel.setText(m.user.toString());

        estadosCB.setItems(FXCollections.observableArrayList(licenceStates));

        generateButton.setOnAction(e -> {
            
            try {

                LicenceState ls = estadosCB.valueProperty().getValue();
                System.out.println(ls);

                reportTable.setItems(null);
                licencesReport.clear();
                licencesReport.addAll(ac.getLicencesStateDatesBetween(
                        Date.valueOf(datePicker.getValue()),
                        Date.valueOf(datePickerEnd.getValue()),
                        ls
                ));
                    setLicencesTable();
            }
            catch (NullPointerException exception){
                m.showAlert("Introduzca fechas que corresponda con el formato dd/MM/yyyy", 1);
            }
        });


    }

    private void setLicencesTable() {

        if (!licencesReport.isEmpty()) {
            reportTable.setItems(licencesReport);
        } else {
            m.showAlert("No hubo despachos en el dia seleccionado.", 1);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idLicenciaColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getId()));
        idDriverColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getUser().getId()));
        nameColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getUser().getName()));
        lastNameColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getUser().getLastName()));
        stateColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getState().getName()));
        updateDateColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().getLastUpdate()));

//        sedeColumn.setCellValueFactory(licence -> new SimpleObjectProperty(licence.getValue().get));

        datePicker.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });

        datePickerEnd.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });

    }

    @FXML
    private void goBack(ActionEvent event) {
        m.showHome();
    }

    @FXML
    private void generateExcel(ActionEvent event) throws IOException {

        if (datePicker.getValue() != null && reportTable.getColumns().get(0).getText() != null) {
            String date = datePicker.getValue()
                    .format(DateTimeFormatter.ofPattern("dd_MM_yy"));

            String sheetName = "Reporte_Licencias_" + estadosCB.getValue().getName() + "_" + date;

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Reporte");
            fileChooser.setInitialFileName(sheetName);
            File selectedFile = fileChooser.showSaveDialog(null);

            if (selectedFile != null) {

                try (Workbook workbook = new HSSFWorkbook()) {
                    Sheet spreadsheet = workbook.createSheet(sheetName);

                    Row row = spreadsheet.createRow(0);

                    for (int j = 0; j < reportTable.getColumns().size(); j++) {
                        row.createCell(j).setCellValue(reportTable.getColumns().get(j).getText());
                    }

                    for (int i = 0; i < reportTable.getItems().size(); i++) {
                        row = spreadsheet.createRow(i + 1);
                        for (int j = 0; j < reportTable.getColumns().size(); j++) {
                            if (reportTable.getColumns().get(j).getCellData(i) != null) {
                                row.createCell(j)
                                        .setCellValue(reportTable.getColumns().get(j).getCellData(i).toString());
                            } else {
                                row.createCell(j).setCellValue("");
                            }
                        }
                    }

                    FileOutputStream fileOut = new FileOutputStream(selectedFile.getAbsoluteFile() + ".xls");
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (Exception e) {
                    this.m.showAlert("Hubo un problema al exportar el documento.", 2);
                }
                this.m.showAlert("Reporte de Licencias exportado correctamente.", 4);
            } else {
                this.m.showAlert("Debe seleccionar el directorio donde quiera exportar el documento.", 1);
            }
        } else {
            this.m.showAlert(
                    "La tabla no tiene contenido. Por favor seleccione una fecha que tenga tramites de licencias asociados.", 1);
        }

    }

    @FXML
    private void showStatics(ActionEvent event) {

        Scene scene;
        this.backButton = (Button) event.getSource();
        scene = backButton.getScene();
        this.m.showStatics(scene);
    }

}

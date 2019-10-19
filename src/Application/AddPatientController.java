package Application;

import java.io.IOException;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import Database.Database;

public class AddPatientController {

    private ObservableList sexChoiceBox = FXCollections.observableArrayList("Male",
            "Female");
    private ObservableList temperamentChoiceBox = FXCollections.observableArrayList("Friendly",
        "Neutral","Aggressive");

    @FXML
    private Button dashboardButton;
    @FXML
    private Button patientsButton;
    @FXML
    private Label newPatientNameLabel;
    @FXML
    private TextField newPatientName;
    @FXML
    private Label newPatientSpeciesLabel;
    @FXML
    private TextField newPatientSpecies;
    @FXML
    private Label newPatientSexLabel;
    @FXML
    private ChoiceBox<String> newPatientSex;
    @FXML
    private Label newPatientColorLabel;
    @FXML
    private TextField newPatientColor;
    @FXML
    private Label newPatientBreedLabel;
    @FXML
    private TextField newPatientBreed;
    @FXML
    private Label newPatientAgeLabel;
    @FXML
    private TextField newPatientAge;
    @FXML
    private Label newPatientMicrochipLabel;
    @FXML
    private TextField newPatientMicrochip;
    @FXML
    private Button savePatientButton;
    @FXML
    private Label requiredFieldsAlertLabel;
    @FXML
    private ChoiceBox<String> newPatientTemperament;
    @FXML
    private TextField newPatientWeight;
    @FXML
    private RadioButton newPatientAdoptable;
    @FXML
    private Label newPatientAdoptableLabel;
    @FXML
    private Label newPatientTemperamentLabel;
    @FXML
    private Label newPatientWeightLabel;

    // Load String values into Sex ChoiceBox.
    @FXML
    private void initialize() throws Exception {
        newPatientSex.setItems(sexChoiceBox);
        newPatientTemperament.setItems(temperamentChoiceBox);
    }

    // Return to Dashboard
    public void dashboardButtonPushed(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    // Return to Patients Scene
    public void patientsButtonPushed(ActionEvent event) throws IOException {
        Parent patientsParent = FXMLLoader.load(getClass().getResource("/Patients.fxml"));
        Scene patientsScene = new Scene(patientsParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientsScene);
        window.show();
    }

    // Go to Kennel
    public void kennelButtonPressed(ActionEvent event) throws IOException {
        Parent kennelParent = FXMLLoader.load(getClass().getResource("/Kennel.fxml"));
        Scene kennelScene = new Scene(kennelParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(kennelScene);
        window.show();
    }


    public void savePatientButtonPushed(ActionEvent event) throws IOException {
        // Basically the following code sets the label of any field that hasn't been filled in properly to red.
        // The else statements reset the color to the original color if it has been filled in (in case they fixed 1 but
        // not all.
        boolean allFieldsFilled = true;
        if (newPatientName.getText().trim().isEmpty()) {
            newPatientNameLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientNameLabel.setStyle("-fx-text-fill: #ddedf4");

        if (newPatientSpecies.getText().trim().isEmpty()) {
            newPatientSpeciesLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientSpeciesLabel.setStyle("-fx-text-fill: #ddedf4");

        if (newPatientSex.getValue() == null) {
            newPatientSexLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientSexLabel.setStyle("-fx-text-fill: #ddedf4");

        if (newPatientColor.getText().trim().isEmpty()) {
            newPatientColorLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientColorLabel.setStyle("-fx-text-fill: #ddedf4");

        if (newPatientBreed.getText().trim().isEmpty()) {
            newPatientBreedLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientBreedLabel.setStyle("-fx-text-fill: #ddedf4");

        if (newPatientAge.getText().trim().isEmpty()) {
            newPatientAgeLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientAgeLabel.setStyle("-fx-text-fill: #ddedf4");

        if (newPatientMicrochip.getText().trim().isEmpty()) {
            newPatientMicrochipLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientMicrochipLabel.setStyle("-fx-text-fill: #ddedf4");

        if (newPatientWeight.getText().trim().isEmpty()) {
            newPatientWeightLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientWeightLabel.setStyle("-fx-text-fill: #ddedf4");

        if (newPatientTemperament.getValue() == null) {
            newPatientTemperamentLabel.setStyle("-fx-text-fill: red");
            requiredFieldsAlertLabel.setText("(Red fields are required)");
            allFieldsFilled = false;
        } else newPatientTemperamentLabel.setStyle("-fx-text-fill: #ddedf4");
        String adoptableRadio_On = "True";
        String adoptableRadio_Off = "False";

        if (allFieldsFilled) {
            // Passed the other checks, so create new animal in database
            Database
                    .saveNewPatient(newPatientName.getText(), newPatientSpecies.getText(), newPatientTemperament.getValue(),
                            newPatientSex.getValue(), newPatientColor.getText(), newPatientBreed.getText(),
                            newPatientMicrochip.getText(), newPatientAge.getText(), newPatientWeight.getText(),
                            java.util.Calendar.getInstance().getTime().toString(),
                            newPatientAdoptable.isSelected()?adoptableRadio_On:adoptableRadio_Off );

            // Now that the animal has been added, send user back to the patients page.
            patientsButtonPushed(event);
        }
    }
}

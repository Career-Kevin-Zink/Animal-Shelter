package Application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import Database.Database;

public class AddPatientController {

    @FXML
    private Button dashboardButton;
    @FXML
    private Button patientsButton;
    @FXML
    private TextField newPatientName;
    @FXML
    private TextField newPatientSpecies;
    @FXML
    private ChoiceBox<?> newPatientSex;
    @FXML
    private TextField newPatientColor;
    @FXML
    private TextField newPatientBreed;
    @FXML
    private TextField newPatientAge;
    @FXML
    private TextField newPatientMicrochip;
    @FXML
    private Button savePatientButton;

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

    public void savePatientButtonPushed(ActionEvent event) throws IOException {

        // @TODO Have the appropriate text field change to red when the form is submitted but the field is empty.
        // @TODO Same thing as any other form does when you don't fill in required information
        if (newPatientName.getText().isEmpty() || newPatientName.getText().compareTo(" ") == 0) {
            System.out.println("Name field required");
        }
        if (newPatientSpecies.getText().isEmpty() || newPatientSpecies.getText().compareTo(" ") == 0) {
            System.out.println("Species field required");
        }
        if (newPatientColor.getText().isEmpty() || newPatientColor.getText().compareTo(" ") == 0) {
            System.out.println("Color field required");
        }
        if (newPatientBreed.getText().isEmpty() || newPatientBreed.getText().compareTo(" ") == 0) {
            System.out.println("Breed field required");
        }
        if (newPatientAge.getText().isEmpty() || newPatientAge.getText().compareTo(" ") == 0) {
            System.out.println("Age field required");
        }
        if (newPatientMicrochip.getText().isEmpty() || newPatientMicrochip.getText().compareTo(" ") == 0) {
            System.out.println("Microchip field required");
        }

        // Passed the other checks, so create new animal in database
        Database.saveNewPatient(newPatientName.getText(), newPatientSpecies.getText(), newPatientSex.toString(),
                newPatientColor.getText(), newPatientBreed.getText(),
                Integer.parseInt(newPatientAge.getText()),newPatientMicrochip.getText());
    }
}

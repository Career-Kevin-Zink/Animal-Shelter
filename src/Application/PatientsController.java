package Application;

import java.io.IOException;

import Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PatientsController {

    @FXML
    private ScrollPane displayPatientsScrollPane;
    @FXML
    private GridPane displayPatientsTitleGridPane;
    @FXML
    private GridPane displayPatientsGridPane;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button patientsButton;
    @FXML
    private Button addPatientButton;

    public void initialize() {
        for (Animal animal : Database.animals) {
            addAnimalToDisplay(animal);
        }
    }

    // Return to Dashboard Button
    public void dashboardButtonPushed(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    // Return to Patients Button
    public void patientsButtonPushed(ActionEvent event) throws IOException {
        Parent patientsParent = FXMLLoader.load(getClass().getResource("/Patients.fxml"));
        Scene patientsScene = new Scene(patientsParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientsScene);
        window.show();
    }

    // Add Patient
    public void addPatientButtonPushed(ActionEvent event) throws IOException {
        Parent addPatientParent = FXMLLoader.load(getClass().getResource("/AddPatient.fxml"));
        Scene addPatientScene = new Scene(addPatientParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPatientScene);
        window.show();
    }

    // Add Animal to display list
    public void addAnimalToDisplay(Animal animal) {
        Label id = new Label(String.valueOf(animal.getAnimalID()));
        Label name = new Label(animal.getName());
        Label species = new Label(animal.getSpecies());
        id.setStyle("-fx-alignment: CENTER; -fx-pref-width: 164.0; -fx-pref-height: 17.0");
        name.setStyle("-fx-alignment: CENTER; -fx-pref-width: 164.0; -fx-pref-height: 17.0");
        species.setStyle("-fx-alignment: CENTER; -fx-pref-width: 164.0; -fx-pref-height: 17.0");
        displayPatientsGridPane.addRow(displayPatientsGridPane.getChildren().size() / 3, id, name, species);
    }

}

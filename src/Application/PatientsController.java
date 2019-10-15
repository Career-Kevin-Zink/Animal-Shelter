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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientsController {

    @FXML
    private TableView<Animal> displayPatientsTableView;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button patientsButton;
    @FXML
    private Button addPatientButton;

    public void initialize() {

        displayPatientsTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("animalID"));
        displayPatientsTableView.getColumns().get(0).setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center;");
        displayPatientsTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        displayPatientsTableView.getColumns().get(1).setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center;");
        displayPatientsTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("species"));
        displayPatientsTableView.getColumns().get(2).setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center;");

        for (Animal animal : Database.animals.values()) {
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

    // Go to Kennel
    public void kennelButtonPressed(ActionEvent event) throws IOException {
        Parent kennelParent = FXMLLoader.load(getClass().getResource("/Kennel.fxml"));
        Scene kennelScene = new Scene(kennelParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(kennelScene);
        window.show();
    }

    // Add Animal to display list
    public void addAnimalToDisplay(Animal animal) {
        displayPatientsTableView.getItems().add(animal);
    }
}

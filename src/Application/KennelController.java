package Application;

import Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class KennelController {

    @FXML
    private GridPane kennelGridPane;
    @FXML
    private Button kennelViewer;
    @FXML
    private Button patientsButton;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button kennelButton;

    public void initialize() {
        kennelGridPane.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        kennelGridPane.setVgap(10); //vertical gap in pixels
        kennelGridPane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
        //(top/right/bottom/left)
        for (Animal animal : Database.animals.values()) {
            addAnimalToDisplay(animal);
        }
    }

    // Patients Button Pressed
    public void patientsButtonPressed(ActionEvent event) throws IOException {
        Parent patientsParent = FXMLLoader.load(getClass().getResource("/Patients.fxml"));
        Scene patientsScene = new Scene(patientsParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(patientsScene);
        window.show();
    }

    // Return to dashboard
    public void dashboardButtonPressed(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(dashboardScene);
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

    // Add kennel buttons to kennel display.
    private int buttonCount = 0; // start with no buttons

    public void addAnimalToDisplay(Animal animal) {

        if (buttonCount % 4 == 0) { // add new row
            Button button = new Button(animal.getName());
            button.setStyle("-fx-alignment: CENTER; -fx-pref-width: 140;");
            kennelGridPane.addRow((buttonCount / 4), button);
            buttonCount++;
        }
        else { // dont add row, just add to next empty column
            Button button = new Button(animal.getName());
            button.setStyle("-fx-alignment: CENTER; -fx-pref-width: 140; ");

            kennelGridPane.add(button, buttonCount % 4, buttonCount / 4);
            buttonCount++;
        }
    }
}

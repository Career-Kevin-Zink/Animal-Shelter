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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class KennelController {

    @FXML
    private GridPane kennelGridPane;
    @FXML
    private Pane kennelviewPane;
    @FXML
    private Label kennelviewKennelNumberLabel;
    @FXML
    private VBox nameVbox;
    @FXML
    private VBox sexVbox;
    @FXML
    private VBox speciesVbox;
    @FXML
    private VBox breedVbox;
    @FXML
    private VBox idVbox;
    @FXML
    private VBox ageVbox;
    @FXML
    private VBox weightVbox;
    @FXML
    private VBox microChipVbox;
    @FXML
    private GridPane kennelviewGridPane;
    @FXML
    private Button kennelViewer;
    @FXML
    private Button patientsButton;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button kennelButton;
    @FXML
    private Button escapeBtn;
    @FXML
    private VBox arrivalVbox;
    @FXML
    private VBox tempermentVbox;
    @FXML
    private VBox adoptableVbox;
    @FXML
    private VBox colorVbox;

    public void initialize() {
        kennelviewPane.setStyle("-fx-background-color: #000000");
        kennelviewPane.setVisible(false);
        kennelGridPane.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        kennelGridPane.setVgap(10); //vertical gap in pixels
        kennelGridPane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
        //(top/right/bottom/left)
        for (Kennel kennel : Database.kennels.values()) {
            addKennelToDisplay(kennel); // need to split the animals to a new page every 20 kennels
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

    public void addKennelToDisplay(Kennel kennel) {


        String buttonText = kennel.getCurrentAnimal() == null ? "Empty" : kennel.getCurrentAnimal().getName();
        Button button = new Button(buttonText);
        button.setUserData(kennel);
        button.setOnAction(e -> {

            if (kennel.getCurrentAnimal() != null)
                displayKennelViewer((Kennel) button.getUserData());
            else
                System.out.println("No animal in kennel.");
        });
        if (buttonCount % 4 == 0) { // add new row
            kennelGridPane.addRow((buttonCount / 4), button);
            buttonCount++;
        } else { // dont add row, just add to next empty column
            kennelGridPane.add(button, buttonCount % 4, buttonCount / 4);
            buttonCount++;
        }
    }

    public void displayKennelViewer(Kennel kennel) {
        System.out.println(kennel.getKennelID());
        kennelviewKennelNumberLabel.setText("Kennel #" + kennel.getKennelID());
        Label kennelviewLabels[] = {
                new Label("ID: " + kennel.getCurrentAnimal().getAnimalID()),
                new Label("Name: " + kennel.getCurrentAnimal().getName()),
                new Label("Sex: " + kennel.getCurrentAnimal().getSex()),
                new Label("Species: " + kennel.getCurrentAnimal().getSpecies()),
                new Label("Breed: " + kennel.getCurrentAnimal().getBreed()),
                new Label("Color: " + kennel.getCurrentAnimal().getColor()),
                new Label("Age: " + kennel.getCurrentAnimal().getAge()),
                new Label("Weight: " + kennel.getCurrentAnimal().getWeight()),
                new Label("Temperment: " + kennel.getCurrentAnimal().getTemperment()),
                new Label("Microchip #: " + kennel.getCurrentAnimal().getMicrochip()),
                new Label("Arrival Date: " + kennel.getCurrentAnimal().getArrivalDate()),
                new Label("Adoptable: " + (kennel.getCurrentAnimal().getAdoptable().compareTo("True") == 0 ? "Yes" : "No")),
        };


        for (int i = 0; i < 12; i++) {
            kennelviewLabels[i].setStyle("-fx-text-alignment: LEFT; -fx-text-fill: #ffffff;");
        }

        idVbox.getChildren().add(kennelviewLabels[0]);
        nameVbox.getChildren().add(kennelviewLabels[1]);
        colorVbox.getChildren().add(kennelviewLabels[5]);
        sexVbox.getChildren().add(kennelviewLabels[2]);
        speciesVbox.getChildren().add(kennelviewLabels[3]);
        breedVbox.getChildren().add(kennelviewLabels[4]);
        ageVbox.getChildren().add(kennelviewLabels[6]);
        weightVbox.getChildren().add(kennelviewLabels[7]);
        microChipVbox.getChildren().add(kennelviewLabels[9]);
        arrivalVbox.getChildren().add(kennelviewLabels[10]);
        tempermentVbox.getChildren().add(kennelviewLabels[8]);
        adoptableVbox.getChildren().add(kennelviewLabels[11]);

        kennelviewPane.setVisible(true);
    }

    @FXML
    void escapeBtnPressed(ActionEvent event) {

        kennelviewPane.setVisible(false);
        idVbox.getChildren().clear();
        nameVbox.getChildren().clear();
        colorVbox.getChildren().clear();
        sexVbox.getChildren().clear();
        speciesVbox.getChildren().clear();
        breedVbox.getChildren().clear();
        ageVbox.getChildren().clear();
        weightVbox.getChildren().clear();
        microChipVbox.getChildren().clear();
        arrivalVbox.getChildren().clear();
        tempermentVbox.getChildren().clear();
        adoptableVbox.getChildren().clear();
    }
}

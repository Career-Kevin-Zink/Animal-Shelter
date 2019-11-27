package Application;

import java.io.IOException;

import Database.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PatientsController {

  private Database database = new Database();

  @FXML
  private TableView<Animal> displayPatientsTableView;
  @FXML
  private Button managementButton;
  @FXML
  private Button viewAdoptableButton;
  private boolean isViewingAdoptable;

  public void initialize() {
    // Hide the management button if the user is not a manager.
    if (!Database.currentUser.isManager()) {
      managementButton.setVisible(false);
    }

    // Set up the animal display columns
    TableColumn<Animal, String> colID = new TableColumn<>("ID");
    colID.setCellValueFactory(new PropertyValueFactory<>("animalID"));
    colID.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 133.0");

    TableColumn<Animal, String> colName = new TableColumn<>("Name");
    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colName.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 133.0");

    TableColumn<Animal, String> colSpecies = new TableColumn<>("Species");
    colSpecies.setCellValueFactory(new PropertyValueFactory<>("species"));
    colSpecies.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 133.0");
    displayPatientsTableView.getColumns().addAll(colID, colName, colSpecies);

    createOptionsButton();

    for (Animal animal : Database.animals.values()) {
      addAnimalToDisplay(animal);
    }
  }

  @FXML
  void managementButtonPushed(ActionEvent event) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/Management.fxml"));
    Scene scene = new Scene(parent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(scene);
    window.show();
  }

  @FXML
  void dashboardButtonPushed(ActionEvent event) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
    Scene scene = new Scene(parent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(scene);
    window.show();
  }

  @FXML
  void kennelButtonPushed(ActionEvent event) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/Kennel.fxml"));
    Scene scene = new Scene(parent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(scene);
    window.show();
  }

  @FXML
  void patientsButtonPushed(ActionEvent event) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("/Patients.fxml"));
    Scene scene = new Scene(parent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(scene);
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
    displayPatientsTableView.getItems().add(animal);
  }

  // Add options button to patient display
  @FXML
  public void createOptionsButton() {

    TableColumn<Animal, String> options = new TableColumn("Options");

    Callback<TableColumn<Animal, String>, TableCell<Animal, String>> cellFactory
        = new Callback<TableColumn<Animal, String>, TableCell<Animal, String>>() {
      @Override
      public TableCell<Animal, String> call(final TableColumn<Animal, String> param) {
        final TableCell<Animal, String> cell = new TableCell<Animal, String>() {
          private final MenuItem adopt = new MenuItem("Adopt Patient");
          private final MenuItem edit = new MenuItem("Edit Patient");
          private final MenuItem remove = new MenuItem("Remove Patient");
          private final MenuButton optionsBtn = new MenuButton("...", null, adopt, edit, remove);

          {
            adopt.setOnAction(e -> {

              if (displayPatientsTableView.getSelectionModel().getSelectedItem().getAdoptable()
                  .equalsIgnoreCase("True")) {
                database.removePatient(
                    displayPatientsTableView.getSelectionModel().getSelectedItem().getAnimalID()
                    , displayPatientsTableView.getSelectionModel().getSelectedItem().getName());

                database.animals.remove(
                    displayPatientsTableView.getSelectionModel().getSelectedItem().getAnimalID());

                displayPatientsTableView.getItems().clear();
                initialize();

              } else {
                System.out.println(
                    displayPatientsTableView.getSelectionModel().getSelectedItem().getName()
                        + " is not available for adoption.");
              }
            });

            edit.setOnAction(e -> {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddPatient.fxml"));
              Parent addPatientParent = null;
              try {
                addPatientParent = loader.load();

                AddPatientController controller = loader.getController();
                controller.updating(
                    displayPatientsTableView.getSelectionModel().getSelectedItem().getAnimalID());

                Scene addPatientScene = new Scene(addPatientParent);

                Stage window = (Stage) displayPatientsTableView.getScene().getWindow();
                window.setScene(addPatientScene);
                window.show();

              } catch (IOException ex) {
                ex.printStackTrace();
              }
            });

            remove.setOnAction(e -> {
              database.removePatient(
                  displayPatientsTableView.getSelectionModel().getSelectedItem().getAnimalID(),
                  displayPatientsTableView.getSelectionModel().getSelectedItem().getName());

              database.animals.remove(
                  displayPatientsTableView.getSelectionModel().getSelectedItem().getAnimalID());

              displayPatientsTableView.getItems().clear();
              initialize();
            });

            optionsBtn.showingProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                  Boolean newValue) {
                if (newValue == true) {
                  displayPatientsTableView.getSelectionModel().select(getIndex());
                }
              }
            });

            optionsBtn.setStyle(
                "-fx-max-width: 5px; -fx-max-height: 5px; -fx-background-color: transparent;");
          }

          @Override
          public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setGraphic(null);
            } else {
              setGraphic(optionsBtn);
            }
          }
        };
        return cell;
      }
    };

    options.setCellFactory(cellFactory);
    options.setEditable(false);
    options.setSortable(false);
    options.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 100.0;");
    displayPatientsTableView.getColumns().add(options);
  }

  @FXML
  public void viewAdoptableButtonPushed(ActionEvent actionEvent) {

    if (!isViewingAdoptable) {
      displayPatientsTableView.getItems().removeIf(a -> a.getAdoptable().equalsIgnoreCase("false"));
      viewAdoptableButton.setText("View All");
      isViewingAdoptable = true;
    } else {
      displayPatientsTableView.getItems().clear();
      displayPatientsTableView.getItems().addAll(Database.animals.values());
      viewAdoptableButton.setText("View Adoptable");
      isViewingAdoptable = false;
    }

  }
}

package Application;

import Application.TaskManager.AssignedTask;
import Database.Database;
import Database.Employee;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagementController {

  @FXML
  private TableView<Employee> displayEmployeesTableView;

  @FXML
  private TableView<TaskManager.Task> displayTasksTableView;

  @FXML
  private Pane newEmployeePane;

  @FXML
  private Pane viewEmployeesPane;

  @FXML
  private Pane newTaskPane;

  @FXML
  private Pane viewTasksPane;

  @FXML
  private TextField newEmployeeNameField;

  @FXML
  private Label newEmployeeNameLabel;

  @FXML
  private Label newTaskNameLabel;

  @FXML
  private TextField newTaskNameTextField;

  @FXML
  private Label newTaskDescriptionLabel;

  @FXML
  private TextArea newTaskDescriptionTextArea;

  @FXML
  private TextField newEmployeeUsernameField;

  @FXML
  private Label newEmployeeUsernameLabel;

  @FXML
  private TextField newEmployeePasswordField;

  @FXML
  private Label newEmployeePasswordLabel;

  @FXML
  private CheckBox newEmployeeManagerCheckBox;

  @FXML
  private Pane assignTaskPane;

  @FXML
  private ChoiceBox<TaskManager.Task> assignTask;

  @FXML
  private ChoiceBox<Employee> assignEmployee;

  @FXML
  private ChoiceBox<Kennel> assignKennel;

  @FXML
  private ChoiceBox<Animal> assignAnimal;

  @FXML
  private Button assignTaskBtnConfirm;

  @FXML
  private Button assignTaskBtn;

  public void initialize() throws IOException {

    // If for some reason a non manager accessed this page, throw them back to the dashboard.
    if (!Database.currentUser.isManager()) // Should never happen, ever
    {
      dashboardButtonPushed(new ActionEvent());
    }

    newEmployeePane.setVisible(false);
    newTaskPane.setVisible(false);
    assignTaskPane.setVisible(false);
    prepareEmployeeDisplay();
    prepareTaskDisplay();

    // These make it so when you hit escape, it cancels creating a new task or user.
    newEmployeePane.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        newEmployeePane.setVisible(false);
        viewEmployeesPane.setVisible(true);
      }
    });
    newTaskPane.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        newTaskPane.setVisible(false);
        viewTasksPane.setVisible(true);
      }
    });
    assignTaskPane.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        assignTaskPane.setVisible(false);
        viewTasksPane.setVisible(true);
      }
    });

    for (Employee emp : Database.employees.values()) {
      displayEmployeesTableView.getItems().add(emp);
    }
    for (TaskManager.Task task : TaskManager.getTasks()) {
      displayTasksTableView.getItems().add(task);
    }

    for (Employee emp : Database.employees.values()) {
      assignEmployee.getItems().add(emp);
    }
    for (Animal ani : Database.animals.values()) {
      assignAnimal.getItems().add(ani);
    }
    for (Kennel ken : Database.kennels.values()) {
      assignKennel.getItems().add(ken);
    }
    for (TaskManager.Task task : TaskManager.getTasks()) {
      assignTask.getItems().add(task);
    }

  }

  @FXML
  void createNewUserButtonPushed(ActionEvent event) {
    viewEmployeesPane.setVisible(false);
    newEmployeePane.setVisible(true);
  }

  @FXML
  void saveNewUserButtonPushed(ActionEvent event) {
    boolean allFilled = true;

    if (newEmployeeNameField.getText().trim().isEmpty()) {
      allFilled = false;
      newEmployeeNameLabel.setStyle("-fx-text-fill: red;");
    } else {
      newEmployeeNameLabel.setStyle("-fx-text-fill: #ddedf4;");
    }

    if (newEmployeeUsernameField.getText().trim().isEmpty()) {
      allFilled = false;
      newEmployeeUsernameLabel.setStyle("-fx-text-fill: red;");
    } else {
      newEmployeeUsernameLabel.setStyle("-fx-text-fill: #ddedf4;");
    }

    if (newEmployeePasswordField.getText().trim().isEmpty()) {
      allFilled = false;
      newEmployeePasswordLabel.setStyle("-fx-text-fill: red;");
    } else {
      newEmployeePasswordLabel.setStyle("-fx-text-fill: #ddedf4;");
    }

    if (allFilled) {
      Employee emp = new Employee(-1, newEmployeeUsernameField.getText(),
          newEmployeeNameField.getText(), newEmployeeManagerCheckBox.isSelected());
      Database.saveNewEmployee(emp, newEmployeePasswordField.getText());
      displayEmployeesTableView.getItems().add(emp);
      viewEmployeesPane.setVisible(true);
      newEmployeePane.setVisible(false);
    }
  }

  @FXML
  void saveNewTaskButtonPushed(ActionEvent event) {
    boolean allFilled = true;

    if (newTaskNameTextField.getText().trim().isEmpty()) {
      allFilled = false;
      newTaskNameLabel.setStyle("-fx-text-fill: red;");
    } else {
      newTaskNameLabel.setStyle("-fx-text-fill: #ddedf4;");
    }

    if (newTaskDescriptionTextArea.getText().trim().isEmpty()) {
      allFilled = false;
      newTaskDescriptionLabel.setStyle("-fx-text-fill: red;");
    } else {
      newTaskDescriptionLabel.setStyle("-fx-text-fill: #ddedf4;");
    }

    if (allFilled) {
      displayTasksTableView.getItems().add(
          TaskManager
              .createTask(newTaskNameTextField.getText(), newTaskDescriptionTextArea.getText()));
      viewTasksPane.setVisible(true);
      newTaskPane.setVisible(false);
    }
  }

  @FXML
  public void createNewTaskButtonPushed(ActionEvent event) {
    viewTasksPane.setVisible(false);
    newTaskPane.setVisible(true);
  }

  @FXML
  public void assignTaskButtonPushed(ActionEvent actionEvent) {
    viewTasksPane.setVisible(false);
    assignTaskPane.setVisible(true);
  }

  @FXML
  void setAssignTaskBtnConfirm(ActionEvent event) {
    Database
        .saveNewAssignedTask(new AssignedTask(-1, assignTask.getValue(), assignEmployee.getValue(),
            assignKennel.getValue(), assignAnimal.getValue()));
    assignTaskPane.setVisible(false);
    viewTasksPane.setVisible(true);

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


  private void prepareEmployeeDisplay() {
    // Employee TableView
    TableColumn<Employee, String> colID = new TableColumn<>("ID");
    colID.setCellValueFactory(new PropertyValueFactory<>("id"));
    colID.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 150.0");
    colID.setSortable(false);
    colID.setEditable(false);

    TableColumn<Employee, String> colName = new TableColumn<>("Name");
    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colName.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 150.0");
    colName.setSortable(false);
    colName.setEditable(false);

    TableColumn<Employee, String> colUName = new TableColumn<>("Username");
    colUName.setCellValueFactory(new PropertyValueFactory<>("username"));
    colUName.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 150.0");
    colUName.setSortable(false);
    colUName.setEditable(false);

    TableColumn<Employee, String> colManager = new TableColumn<>("Manager");
    colManager.setCellValueFactory(cellData -> {
      boolean isManager = cellData.getValue().isManager();
      String isManagerStr;
      if (isManager) {
        isManagerStr = "Yes";
      } else {
        isManagerStr = "No";
      }
      return new ReadOnlyStringWrapper(isManagerStr);
    });
    colManager.setCellFactory(col -> new TableCell<Employee, String>() {
      @Override
      protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? null : item);
      }
    });
    colManager.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 150.0");
    colManager.setEditable(false);
    colManager.setSortable(false);

    displayEmployeesTableView.getColumns().addAll(colID, colName, colUName, colManager);
  }

  private void prepareTaskDisplay() {
    // Tasks TableView
    TableColumn<TaskManager.Task, Integer> colID = new TableColumn<>("ID");
    colID.setCellValueFactory(new PropertyValueFactory<>("taskID"));
    colID.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 50.0");
    colID.setSortable(false);
    colID.setEditable(false);

    TableColumn<TaskManager.Task, String> colName = new TableColumn<>("Task Name");
    colName.setCellValueFactory(new PropertyValueFactory<>("taskName"));
    colName.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 100.0");
    colName.setSortable(false);
    colName.setEditable(false);

    TableColumn<TaskManager.Task, String> colDesc = new TableColumn<>("Task Description");
    colDesc.setCellValueFactory(new PropertyValueFactory<>("taskDescription"));
    colDesc.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 450.0");
    colDesc.setSortable(false);
    colDesc.setEditable(false);

//        TableColumn<TaskManager.Task, String> colAssigned = new TableColumn<>("Assigned tasks.");
//        colDesc.setCellValueFactory(new PropertyValueFactory<>("assignments"));
//        colDesc.setStyle("-fx-text-fill: #ddedf4; -fx-alignment: center; -fx-pref-width: 100.0");
//        colDesc.setSortable(false);
//        colDesc.setEditable(false);

    //displayTasksTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    displayTasksTableView.getColumns().addAll(colID, colName, colDesc);
  }


}
package Application;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagementController {

    @FXML
    private TableView<Employee> displayEmployeesTableView;

    @FXML
    private Pane newEmployeePane;

    @FXML
    private Pane managementPane;

    @FXML
    private TextField newEmployeeNameField;

    @FXML
    private Label newEmployeeNameLabel;

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

    public void initialize() throws IOException {

        // If for some reason a non manager accessed this page, throw them back to the dashboard.
        if (!Database.currentUser.isManager()) // Should never happen, ever
            dashboardButtonPushed(new ActionEvent());

        newEmployeePane.setVisible(false);
        setupEmployeeDisplay();

        for (Employee emp : Database.employees.values()) {
            addEmployeeToDisplay(emp);
        }
    }

    public void addEmployeeToDisplay(Employee employee) {
        displayEmployeesTableView.getItems().add(employee);
    }

    @FXML
    void createNewUserButtonPushed(ActionEvent event) {
        managementPane.setVisible(false);
        newEmployeePane.setVisible(true);
    }

    @FXML
    void saveNewUserButtonPushed(ActionEvent event) {
        boolean allFilled = true;

        if (newEmployeeNameField.getText().trim().isEmpty()) {
            allFilled = false;
            newEmployeeNameLabel.setStyle("-fx-text-fill: red;");
        } else newEmployeeNameLabel.setStyle("-fx-text-fill: #ddedf4;");

        if (newEmployeeUsernameField.getText().trim().isEmpty()) {
            allFilled = false;
            newEmployeeUsernameLabel.setStyle("-fx-text-fill: red;");
        } else newEmployeeUsernameLabel.setStyle("-fx-text-fill: #ddedf4;");

        if (newEmployeePasswordField.getText().trim().isEmpty()) {
            allFilled = false;
            newEmployeePasswordLabel.setStyle("-fx-text-fill: red;");
        } else newEmployeePasswordLabel.setStyle("-fx-text-fill: #ddedf4;");

        if (allFilled) {
            Employee emp = new Employee(-1, newEmployeeUsernameField.getText(),
                    newEmployeeNameField.getText(), newEmployeeManagerCheckBox.isSelected());
            Database.saveNewEmployee(emp, newEmployeePasswordField.getText());
            addEmployeeToDisplay(emp);
            managementPane.setVisible(true);
            newEmployeePane.setVisible(false);
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

    private void setupEmployeeDisplay() {
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
            if (isManager) isManagerStr = "Yes";
            else isManagerStr = "No";
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
}
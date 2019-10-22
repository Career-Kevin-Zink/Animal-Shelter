package Application;

import Database.Database;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DashboardController {

    @FXML
    private Label dashboardWelcomeLabel;
    @FXML
    private Label loginFailLabel;
    @FXML
    private Button patientsButton;
    @FXML
    private Button dashboardButton;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Pane loginPane;

    public void initialize() {
        buttonBar.setVisible(false);
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

    @FXML // attempt to login
    public void loginButtonPushed(ActionEvent event) {
        // The login system has no influence over access right now. Right now it is just visual.
        if (loginUsernameField.getText().trim().isEmpty() || loginPasswordField.getText().trim().isEmpty()) {
            doLoginErrorAnim();
            return;
        } else {
            if (Database.tryLogin(loginUsernameField.getText(), loginPasswordField.getText())) {
                dashboardWelcomeLabel.setText(String.format("Welcome, %s!", Database.currentUser.getName()));
                loginPane.setVisible(false);
                buttonBar.setVisible(true);
                dashboardWelcomeLabel.setVisible(true);
                if (Database.currentUser.isManager()) {
                    MenuButton btn = new MenuButton("Management");
                    btn.setStyle("-fx-background-color: transparent;");
                    btn.getItems().addAll(
                            new MenuItem("Create New Employee"),
                            new MenuItem("View All Employees"),
                            new MenuItem("Create New Task"),
                            new MenuItem("Assign Tasks")
                    );
                    buttonBar.getButtons().add(0, btn);
                }

            } else {
                doLoginErrorAnim();
            }
        }
    }

    private void doLoginErrorAnim() {
        loginFailLabel.setVisible(true);
        Timeline delay = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                loginFailLabel.setVisible(false);
            }
        }));
        delay.setCycleCount(1);
        delay.play();
    }
}

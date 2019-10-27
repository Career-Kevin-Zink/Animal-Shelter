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
    private Button managementButton;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Pane loginPane;

    public void initialize() {
        if(Database.currentUser == null)
            buttonBar.setVisible(false);
        else {
            dashboardWelcomeLabel.setText(String.format("Welcome, %s!", Database.currentUser.getName()));
            loginPane.setVisible(false);
            buttonBar.setVisible(true);
            // Hide the management button if the user is not a manager.
            if (!Database.currentUser.isManager())
                managementButton.setVisible(false);
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
                // Hide the management button if the user is not a manager.
                if (!Database.currentUser.isManager())
                    managementButton.setVisible(false);
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

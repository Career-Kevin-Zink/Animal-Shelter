package Application;

import Database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Animal Shelter Management App");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);
        Database.loadAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

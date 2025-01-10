package eu.telecomnancy.codingweek.codenames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import eu.telecomnancy.codingweek.codenames.music.Music;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Tackle music
        URL fxmlURL = getClass().getResource("/views/root.fxml");
        if (fxmlURL == null) {
            System.err.println("Could not find root.fxml");
            System.exit(1);
        }
        Parent root = FXMLLoader.load(fxmlURL);

        Scene scene = new Scene(root, 900, 760);
        primaryStage.setMinHeight(760);
        primaryStage.setMinWidth(900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Codenames for Coding Week");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

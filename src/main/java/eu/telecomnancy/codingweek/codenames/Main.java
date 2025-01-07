package eu.telecomnancy.codingweek.codenames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Tackle music
        String musicFile = getClass().getResource("/music/apt.mp3").toString();
        try{
            Media media = new Media(musicFile);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (IllegalArgumentException e) {
            // Handle invalid file path or unsupported format
            System.out.println("Error: The media file could not be loaded. Check the file path or format.");
            e.printStackTrace();

        } catch (Exception e) {
            // Handle other general exceptions (e.g., issues during playback)
            System.out.println("An error occurred while trying to play the media.");
            e.printStackTrace();
        }
        

        URL fxmlURL = getClass().getResource("/views/root.fxml");
        if (fxmlURL == null) {
            System.err.println("Could not find root.fxml");
            System.exit(1);
        }
        Parent root = FXMLLoader.load(fxmlURL);

        Scene scene = new Scene(root, 900, 760);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Codenames for Coding Week");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

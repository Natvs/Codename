package eu.telecomnancy.codingweek.codenames.controller;

import java.io.File;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.utils.SaveFile;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;


public class HomeController {
    private final Session session;

    private boolean isMuted = true;

    @FXML
    private GridPane homeView;

    @FXML 
    private ImageView muteImage;

    public HomeController(Session session) {
        this.session = session;
    }

    @FXML
    public void initialize() {
        setEvents();
    }

    private void setEvents() {
        homeView.setOnKeyPressed((keyevent) -> {
            switch (keyevent.getCode()) {
                case Q -> onQuit();
                case N -> onNewGame();
                case C -> onLoadGame();
                default -> {}
            }
        });
    }

    @FXML
    private void onNewGame() {
        RootController.getInstance().changeView("/views/newConfig.fxml");
    }   

    @FXML
    private void onLoadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir une sauvegarde");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier JSON", "*.json"));
        File file = fileChooser.showOpenDialog(homeView.getScene().getWindow());
        if (file != null) {
            this.session.clone(SaveFile.loadData(file.getAbsolutePath()));
            RootController.getInstance().changeView("/views/game.fxml");
        }
    }

    @FXML
    private void onQuit() {
        System.exit(0);
    }

    @FXML
    private void onCredits() {
        RootController.getInstance().changeView("/views/credit.fxml");
    }

    @FXML
    private void onMute() {
        if (isMuted) {
            muteImage.setImage(new Image(getClass().getResource("/images/sound-high-svgrepo-com.png").toString()));
            session.getMusic().unMute();
            isMuted = false;
        } else {
            muteImage.setImage(new Image(getClass().getResource("/images/sound-mute-svgrepo-com.png").toString()));
            isMuted = true;
            session.getMusic().mute();
        }
    }
}

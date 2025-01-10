package eu.telecomnancy.codingweek.codenames.controller;

import java.io.File;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.utils.SaveFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;


public class HomeController {
    private Session session;

    @FXML
    private GridPane homeView;

    @FXML
    private Button newGameButton;

    @FXML
    private Button quitButton;

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
            Session session = SaveFile.loadData(file.getAbsolutePath());
            this.session.clone(session);
            RootController.getInstance().changeView("/views/game.fxml");
        }
    }

    @FXML
    private void onQuit() {
        System.exit(0);
    }
}

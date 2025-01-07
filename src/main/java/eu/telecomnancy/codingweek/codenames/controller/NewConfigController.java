package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.GameConfig;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class NewConfigController {

    @FXML
    private GridPane mainPane;
    @FXML
    private Button startButton;
    @FXML
    private ComboBox<String> thematicSelection;

    private GameConfig config = new GameConfig();

    private Boolean startEnable = false;

    @FXML
    private void initialize() {
        mainPane.setOnKeyPressed((keyevent) -> {
            switch (keyevent.getCode()) {
                case KeyCode.Q:
                    onBack();
                    break;
                case KeyCode.S:
                    if (startEnable) { onStart(); }
                    break;
            }
        });
        disableStart();
        thematicSelection.getItems().addAll("Tout", "Patate", "Entropie");
    }

    @FXML
    private void onBack() {
        RootController.getInstance().changeView("/views/home.fxml");
    }

    @FXML
    private void onStart() {
        var session = new Session(config);
        RootController.getInstance().setGameView("/views/game.fxml", session);
    }

    @FXML
    private void onThematicSelection() {
        enableStart();
    }

    private void disableStart() {
        startButton.setDisable(true);
        startEnable = false;
    }
    private void enableStart() {
        startButton.setDisable(false);
        startEnable = true;
    }

}

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
    private GridPane newConfigView;
    @FXML
    private Button startButton;
    @FXML
    private ComboBox<String> thematicSelection;

    private Boolean startEnable = false;

    @FXML
    private void initialize() {
        newConfigView.setOnKeyPressed((keyevent) -> {
            switch (keyevent.getCode()) {
                case KeyCode.Q:
                    onBack();
                    break;
                case KeyCode.S:
                    if (startEnable) { onStart(); }
                    break;
                default:
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
        RootController.getInstance().changeView("/views/game.fxml");
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

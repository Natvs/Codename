package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class NewConfigController {
    @FXML
    private Button startButton;

    @FXML
    private ComboBox<String> thematicSelection;

    @FXML
    private void initialize() {
        startButton.setDisable(true);
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
        startButton.setDisable(false);
    }
}

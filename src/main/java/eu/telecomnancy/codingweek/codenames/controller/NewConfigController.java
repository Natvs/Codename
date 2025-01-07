package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.awt.event.KeyEvent;
import java.beans.EventHandler;

public class NewConfigController {

    @FXML
    private GridPane mainPane;
    @FXML
    private Button startButton;

    @FXML
    private ComboBox<String> thematicSelection;

    @FXML
    private void initialize() {
        mainPane.setOnKeyPressed((keyevent) -> {
            System.out.println("Key pressed : " + keyevent.getCode());
            switch (keyevent.getCode()) {
                case KeyCode.Q:
                    RootController.getInstance().changeView("/views/home.fxml");
                    break;
                case KeyCode.S:
                    RootController.getInstance().changeView("/views/game.fxml");
                    break;
            }
        });
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

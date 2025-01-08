package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PlayerFieldController {
    private String name;
    private NewConfigController newConfigController;
    private int column;

    @FXML
    private HBox playerField;

    @FXML
    private TextField playerName;

    public PlayerFieldController(String name, NewConfigController newConfigController, int column) {
        this.name = name;
        this.newConfigController = newConfigController;
        this.column = column;
    }

    @FXML
    private void initialize() {
        playerName.setText(name);
    }

    @FXML
    private String getName() {
        return playerName.getText();
    }

    @FXML
    private void onRemovePlayer() {
        newConfigController.removePlayerField(playerField, column);
    }
}


package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PlayerFieldController {
    private String name;
    private NewConfigController newConfigController;
    private int playerType;

    @FXML
    private HBox playerField;

    @FXML
    private TextField playerName;

    public PlayerFieldController(String name, NewConfigController newConfigController, int playerType) {
        this.name = name;
        this.newConfigController = newConfigController;
        this.playerType = playerType;
    }

    @FXML
    private void initialize() {
        playerName.setText(name);
    }

    @FXML
    private void onRemovePlayer() {
        newConfigController.removePlayerField(playerField, playerType);
    }
}


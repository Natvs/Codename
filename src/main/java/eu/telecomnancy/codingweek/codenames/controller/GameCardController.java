package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameCardController {
    private String wordS;
    
    @FXML
    private Label word;

    public GameCardController(String word) {
        this.wordS = word;
    }

    @FXML
    private void initialize() {
        word.setText(wordS);
    }
}

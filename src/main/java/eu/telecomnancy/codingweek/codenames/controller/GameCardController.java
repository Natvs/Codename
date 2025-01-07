package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameCardController {
    private String wordS;

    @FXML
    private GridPane gameCard;
    
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

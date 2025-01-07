package eu.telecomnancy.codingweek.codenames.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class GameCardController {
    private String wordS;

    @FXML
    private GridPane mainPane;
    @FXML
    private Label word;

    public GameCardController(String word) {
        this.wordS = word;
    }

    @FXML
    private void initialize() {
        word.setText(wordS);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        System.out.println("Clicked");
    }
}

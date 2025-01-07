package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameCardController {
    @FXML
    private GridPane gameCard;
    
    @FXML
    private Label word;

    private Card card;

    public GameCardController(Card card) {
        this.card = card;
    }

    @FXML
    private void initialize() {
        word.setText(card.getName());
    }
}

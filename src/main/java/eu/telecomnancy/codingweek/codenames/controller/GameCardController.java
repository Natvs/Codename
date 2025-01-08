package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.observers.board.CardColorObserver;
import eu.telecomnancy.codingweek.codenames.observers.board.CardNameObserver;
import eu.telecomnancy.codingweek.codenames.observers.board.CardRevealedObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameCardController {
    @FXML
    private GridPane gameCard;
    
    @FXML
    private Label word;

    private final Card card;

    public GameCardController(Card card) {
        this.card = card;
    }

    @FXML
    private void initialize() {
        setName();
        setColor();
        card.setColorObserver(new CardColorObserver(this));
        card.setNameObserver(new CardNameObserver(this));
        card.setRevealedObserver(new CardRevealedObserver(this));
    }

    public void setName() {
        word.setText(card.getName());
    }

    public void setColor() {
        if (card.getRevealed()) {
            switch (card.getColor()) {
                case Color.RED:
                    gameCard.setStyle("-fx-background-color: #c80000");
                    break;
                case Color.BLUE:
                    gameCard.setStyle("-fx-background-color: #0084ff");
                    break;
                case Color.WHITE:
                    gameCard.setStyle("-fx-background-color: #FFFFFF");
                    break;
                case Color.BLACK:
                    gameCard.setStyle("-fx-background-color: #000000");
                    break;
                default:
                    break;
            }
        }
    }
}

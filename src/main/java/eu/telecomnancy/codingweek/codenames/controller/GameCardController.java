package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.observers.board.CardColorObserver;
import eu.telecomnancy.codingweek.codenames.observers.board.CardNameObserver;
import eu.telecomnancy.codingweek.codenames.observers.board.CardRevealedObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameCardController {
    @FXML
    private GridPane gameCard;
    
    @FXML
    private Label word;

    @FXML
    private ImageView imageView;

    private Card card;
    private Session session;

    public GameCardController(Card card,Session session) {
        this.card = card;
        this.session = session;
    }

    @FXML
    private void initialize() {
        setCard();
        setColor();
        card.setColorObserver(new CardColorObserver(this));
        card.setNameObserver(new CardNameObserver(this));
        card.setRevealedObserver(new CardRevealedObserver(this));
    }

    public void setCard() {
        if (session.getConfig().getImageMode()) {
            word.setManaged(false);
            imageView.setImage(new Image(card.getImageURL()));
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
        } else {
            word.setText(card.getName());
            imageView.setManaged(false);
        }
    }

    public void setColor() {
        if (card.getRevealed()) {
            switch (card.getColor()) {
                case RED -> gameCard.setId("gameCard-red");
                case BLUE -> gameCard.setId("gameCard-blue");
                case WHITE -> gameCard.setId("gameCard-white");
                case BLACK -> gameCard.setId("gameCard-black");
                default -> {}
            }
        }
        else if (session.isAgent()) {
            switch (card.getColor()) {
                case RED -> gameCard.setId("gameCard-red-light");
                case BLUE -> gameCard.setId("gameCard-blue-light");
                case WHITE -> gameCard.setId("gameCard-white-light");
                case BLACK -> gameCard.setId("gameCard-black-light");
                default -> {}
            }
        }
    }

}

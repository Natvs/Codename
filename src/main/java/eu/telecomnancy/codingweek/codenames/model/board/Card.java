package eu.telecomnancy.codingweek.codenames.model.board;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.observers.board.CardColorObserver;
import eu.telecomnancy.codingweek.codenames.observers.board.CardNameObserver;
import eu.telecomnancy.codingweek.codenames.observers.board.CardRevealedObserver;

public class Card {
    
    private String name;
    private Color color;
    private boolean revealed;

    private CardColorObserver colorObserver = null;
    private CardNameObserver nameObserver = null;
    private CardRevealedObserver revealedObserver = null;

    public Card(String name, Color color) {
        setName(name);
        setColor(color);
        hide();
    }
    public Card(@JsonProperty("name") String name,@JsonProperty("color") Color color, @JsonProperty("revelead") boolean revealed){
        this.name = name;
        this.color = color;
        this.revealed = revealed;
    }

    public String getName() {
        return this.name;
    }
    public Color getColor() {
        return this.color;
    }
    public Boolean getRevealed() {
        return this.revealed;
    }

    public void setName(String name) {
        this.name = name;
        if (nameObserver != null) {
            nameObserver.handle();
        }
    }
    public void setColor(Color color) {
        this.color = color;
        if (colorObserver != null) {
            colorObserver.handle();
        }
    }
    public void show() {
        this.revealed = true;
    }
    public void hide() {
        this.revealed = false;
    }

    public void setColorObserver(CardColorObserver observer) {
        this.colorObserver = observer;
    }
    public void setNameObserver(CardNameObserver observer) {
        this.nameObserver = observer;
    }
    public void setRevealedObserver(CardRevealedObserver observer) {
        this.revealedObserver = observer;
    }

}

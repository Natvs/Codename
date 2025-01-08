package eu.telecomnancy.codingweek.codenames.model.board;

import eu.telecomnancy.codingweek.codenames.model.color.Color;

public class Card {
    
    private String name;
    private Color color;
    private Boolean revealed;

    public Card(String name, Color color) {
        this.name = name;
        this.color = color;
        this.revealed = false;
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

    public void show() {
        this.revealed = true;
    }

    public void hide() {
        this.revealed = false;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

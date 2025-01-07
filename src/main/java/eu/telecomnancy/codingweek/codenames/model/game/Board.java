package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.model.card.Card;

public class Board {
    
    private int width;
    private int height; 
    private Card[][] grid;

    public Board(int width, int height, Card[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Card getCard(int i, int j) {
        return this.grid[i][j];
    }

}

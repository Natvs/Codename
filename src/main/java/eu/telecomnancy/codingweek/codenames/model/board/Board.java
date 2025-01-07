package eu.telecomnancy.codingweek.codenames.model.board;

import eu.telecomnancy.codingweek.codenames.utils.openCardsService;

public class Board {
    
    private int width;
    private int height; 
    private Card[][] grid;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = openCardsService.initGridCards(width, height);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getNumberCards() {
        return this.width * this.height;
    }

    public Card[][] getGrid() {
        return this.grid;
    }

    public Card getCard(int i, int j) {
        return this.grid[i][j];
    }

}

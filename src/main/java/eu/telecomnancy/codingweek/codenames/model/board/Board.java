package eu.telecomnancy.codingweek.codenames.model.board;

import eu.telecomnancy.codingweek.codenames.utils.openCardsService;

public class Board {
    
    private int length;
    private int width; 
    private Card[][] grid;

    public Board(int length, int width) {
        this.length = length;
        this.width = width;
        this.grid = openCardsService.initGridCards(length, width);
    }

    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    public int getNumberCards() {
        return this.length * this.width;
    }

    public Card[][] getGrid() {
        return this.grid;
    }

    public Card getCard(int i, int j) {
        return this.grid[i][j];
    }

}

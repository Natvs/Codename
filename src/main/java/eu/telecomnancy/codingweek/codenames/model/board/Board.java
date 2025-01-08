package eu.telecomnancy.codingweek.codenames.model.board;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.telecomnancy.codingweek.codenames.utils.openCardsService;

public class Board {
    
    private int heigth;
    private int width; 
    private final Card[][] grid;

    public Board(@JsonProperty("height") int height,@JsonProperty("width") int width) {
        this.heigth = height;
        this.width = width;
        this.grid = openCardsService.initGridCards(height, width);
    }

    public void setSize(int width, int heigth) {
        this.heigth = heigth;
        this.width = width;
    }

    public int getHeigth() {
        return this.heigth;
    }

    public int getWidth() {
        return this.width;
    }

    public Card[][] getGrid() {
        return this.grid;
    }

    public Card getCard(int i, int j) {
        return this.grid[i][j];
    }

}

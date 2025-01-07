package eu.telecomnancy.codingweek.codenames.model.board;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public Board(@JsonProperty("length") int length,@JsonProperty("grid") Card[][] grid,@JsonProperty("width") int width){
        this.length = length;
        this.width = width;
        this.grid = grid;
    }
    public int getLength() {
        return this.length;
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

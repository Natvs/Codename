package eu.telecomnancy.codingweek.codenames.model.board;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;

public class Board {
    
    private int heigth;
    private int width; 
    private Card[][] grid;

    public Board(int length, int width) {
        this.heigth = length;
        this.width = width;
        this.grid = openCardsService.initGridCards(length, width);
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

    public int getNumberCards() {
        return this.heigth * this.width;
    }

    public Card[][] getGrid() {
        return this.grid;
    }

    public Card getCard(int i, int j) {
        return this.grid[i][j];
    }

    public int getRemainingCards(Color color){
        int number = 0;
        for (int j = 0; j < heigth; j++) {
            for (int i = 0; i < width; i++) {
                if (grid[i][j].getColor() == color) {
                    number ++;
                }
            }
        }
        return number;
    }

}

package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.model.card.Card;
import eu.telecomnancy.codingweek.codenames.model.color.Color;

public class Board {
    
    private int width = 5;
    private int height = 5;
    private Card[][] grid;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    
        // Initialisation de la Grille
        this.grid = new Card[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String name = null; // A_REVOIR
                grid[i][j] = new Card(name, Color.WHITE);
            }
        }

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

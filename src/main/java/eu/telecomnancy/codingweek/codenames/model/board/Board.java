package eu.telecomnancy.codingweek.codenames.model.board;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;

public class Board {
    
    private int heigth;
    private int width; 
    private Card[][] grid;
    @JsonIgnore
    private final ArrayList<String> fullWordList;
    private ArrayList<String> wordsList;

    // Ajouter constructeur par défaut pour Jackson
    public Board() {
        // Initialisation par défaut
        this.wordsList = new ArrayList<>();
        this.fullWordList = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        this.grid = new Card[5][5];
    }

    public Board(@JsonProperty("height") int height,@JsonProperty("width") int width) {
        this.heigth = height;
        this.width = width;
        wordsList = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        fullWordList = new ArrayList<>(wordsList);
        this.grid = openCardsService.initGridCards(height, width, wordsList);
    }
    
    public void clone(Board target) {
        this.heigth = target.getHeigth();
        this.width = target.getWidth();
        this.grid = target.getGrid();
    }

    public void setSize(int width, int heigth) {
        this.heigth = heigth;
        this.width = width;
        this.grid = openCardsService.initGridCards(heigth, width, wordsList);
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

    public void setGrid(Card[][] grid) {
        this.grid = grid;
    }

    public Card getCard(int i, int j) {
        return this.grid[i][j];
    }

    public ArrayList<String> getFullWordList() {
        return this.fullWordList;
    }

    public ArrayList<String> getWordList() {
        return this.wordsList;
    }

    @JsonIgnore
    public ArrayList<String> getBoardWordList() {
        ArrayList<String> boardWordsList = new ArrayList<>();
        for (int j = 0; j < heigth; j++) {
            for (int i = 0; i < width; i++) {
                boardWordsList.add(grid[i][j].getName());
            }
        }
        return boardWordsList;
    }

    public int getRemainingCards(Color color){
        int number = 0;
        for (int j = 0; j < heigth; j++) {
            for (int i = 0; i < width; i++) {
                if (grid[i][j].getColor() == color && !grid[i][j].getRevealed()) {
                    number ++;
                }
            }
        }
        return number;
    }

    public void setWordList(ArrayList<String> words) {
        this.wordsList = words;
        this.grid = openCardsService.initGridCards(heigth, width, wordsList);
    }
}

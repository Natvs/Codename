package eu.telecomnancy.codingweek.codenames.model.team;

public class Clue {
    
    private int wordId;
    private int count;
    
    public Clue(int wordId, int count) {
        this.wordId = wordId;
        this.count = count;
    }

    public int getWordId() {
        return this.wordId;
    }

    public int getCount() {
        return this.count;
    }

}

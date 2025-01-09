package eu.telecomnancy.codingweek.codenames.model.clue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Clue {
    
    private String word;
    private int count;
    
    @JsonCreator
    public Clue(@JsonProperty("word") String word, 
                @JsonProperty("number") int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return this.word;
    }

    public int getCount() {
        return this.count;
    }

    public void countDown() {
        this.count --;
    }

}

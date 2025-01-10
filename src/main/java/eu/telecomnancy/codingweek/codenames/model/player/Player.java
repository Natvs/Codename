package eu.telecomnancy.codingweek.codenames.model.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    
    private final String name;

    public Player(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}

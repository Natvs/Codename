package eu.telecomnancy.codingweek.codenames.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    
    private List<Player> playersList = new ArrayList<>();

    public Team(List<Player> playersList) {
        this.playersList = playersList;
    }

    public List<Player> getPlayersList() {
        return this.playersList;
    }

}

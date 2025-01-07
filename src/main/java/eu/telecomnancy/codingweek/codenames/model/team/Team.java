package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class Team {
    
    private List<Player> playersList;
    private List<Clue> cluesList;

    public Team(List<Player> playersList) {
        this.playersList = playersList;
        this.cluesList = new ArrayList<>();
    }

    public List<Player> getPlayersList() {
        return this.playersList;
    }

    public List<Clue> getCluesList() {
        return this.cluesList;
    }

}

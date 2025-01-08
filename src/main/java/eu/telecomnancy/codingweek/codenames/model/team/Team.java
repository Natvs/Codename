package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public abstract class Team {
    
    private List<Player> playersList;
    private List<Clue> cluesList;

    public Team(List<Player> playersList, List<Clue> cluesList) {
        this.playersList = playersList;
        this.cluesList = cluesList;
    }

    public List<Player> getPlayersList() {
        return this.playersList;
    }

    public List<Clue> getCluesList() {
        return this.cluesList;
    }

}

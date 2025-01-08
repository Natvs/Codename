package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.player.Player;

public abstract class Team {
    
    private List<Player> playersList;

    public Team(List<Player> playersList) {
        this.playersList = playersList;
    }

    public List<Player> getPlayersList() {
        return this.playersList;
    }

}

package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class AgentTeam extends Team {

    public AgentTeam(List<Player> playersList) {
        super(playersList);
    }

    public void addClue(Clue clue) {
        this.getCluesList().add(clue);
    }

}

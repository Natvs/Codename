package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.player.Player;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;

public class AgentTeam extends Team {

    public AgentTeam(List<Player> playersList) {
        super(playersList);
    }

    public void addClue(Clue clue) {
        this.getCluesList().add(clue);
    }

}

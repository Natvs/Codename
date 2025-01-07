package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class AgentTeam extends Team {

    public AgentTeam(List<Player> playersList, List<Clue> cluesList) {
        super(playersList, cluesList);
    }

    public void addClue(Clue clue) {
        this.getCluesList().add(clue);
    }

}

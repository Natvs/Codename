package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class AgentTeam extends Team {

    public AgentTeam(@JsonProperty("playersList") List<Player> playersList,@JsonProperty("cluesList") List<Clue> cluesList) {
        super(playersList, cluesList);
    }
    public void addClue(Clue clue) {
        this.getCluesList().add(clue);
    }

}

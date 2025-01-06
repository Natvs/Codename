package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class AgentTeam extends Team {

    public AgentTeam(Color color, List<Player> playersList, Session session) {
        super(color, playersList, session);
    }

    public void addClue(Clue clue) {
        // A_REVOIR
    }

}

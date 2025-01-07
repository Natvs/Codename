package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class SpyTeam extends Team{
    
    public SpyTeam(List<Player> playersList, List<Clue> cluesList) {
        super(playersList, cluesList);
    }

    public void guess(Card card) {
        // A_REVOIR
    }

}

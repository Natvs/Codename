package eu.telecomnancy.codingweek.codenames.ia;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.codingweek.codenames.ia.algo.ClueGuesser;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.game.Session;


public class ClueGuesserTest {
    @Test
    void testCardGuesser() {

        // Test CardGuesser
        Session session = new Session();
        session.getBoard().setSize(5, 5);
        
        ClueGuesser ia = new ClueGuesser(session);
        Clue clue = ia.getClue();
        System.out.print(clue);
    }

}

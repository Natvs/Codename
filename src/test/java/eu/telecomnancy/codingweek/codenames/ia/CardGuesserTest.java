package eu.telecomnancy.codingweek.codenames.ia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.ia.algo.CardGuesser;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.board.Card;


public class CardGuesserTest {
    @Test
    void testCardGuesser() {

        // Test CardGuesser
        Session session = new Session();
        session.getBoard().setSize(5, 5);
        Clue clue = new Clue("abeille", 3);
        session.getCurrentColoredTeam().getCluesList().add(clue);
        
        CardGuesser ia = new CardGuesser(session);
        Card[] result = ia.getResult();
        assertNotEquals(null, result);
        assertEquals(clue.getCount(), result.length);
    }

}

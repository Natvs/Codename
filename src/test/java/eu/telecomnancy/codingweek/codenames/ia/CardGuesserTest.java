package eu.telecomnancy.codingweek.codenames.ia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.ia.algo.CardGuesser;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.board.Card;


public class CardGuesserTest {
    @Test
    void testCardGuesser() {

        // Config Session
        Session session = new Session();
        Integer height = 2;
        Integer width = 3;
        session.getBoard().setSize(width, height);
        Card[][] grid = new Card[height][width];
        grid[0][0] = new Card("miel", Color.BLUE);
        grid[0][1] = new Card("œil", Color.WHITE);
        grid[0][2] = new Card("tricycle", Color.RED);
        grid[1][0] = new Card("guêpe", Color.BLUE);
        grid[1][1] = new Card("jouet", Color.BLACK);
        grid[1][2] = new Card("santé", Color.WHITE);
        session.getBoard().setGrid(grid);
        // Créer un indice "Clue"
        Clue clue = new Clue("abeille", 2);
        session.getCurrentColoredTeam().getCluesList().add(clue);
        // Test CardGuesser
        CardGuesser ia = new CardGuesser(session);
        Card[] result = ia.getResult();
        assertNotEquals(null, result);
        assertEquals(clue.getCount(), result.length);
    }

}

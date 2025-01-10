package eu.telecomnancy.codingweek.codenames.ia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.codingweek.codenames.ia.algo.ClueGuesser;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;


public class ClueGuesserTest {
    @Test
    void testCardGuesser() {

        // Config Session
        Session session = new Session();
        Integer width = 2;
        Integer height = 3;
        session.getBoard().setSize(width, height);
        Card[][] grid = new Card[width][height];
        grid[0][0] = new Card("miel", Color.BLUE);
        grid[0][1] = new Card("œil", Color.WHITE);
        grid[0][2] = new Card("tricycle", Color.RED);
        grid[1][0] = new Card("guêpe", Color.BLUE);
        grid[1][1] = new Card("jouet", Color.BLACK);
        grid[1][2] = new Card("santé", Color.WHITE);
        session.getBoard().setGrid(grid);
        // Test CardGuesser
        ClueGuesser ia = new ClueGuesser(Color.BLUE, session);
        Clue clue = ia.getClue();
        Clue clue_expected = new Clue("abeille", 2);
        assertEquals(clue_expected, clue);
    }

}

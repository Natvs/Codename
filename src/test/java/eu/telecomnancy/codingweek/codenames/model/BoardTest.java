package eu.telecomnancy.codingweek.codenames.model;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void testRemainingCards() {
        Session session = new Session();
        Board board = session.getBoard();
        Card[][] grid = board.getGrid();
        int heigth = board.getHeigth();
        int width = board.getWidth();
        for (int j = 0; j < heigth; j++) {
            for (int i = 0; i < width; i++) {
                if (grid[i][j].getColor() == Color.BLUE) {
                    grid[i][j].reveal();
                }
            }
        }
        assertEquals(0, board.getRemainingCards(Color.BLUE));
        assertNotEquals(0, board.getRemainingCards(Color.RED));
    }
}

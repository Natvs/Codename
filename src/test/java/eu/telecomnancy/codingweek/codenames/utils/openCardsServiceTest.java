package eu.telecomnancy.codingweek.codenames.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.card.Card;
import org.junit.jupiter.api.Test;


public class openCardsServiceTest {
    private static ArrayList<String> cardsList; 
    private static ArrayList<Card> choosenCards;
    
    @BeforeAll
    static void configTest() {
        String fileName = new String("src/test/resources/words/test_words.txt");
        cardsList = openCardsService.openFile(fileName);
    }
    
    @Test
    void testOpenFile() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Afrique");
        list.add("Aiguille");
        list.add("Aile");
        list.add("Air");
        list.add("Alien");
        list.add("Allemagne");
        list.add("Alpes");
        list.add("Amour");
        list.add("Ampoule");
        list.add("Amérique");
        for (String elt:cardsList){
            assertEquals(true, list.contains(elt));
        }
        for (String elt:list){
            assertEquals(true, cardsList.contains(elt));
        }
    }

    @Test
    void testCardsGeneration() {
        int size = 5;
        choosenCards = openCardsService.setRandomCards(cardsList, size);
        assertEquals(size, choosenCards.size());
        size = 15;
        choosenCards = openCardsService.setRandomCards(cardsList, size);
        assertEquals(null, choosenCards);
    }

    public void displayCards(ArrayList<Card> list) {
        ArrayList<String> listStr = new ArrayList<String>(list.size());
        for(Card card:list){
            listStr.add(card.getName()+":"+card.getColor() );
        }
        System.out.println(listStr);
    }

    @Test
    void testConfigCards() {
        int size = 5;
        choosenCards = openCardsService.setRandomCards(cardsList, size);
        openCardsService.configCards(choosenCards);
        displayCards(choosenCards);
        int[] listValues = giveTypeList(choosenCards);
        assertEquals(Math.max(1,size/25), listValues[0]);
        assertEquals(Math.max(1,size*8/25), Math.min(listValues[1],listValues[2]));
        assertEquals(Math.max(1,size*8/25)+1, Math.max(listValues[1],listValues[2]));
        assertEquals(size - listValues[0] - listValues[1] - listValues[2], listValues[3]);
    }
    public int[] giveTypeList(ArrayList<Card> cards) {
        int[] listValues = new int[4];
        for (Card card:cards){
            if (card.getColor() == Color.BLACK){
                listValues[0] += 1;
            } else if (card.getColor() == Color.RED){
                listValues[1] += 1;
            } else if (card.getColor() == Color.BLUE) {
                listValues[2] += 1;
            } else {
                assertEquals(Color.WHITE, card.getColor());
                listValues[3] += 1;
            }

        }
        return listValues;
    }
    @Test
    void testIndex() {
        assertEquals(1, openCardsService.getIndex(1,0,3));
        assertEquals(4, openCardsService.getIndex(1,1,3));
        assertEquals(5, openCardsService.getIndex(2,1,3));
    }

    @Test
    void testGridCreator() {
        Card[][] grid = openCardsService.initListCards(5,3);
        assertEquals(true, grid[0][0] instanceof Card);
        assertEquals(true, grid[3][1] instanceof Card);
        assertEquals(true, grid[4][2] instanceof Card);
    }
}

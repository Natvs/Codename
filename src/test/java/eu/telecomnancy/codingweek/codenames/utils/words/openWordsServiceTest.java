package eu.telecomnancy.codingweek.codenames.utils.words;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import eu.telecomnancy.codingweek.codenames.model.Color;
import org.junit.jupiter.api.Test;


public class openWordsServiceTest {
    private static ArrayList<String> wordsList; 
    private static ArrayList<Words> choosenWords;
    
    @BeforeAll
    static void configTest() {
        String fileName = new String("src/test/resources/words/test_words.txt");
        wordsList = openWordsService.openFile(fileName);
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
        for (String elt:wordsList){
            assertEquals(true, list.contains(elt));
        }
        for (String elt:list){
            assertEquals(true, wordsList.contains(elt));
        }
    }

    @Test
    void testWordsGeneration() {
        int size = 5;
        choosenWords = openWordsService.setRandomWords(wordsList, size);
        assertEquals(size, choosenWords.size());
        size = 15;
        choosenWords = openWordsService.setRandomWords(wordsList, size);
        assertEquals(null, choosenWords);
    }

    public void displayWords(ArrayList<Words> list) {
        ArrayList<String> listStr = new ArrayList<String>(list.size());
        for(Words word:list){
            listStr.add(word.getName()+":"+word.getColor() );
        }
        System.out.println(listStr);
    }

    @Test
    void testConfigWords() {
        int size = 5;
        choosenWords = openWordsService.setRandomWords(wordsList, size);
        openWordsService.configWords(choosenWords);
        displayWords(choosenWords);
        int[] listValues = giveTypeList(choosenWords);
        assertEquals(Math.max(1,size/25), listValues[0]);
        assertEquals(Math.max(1,size*8/25), Math.min(listValues[1],listValues[2]));
        assertEquals(Math.max(1,size*8/25)+1, Math.max(listValues[1],listValues[2]));
        assertEquals(size - listValues[0] - listValues[1] - listValues[2], listValues[3]);
    }
    public int[] giveTypeList(ArrayList<Words> words) {
        int[] listValues = new int[4];
        for (Words word:words){
            if (word.getColor() == Color.BLACK){
                listValues[0] += 1;
            } else if (word.getColor() == Color.RED){
                listValues[1] += 1;
            } else if (word.getColor() == Color.BLUE) {
                listValues[2] += 1;
            } else {
                assertEquals(Color.WHITE, word.getColor());
                listValues[3] += 1;
            }

        }
        return listValues;
    }
}

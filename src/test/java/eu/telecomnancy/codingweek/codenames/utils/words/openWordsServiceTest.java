package eu.telecomnancy.codingweek.codenames.utils.words;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class openWordsServiceTest {
    private static ArrayList<String> wordsList; 

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
        ArrayList<Words> choosenWords = openWordsService.setRandomWords(wordsList, size);
        assertEquals(size, choosenWords.size());
        displayWords(choosenWords);
        size = 15;
        choosenWords = openWordsService.setRandomWords(wordsList, size);
        assertEquals(null, choosenWords);
    }

    public void displayWords(ArrayList<Words> list) {
        ArrayList<String> listStr = new ArrayList<String>(list.size());
        for(Words word:list){
            listStr.add(word.getName());
        }
        System.out.println(listStr);
    }
}

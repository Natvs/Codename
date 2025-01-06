package eu.telecomnancy.codingweek.codenames.utils.words;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

//import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class openWordsServiceTest {
    
    @Test
    void testOpenFile() {
        String fileName = new String("src/test/resources/words/test_words.txt");
        ArrayList<String> wordsList = openWordsService.openFile(fileName);
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
}

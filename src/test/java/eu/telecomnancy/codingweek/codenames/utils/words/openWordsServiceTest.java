package eu.telecomnancy.codingweek.codenames.utils.words;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class openWordsServiceTest {
    
    @Test
    void testOpenFile() {
        String fileName = new String("test.txt");
        openWordsService instance = openWordsService.getInstance(fileName);
        assertNotEquals(null, instance);
    }
}

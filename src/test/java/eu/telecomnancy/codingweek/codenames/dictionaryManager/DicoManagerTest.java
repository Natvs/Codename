package eu.telecomnancy.codingweek.codenames.dictionaryManager;

import org.junit.jupiter.api.Test;


public class DicoManagerTest {
    @Test
    void testDicoManager() {

        // Paramètres :
        String project_file_path = "src/main/java/eu/telecomnancy/codingweek/codenames/dictionaryManager/PythonDictionaryManager/";
        String word = "abat-jour";
        String image_save_path = project_file_path + "BdD";
        String codenamesWords_path = project_file_path + "codenames_words.txt";
        String lexicalField_path = project_file_path + "lexical_field.txt";
        String[] args = {word, image_save_path, codenamesWords_path, lexicalField_path};
        
        // Test DicoManager.main
        DicoManager.manageDico(args);
    }

}

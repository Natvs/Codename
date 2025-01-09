package eu.telecomnancy.codingweek.codenames.theme;


import java.util.ArrayList;
import java.util.Arrays;

import eu.telecomnancy.codingweek.codenames.theme.search.Utility;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class SearchTest {
    @Test
    void testTheme() {

        ArrayList<String> dico = openCardsService.openFile("src/main/resources/words/codenames_words.txt");

        // Test Utility.getSearchDico
        // Test 1: attention aux mots avec majuscule et "oe" colée
        String keyWord = "no";
        ArrayList<String> searchDico = Utility.getSearchDico(keyWord);
        ArrayList<String> searchDico_expected = new ArrayList<>(Arrays.asList(
            "Noël", "nœud", "noisette", "noix", "nom", "nombre", "nourriture", "noyau"
        ));
        assertEquals(searchDico_expected, searchDico);
        // Test 2: attention aux mots avec accents
        keyWord = "pat";
        searchDico = Utility.getSearchDico(keyWord);
        searchDico_expected = new ArrayList<>(Arrays.asList(
            "patate", "pâte", "pâte à modeler", "pâtisserie", "patte"
        ));
        assertEquals(searchDico_expected, searchDico);
    }

}

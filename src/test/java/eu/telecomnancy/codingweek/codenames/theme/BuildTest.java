package eu.telecomnancy.codingweek.codenames.theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import eu.telecomnancy.codingweek.codenames.utils.openCardsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class BuildTest {
    @Test
    void testBuildTheme() {

        ArrayList<String> dico = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<String> dico_lexicalField = openCardsService.openFile("src/main/resources/words/lexical_field.txt");

        // Teste Utility.getWordId :
        Integer wordId = Utility.getWordId("abeille", dico); // Teste 1: avec "abeille"
        assertEquals(1, wordId);
        wordId = Utility.getWordId("journaux", dico); // Teste 2: avec "journeaux"
        assertEquals(500, wordId);
        wordId = Utility.getWordId("zoo", dico); // Teste 3: avec "zoo"
        assertEquals(1000, wordId);

        // Teste Utility.getLexicalFieldIntegerList
        // Teste 1: avec "abeille"
        ArrayList<Integer> listDigit = Utility.getLexicalFieldIntegerList(1, dico_lexicalField);
        ArrayList<Integer> listDigit_expected = new ArrayList<>(Arrays.asList(
            588, 484, 455, 818, 403, 415, 608, 628, 732, 680, 124, 37, 635, 812, 38, 114, 28
        ));
        assertEquals(listDigit_expected, listDigit);
        // Teste 2: avec "journeaux"
        listDigit = Utility.getLexicalFieldIntegerList(500, dico_lexicalField);
        listDigit_expected = new ArrayList<>(Arrays.asList(
            679, 544, 140, 151, 801, 268, 319, 639, 388, 159, 930, 531, 276, 656, 519, 734, 860, 90, 338, 692, 924
        ));
        assertEquals(listDigit_expected, listDigit);
        // Teste 3: avec "zoo"
        listDigit = Utility.getLexicalFieldIntegerList(1000, dico_lexicalField);
        listDigit_expected = new ArrayList<>(Arrays.asList(
            685, 28, 139, 36, 491, 671, 438, 827, 527, 343, 661, 74, 467, 872, 977, 997
        ));
        assertEquals(listDigit_expected, listDigit);

        // Teste Utility.joinListDigit
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(3, 5, 6, 2));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 3, 2, 4));
        ArrayList<Integer> liestJoined = Utility.joinListDigit(list1, list2);
        Collections.sort(liestJoined);
        ArrayList<Integer> liestJoined_expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(liestJoined_expected, liestJoined);

        // Teste Utility.convertListDigitToLexicalFieldList
        listDigit = new ArrayList<>(Arrays.asList(1, 500, 1000));
        ArrayList<String> lexicalFieldList = Utility.convertListDigitToLexicalFieldList(listDigit, dico);
        ArrayList<String> lexicalFieldList_expected = new ArrayList<>(Arrays.asList(
            "abeille", "journaux", "zoo"
        ));
        assertEquals(lexicalFieldList_expected, lexicalFieldList);

        // Teste Utility.getDicoTheme
        // Test 1: avec mot du dictionnaire "dico"
        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList("abeille", "abricot"));
        ArrayList<String> dicoTheme = Utility.getDicoTheme(wordsList);
        ArrayList<String> dicoTheme_attendu = new ArrayList<>(Arrays.asList(
            "abeille", "abricot", "ananas", "animal", "araignée", "arbre", "banane", "bourgeon", "brosse", "cerise", "citron", "confiture", 
            "fleur", "fourmi", "fraise", "framboise", "fruit", "gâteau", "guêpe", "insecte", "jus", "melon", 
            "miel", "mouche", "nid", "nourriture", "noyau", "orange", "pamplemousse", "papillon", "pêche", "piqûre", 
            "poire", "pomme", "prune", "raisin", "rayon", "reine", "rosé", "sucre", "tarte"
        ));
        assertEquals(dicoTheme_attendu, dicoTheme);
        // Teste 2: avec mots inconus
        wordsList = new ArrayList<>(Arrays.asList("bougabouga", "42", "patate chaude"));
        dicoTheme = Utility.getDicoTheme(wordsList);
        dicoTheme_attendu = new ArrayList<>();
        assertEquals(dicoTheme_attendu, dicoTheme);

    }

}

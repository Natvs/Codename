package eu.telecomnancy.codingweek.codenames.ia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import eu.telecomnancy.codingweek.codenames.ia.algo.UtilityIA;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;


public class UtilityIATest {
    @Test
    void testCardGuesser() {

        // importe les dico
        ArrayList<String> dico = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<String> dico_lexicalField = openCardsService.openFile("src/main/resources/words/lexical_field.txt");

        // Test Utility.getLexicalFieldList
        // Test 1: avec "abeille"
        ArrayList<String> lexicalFieldList = UtilityIA.getLexicalFieldList("abeille", dico, dico_lexicalField);
        ArrayList<String> lexicalFieldList_expected = new ArrayList<>(Arrays.asList(
            "miel", "insecte", "guêpe", "reine", "fleur", "fourmi", "mouche", "nid", "piqûre", "papillon", 
            "brosse", "araignée", "nourriture", "rayon", "arbre", "bourgeon", "animal"
        ));
        assertEquals(lexicalFieldList_expected, lexicalFieldList);
        // Test 2: avec "journaux"
        lexicalFieldList = UtilityIA.getLexicalFieldList("journaux", dico, dico_lexicalField);
        lexicalFieldList_expected = new ArrayList<>(Arrays.asList(
            "papier", "magazine", "cahier", "canard", "radio", "courrier", "directeur", "écureuil", "feuille", "carnet", 
            "titre", "livre", "couverture", "manteau", "lecture", "placard", "semaine", "billet", "écriture", "partie", "ticket"
        ));
        // assertEquals(lexicalFieldList_expected, lexicalFieldList);
        // Test 3: avec "zoo"
        lexicalFieldList = UtilityIA.getLexicalFieldList("zoo", dico, dico_lexicalField);
        lexicalFieldList_expected = new ArrayList<>(Arrays.asList(
            "parc", "animal", "cage", "aquarium", "jardin", "panda", "girafe", "rhinocéros", "lion", "éléphant", 
            "ours", "bassin", "hippopotame", "singe", "vétérinaire", "zèbre"
        ));
        // assertEquals(lexicalFieldList_expected, lexicalFieldList);
        // Test 4: avec un mot inconu "bougabouga"
        lexicalFieldList = UtilityIA.getLexicalFieldList("bougabouga", dico, dico_lexicalField);
        lexicalFieldList_expected = new ArrayList<>();
        assertEquals(lexicalFieldList_expected, lexicalFieldList);

        // Test UtilityIA.intersectStringList
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(
            "abeille", "âge", "câlin", "arc-en-ciel", "journaux", "Noël", "zoo"
        ));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(
            "abricot", "âge", "arc-en-ciel", "banane", "journaux", "Noël", "zigzag"
        ));
        ArrayList<String> listIntersection = UtilityIA.intersectStringList(list1, list2);
        ArrayList<String> listIntersection_expected = new ArrayList<>(Arrays.asList(
            "âge", "arc-en-ciel", "journaux", "Noël"
        ));
        assertEquals(listIntersection_expected, listIntersection);
    }

}

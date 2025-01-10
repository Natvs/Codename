package eu.telecomnancy.codingweek.codenames.ia;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import eu.telecomnancy.codingweek.codenames.theme.Utility;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.ia.algo.Algo;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;


public class AlgoTest {
    @Test
    void testIA() {
        
        // Test Utility.getLexicalFieldList
        // Test 1: avec "abeille"
        ArrayList<String> lexicalFieldList = Utility.getLexicalFieldList("abeille");
        ArrayList<String> lexicalFieldList_expected = new ArrayList<>(Arrays.asList(
            "miel", "insecte", "guêpe", "reine", "fleur", "fourmi", "mouche", "nid", "piqûre", "papillon", 
            "brosse", "araignée", "nourriture", "rayon", "arbre", "bourgeon", "animal"
        ));
        assertEquals(lexicalFieldList_expected, lexicalFieldList);
        // Test 2: avec "journaux"
        lexicalFieldList = Utility.getLexicalFieldList("journaux");
        lexicalFieldList_expected = new ArrayList<>(Arrays.asList(
            "papier", "magazine", "cahier", "canard", "radio", "courrier", "directeur", "écureuil", "feuille", "carnet", 
            "titre", "livre", "couverture", "manteau", "lecture", "placard", "semaine", "billet", "écriture", "partie", "ticket"
        ));
        // assertEquals(lexicalFieldList_expected, lexicalFieldList);
        // Test 3: avec "zoo"
        lexicalFieldList = Utility.getLexicalFieldList("zoo");
        lexicalFieldList_expected = new ArrayList<>(Arrays.asList(
            "parc", "animal", "cage", "aquarium", "jardin", "panda", "girafe", "rhinocéros", "lion", "éléphant", 
            "ours", "bassin", "hippopotame", "singe", "vétérinaire", "zèbre"
        ));
        // assertEquals(lexicalFieldList_expected, lexicalFieldList);
        // Test 4: avec un mot inconu "bougabouga"
        lexicalFieldList = Utility.getLexicalFieldList("bougabouga");
        lexicalFieldList_expected = new ArrayList<>();
        assertEquals(lexicalFieldList_expected, lexicalFieldList);

        // Test simulate
        // A_REVOIR
    }

}

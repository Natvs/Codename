package eu.telecomnancy.codingweek.codenames.theme.search;

import java.util.ArrayList;

import eu.telecomnancy.codingweek.codenames.theme.search.Utility;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;


public class Utility {
    
    public static ArrayList<String> getSearchDico(String keyWord) {
        ArrayList<String> dico = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<String> searchDico = new ArrayList<>();

        for (String word : dico) {
            if (word.startsWith(keyWord)) {
                searchDico.add(word);
            }
        }

        return searchDico;
    }

}

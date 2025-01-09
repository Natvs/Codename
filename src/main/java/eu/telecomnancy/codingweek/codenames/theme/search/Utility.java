package eu.telecomnancy.codingweek.codenames.theme.search;

import java.text.Normalizer;
import java.util.ArrayList;

import eu.telecomnancy.codingweek.codenames.theme.search.Utility;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;


public class Utility {

    // Fonction pour normaliser une chaîne (supprimer les accents et insensibilité à la casse)
    public static String normaliseString(String text) {
        // Remplacer la ligature "œ" par "oe" manuellement
        text = text.replace("œ", "oe").replace("Œ", "oe");
        // Supprimer les accents en utilisant la normalisation Unicode
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        // Retirer les caractères diacritiques (accents)
        normalizedText = normalizedText.replaceAll("\\p{M}", "").toLowerCase();
        return normalizedText;
    }
    
    public static ArrayList<String> getSearchDico(String keyWord) {
        ArrayList<String> dico = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<String> searchDico = new ArrayList<>();

        String NormalisedKeyWord = normaliseString(keyWord);
        for (String word : dico) {
            String NormalisedWord = normaliseString(word);
            if (NormalisedWord.startsWith(NormalisedKeyWord)) {
                searchDico.add(word);
            }
        }

        return searchDico;
    }

}

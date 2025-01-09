package eu.telecomnancy.codingweek.codenames.theme.build;

import java.util.ArrayList;
import java.util.HashSet;

import eu.telecomnancy.codingweek.codenames.utils.openCardsService;


public class Utility {
    
    public static int getWordId(String word, ArrayList<String> dico) {
        // Obtenir l'id du mot "word" dans le dictionnaire "dico"

        int wordId = 0;
        int dicoLength = dico.size();

        while (wordId < dicoLength && !(word.equals(dico.get(wordId)))) {
            wordId++;
        }
        if (wordId == dicoLength) {
            wordId = -1; // en cas d'erreur on renvois -1
        }
        return wordId;
    }

    public static ArrayList<Integer> getLexicalFieldIntegerList(int wordId, ArrayList<String> dico_lexicalField) {
        // Obtenir la liste des id des mots du champ lexical "listDigit" du mot associé à l'id "wordId"
        
        String ligne = dico_lexicalField.get(wordId);
        ArrayList<Integer> listDigit = new ArrayList<>();
        
        String[] elements = ligne.split(",\\s*"); // Séparer la chaîne en utilisant la virgule comme délimiteur
        for (String element : elements) { // Parcourir chaque élément et convertir en Integer
            listDigit.add(Integer.parseInt(element));
        }
        return listDigit;
    }

    public static ArrayList<String> getLexicalFieldList(ArrayList<Integer> listDigit, ArrayList<String> dico) {
        // Convertir la liste d'id "listDigit" en la la liste de mot associé au "dico"

        ArrayList<String> lexicalFieldList = new ArrayList<>();
        for (int wordId : listDigit) {
            String word = dico.get(wordId);
            lexicalFieldList.add(word);
        }
        return lexicalFieldList;
    }

    public static ArrayList<String> getWordDicoTheme(String word, ArrayList<String> dico, ArrayList<String> dico_lexicalField) {
        // Obtenir le cictionnaire des champs lexical associé au mot "word"
        ArrayList<String> wordDicoTheme;

        int wordId = Utility.getWordId(word, dico);
        if (wordId == -1) { // en cas d'erreur on renvois un "dicoTheme" vide
            wordDicoTheme = null;
        } else {
            ArrayList<Integer> listDigit = Utility.getLexicalFieldIntegerList(wordId, dico_lexicalField);
            wordDicoTheme = Utility.getLexicalFieldList(listDigit, dico); 
        }

        return wordDicoTheme;
    }

    public static ArrayList<String> joinDico(ArrayList<String> dico1, ArrayList<String> dico2) {
        // Utilisation d'un HashSet pour éliminer les doublons
        HashSet<String> set = new HashSet<>(dico1);
        set.addAll(dico2);

        // Convertir le HashSet en ArrayList
        return new ArrayList<>(set);
    }

    public static ArrayList<String> getDicoTheme(ArrayList<String> wordsList) {
        ArrayList<String> dicoTheme = new ArrayList<>();
        ArrayList<String> dico = openCardsService.openFile("src/main/java/resources/words/codenames_words.txt");
        ArrayList<String> dico_lexicalField = openCardsService.openFile("src/main/java/resources/words/lexical_field.txt");
        
        for (String word : wordsList) {
            ArrayList<String> wordDicoTheme = Utility.getWordDicoTheme(word, dico, dico_lexicalField);
            dicoTheme = Utility.joinDico(dicoTheme, wordDicoTheme);
        }
        return dicoTheme;
    }

}

package eu.telecomnancy.codingweek.codenames.theme.build;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

import eu.telecomnancy.codingweek.codenames.utils.openCardsService;


public class Utility {
    
    public static Integer getWordId(String word, ArrayList<String> dico) {
        // Obtenir l'id du mot "word" dans le dictionnaire "dico"

        Integer wordId = 0;
        Integer dicoLength = dico.size();

        while (wordId < dicoLength && !(word.equals(dico.get(wordId)))) {
            wordId++;
        }
        if (wordId == dicoLength) {
            wordId = -1; // en cas d'erreur on renvois -1
        }
        return wordId+1;
    }

    public static ArrayList<Integer> getLexicalFieldIntegerList(Integer wordId, ArrayList<String> dico_lexicalField) {
        // Obtenir la liste des id des mots du champ lexical "listDigit" du mot associé à l'id "wordId"
        
        String ligne = dico_lexicalField.get(wordId);
        ArrayList<Integer> listDigit = new ArrayList<>();
        
        String[] elements = ligne.split(",\\s*"); // Séparer la chaîne en utilisant la virgule comme délimiteur
        for (String element : elements) { // Parcourir chaque élément et convertir en Integer
            listDigit.add(Integer.parseInt(element));
        }
        return listDigit;
    }

    public static ArrayList<Integer> joinListDigit(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        // Utiliser un HashSet pour éviter les doublons
        HashSet<Integer> set = new HashSet<>();
        
        // Ajouter tous les éléments des deux listes au set
        set.addAll(list1);
        set.addAll(list2);
        
        // Convertir le set en ArrayList
        return new ArrayList<>(set);
    }

    public static ArrayList<String> convertListDigitToLexicalFieldList(ArrayList<Integer> listDigit, ArrayList<String> dico) {
        // Convertir la liste d'id "listDigit" en la la liste de mot associé au "dico"

        ArrayList<String> lexicalFieldList = new ArrayList<>();
        for (Integer wordId : listDigit) {
            String word = dico.get(wordId);
            lexicalFieldList.add(word);
        }
        return lexicalFieldList;
    }

    public static ArrayList<String> getDicoTheme(ArrayList<String> wordsList) {
        ArrayList<String> dicoTheme = new ArrayList<>();
        ArrayList<String> dico = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<String> dico_lexicalField = openCardsService.openFile("src/main/resources/words/lexical_field.txt");

        ArrayList<Integer> listDigitTheme = new ArrayList<>();
        for (String word : wordsList) {
            Integer wordId = Utility.getWordId(word, dico);
            if (wordId == -1) {
                ArrayList<Integer> wordListDigit = Utility.getLexicalFieldIntegerList(wordId, dico_lexicalField);
                wordListDigit.add(wordId);
                listDigitTheme = Utility.joinListDigit(listDigitTheme, wordListDigit);
            }
        }
        Collections.sort(listDigitTheme);
        dicoTheme = Utility.convertListDigitToLexicalFieldList(listDigitTheme, dico);
        return dicoTheme;
    }

}

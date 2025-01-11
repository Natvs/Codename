package eu.telecomnancy.codingweek.codenames.ia.algo;

import java.text.Normalizer;
import java.util.ArrayList;


public class UtilityIA {

    public static Integer getWordId(String word, ArrayList<String> dico) {
        // Obtenir l'id du mot "word" dans le dictionnaire "dico"

        Integer wordId = 0;
        Integer dicoLength = dico.size();

        while (wordId < dicoLength && !(word.equals(dico.get(wordId)))) {
            wordId++;
        }
        if (wordId >= dicoLength) {
            wordId = -1; // en cas d'erreur on renvois -1
        }
        return wordId+1;
    }

    public static ArrayList<Integer> getLexicalFieldIntegerList(Integer wordId, ArrayList<String> dico_lexicalField) {
        // Obtenir la liste des id des mots du champ lexical "listDigit" du mot associé à l'id "wordId"
        
        String ligne = dico_lexicalField.get(wordId-1);
        ArrayList<Integer> lexicalFieldList = new ArrayList<>();
        
        String[] elements = ligne.split(",\\s*"); // Séparer la chaîne en utilisant la virgule comme délimiteur
        if (elements[0].equals("")) {
            return lexicalFieldList;
        }
        for (String element : elements) { // Parcourir chaque élément et convertir en Integer
            lexicalFieldList.add(Integer.parseInt(element));
        }
        return lexicalFieldList;
    }

    public static ArrayList<String> convertListDigitToLexicalFieldList(ArrayList<Integer> listDigit, ArrayList<String> dico) {
        // Convertir la liste d'id "listDigit" en la la liste de mot associé au "dico"

        ArrayList<String> lexicalFieldList = new ArrayList<>();
        for (Integer wordId : listDigit) {
            String word = dico.get(wordId-1);
            lexicalFieldList.add(word);
        }
        return lexicalFieldList;
    }
    
    public static ArrayList<String> getLexicalFieldList(String word, ArrayList<String> dico, ArrayList<String> dico_lexicalField) {
        ArrayList<String> lexicalFieldList;
        Integer wordId = UtilityIA.getWordId(word, dico);
        if (wordId < 1) {
            lexicalFieldList = new ArrayList<>();
        } else {
            ArrayList<Integer> listDigit = UtilityIA.getLexicalFieldIntegerList(wordId, dico_lexicalField);
            lexicalFieldList = convertListDigitToLexicalFieldList(listDigit, dico);
        }
        return lexicalFieldList;
    }

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

    public static ArrayList<String> normaliseStringList(ArrayList<String> list) {
        ArrayList<String> normalisedList = new ArrayList<>();
        for (String text : list) {
            String normalisedText = normaliseString(text);
            normalisedList.add(normalisedText);
        }
        return normalisedList;
    }

    public static ArrayList<String> intersectStringList(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> listIntersection = new ArrayList<>();
        ArrayList<String> normalisedList2 = normaliseStringList(list2);
        for (String text : list1) {
            String NormalisedText = normaliseString(text);
            if (normalisedList2.contains(NormalisedText)) {
                listIntersection.add(text);
            }
        }
        return listIntersection;
    }

}

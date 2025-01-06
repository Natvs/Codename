package eu.telecomnancy.codingweek.codenames.utils.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class openWordsService {
    

    public static ArrayList<String> openFile(String fileName){
        ArrayList<String> lines = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        } catch (FileNotFoundException e){
            System.err.println("Fichier introuvable\n"+e.getMessage());
            System.exit(1);
        } catch (IOException e){
            System.err.println("Erreur d'entrée/sortie\n"+e.getMessage());
            System.exit(2);
        } catch (SecurityException e){
            System.err.println("Erreur de sécurité\n");
            System.exit(3);
        }
        return lines;

    }

    public static ArrayList<Words> setRandomWords(ArrayList<String> list,int nb_elt){
        int size = list.size(); // que se passe t'il si la liste est vide ?
        if (size<nb_elt){
            System.err.println("liste de mots trop petite pour pouvoir remplir le tableau");
            return null;
        }
        ArrayList<Words> listWords = new ArrayList<Words>(nb_elt);
        Random random = new Random();
        while(listWords.size()<nb_elt){
            int randomInt = random.nextInt(size);
            Words word = new Words(list.get(randomInt));
            if (!isWordInList(word, listWords)){
                listWords.add(word);
            }
        }
        return listWords;
    }

    public static boolean isWordInList(Words word,ArrayList<Words> list){
        String name = word.getName();
        for(Words elt:list){
            if (elt.getName()==name){
                return true;
            }
        }
        return false;
    }
}

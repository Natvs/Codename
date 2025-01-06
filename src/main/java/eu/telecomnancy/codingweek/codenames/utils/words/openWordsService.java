package eu.telecomnancy.codingweek.codenames.utils.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import eu.telecomnancy.codingweek.codenames.model.Color;

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
        int size = list.size();
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

    public static void configWords(ArrayList<Words> list){

        int[] listValues = new int[3]; // noir, bleu, rouge, blanc = reste
        int size = list.size();
        // choose the team which start
        Random rand = new Random();
        int randInt = rand.nextInt(1);
        // set up the number of tiles for each types
        listValues[0] = Math.max(1,size/25);
        listValues[1] = Math.max(1,size*8/25) + randInt;
        listValues[2] = Math.max(1,size*8/25) + 1 - randInt;
        for (Words elt:list){
            randInt = rand.nextInt(size);
            size -= 1;
            if (randInt<listValues[0]){
                elt.setColor(Color.BLACK);
                listValues[0] -= 1;
            } else if (randInt < listValues[1]+listValues[0]){
                elt.setColor(Color.RED);
                listValues[1] -= 1;
            } else if (randInt < listValues[2] + listValues[1] + listValues[0]){
                elt.setColor(Color.BLUE);
                listValues[2] -= 1;
            }
            else {
                elt.setColor(Color.WHITE);
            }
        }
        if (0 != listValues[0] || 0 != listValues[1] || 0 != listValues[2]){
            System.err.println("Not every tile implemented"); // not supposed to appear
        }

    }

    public static ArrayList<Words> initListWords(int size){
        ArrayList<String> lines = openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<Words> choosenWords = setRandomWords(lines, size);
        configWords(choosenWords);
        return choosenWords;
    }
}

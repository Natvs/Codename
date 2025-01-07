package eu.telecomnancy.codingweek.codenames.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.card.Card;

public class openCardsService {
    

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

    public static ArrayList<Card> setRandomCards(ArrayList<String> list,int nb_elt){
        int size = list.size();
        if (size<nb_elt){
            System.err.println("liste de mots trop petite pour pouvoir remplir le tableau");
            return null;
        }
        ArrayList<Card> listCards = new ArrayList<Card>(nb_elt);
        Random random = new Random();
        while(listCards.size()<nb_elt){
            int randomInt = random.nextInt(size);
            Card card = new Card(list.get(randomInt),Color.WHITE);
            if (!isCardInList(card, listCards)){
                listCards.add(card);
            }
        }
        return listCards;
    }

    public static boolean isCardInList(Card card,ArrayList<Card> list){
        String name = card.getName();
        for(Card elt:list){
            if (elt.getName()==name){
                return true;
            }
        }
        return false;
    }

    public static void configCards(ArrayList<Card> list){

        int[] listValues = new int[3]; // noir, bleu, rouge, blanc = reste
        int size = list.size();
        // choose the team which start
        Random rand = new Random();
        int randInt = rand.nextInt(1);
        // set up the number of tiles for each types
        listValues[0] = Math.max(1,size/25);
        listValues[1] = Math.max(1,size*8/25) + randInt;
        listValues[2] = Math.max(1,size*8/25) + 1 - randInt;
        for (Card elt:list){
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

    public static ArrayList<Card> initListCards(int size){
        ArrayList<String> lines = openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<Card> choosenCards = setRandomCards(lines, size);
        configCards(choosenCards);
        return choosenCards;
    }
}

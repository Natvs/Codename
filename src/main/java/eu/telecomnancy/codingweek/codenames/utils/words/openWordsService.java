package eu.telecomnancy.codingweek.codenames.utils.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class openWordsService {
    

    public static ArrayList<String> openFile(String fileName){
        ArrayList<String> lines = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            System.out.println("s'éxecute");
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        } catch (FileNotFoundException e){
            System.err.println("Fichier introuvable\n"+e.getMessage());
        } catch (IOException e){
            System.err.println("Erreur d'entrée/sortie\n"+e.getMessage());
        } catch (SecurityException e){
            System.err.println("Erreur de sécurité\n");
        }
        return lines;

    }
}

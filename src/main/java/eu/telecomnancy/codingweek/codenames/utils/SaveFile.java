package eu.telecomnancy.codingweek.codenames.utils;


import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class SaveFile {
    public static void saveData(String fileName,Session session){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(fileName), session);
        } catch (IOException e){
            System.err.println("Erreur d'entrée/sortie\n"+e.getMessage());
            System.exit(2);
        } catch (SecurityException e){
            System.err.println("Erreur de sécurité\n");
            System.exit(3);
        }

    }

    public static Session loadData(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        Session session = new Session();
        try {
            session = objectMapper.readValue(new File(fileName), Session.class);
            saveData("src/test/resources/savefile.json", session);
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
        return session;
    }
    public static void erraseData(String fileName){
        File file = new File(fileName);
        if (file.exists()){
            if (file.delete()){
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.err.println("This file doesn't exist.");
        }
    }
}

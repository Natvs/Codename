package eu.telecomnancy.codingweek.codenames.dictionaryManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class DicoManager {
    
    public static void main(String[] args) {
        try {
            // Commande pour exécuter le script Python
            ProcessBuilder pb = new ProcessBuilder("python", "script.py");
            Process process = pb.start();
            
            // Lire la sortie du script Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Attendre que le processus se termine
            int exitCode = process.waitFor();
            System.out.println("Exit Code : " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

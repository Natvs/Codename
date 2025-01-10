package eu.telecomnancy.codingweek.codenames.dictionaryManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class DicoManager {
    
    public static void main(String[] args) {
        try {
            // Chemin vers l'exécutable
            String project_file_path = "src/main/java/eu/telecomnancy/codingweek/codenames/dictionaryManager/PythonDictionaryManager/";
            String executablePath = project_file_path + "dist/Script_DictionaryManager.exe";

            // Construire le processus
            ProcessBuilder processBuilder = new ProcessBuilder(executablePath);
            Process process = processBuilder.start();

            // Lire la sortie du script Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Attendre la fin du processus
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

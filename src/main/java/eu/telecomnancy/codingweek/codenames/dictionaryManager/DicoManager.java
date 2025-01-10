package eu.telecomnancy.codingweek.codenames.dictionaryManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class DicoManager {
    
    public static void manage(String[] args) throws Exception{
        String os = System.getProperty("os.name").toLowerCase();
        String project_file_path = "src/main/java/eu/telecomnancy/codingweek/codenames/dictionaryManager/PythonDictionaryManager/";
        String executablePath;
        if (os.contains("win")) {
            executablePath = project_file_path + "dist/Script_DictionaryManagerWindows.exe";
            System.out.println("Running on Windows");
            
        } else if (os.contains("mac")) {
            executablePath = project_file_path + "dist/Script_DictionaryManagerMac";
            System.out.println("Running on macOS");

        } else if (os.contains("nix") || os.contains("nux")) {
            executablePath = project_file_path + "dist/Script_DictionaryManagerLinux";
            System.out.println("Running on Linux/Unix");

        } else {
            throw new Exception("Unknown OS");
        }
        try {
            // Chemin vers l'exécutable

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

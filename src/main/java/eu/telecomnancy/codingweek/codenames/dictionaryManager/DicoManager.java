package eu.telecomnancy.codingweek.codenames.dictionaryManager;

import java.io.IOException;

public class DicoManager {
    
    public static void manage(String[] args) throws Exception{
        String os = System.getProperty("os.name").toLowerCase();
        String project_file_path = "src/main/resources/script/PythonDictionaryManager/";
        String executablePath;
        if (os.contains("win")) {
            executablePath = project_file_path + "dist/Script_DictionaryManager.exe";
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

            // Create a ProcessBuilder to run the Python script with arguments
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(executablePath, args[0], args[1], args[2], args[3]);

            // Start the process
            Process process = processBuilder.start();

            // Wait for the script to finish
            int exitCode = process.waitFor();
            System.out.println("Python script finished with exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

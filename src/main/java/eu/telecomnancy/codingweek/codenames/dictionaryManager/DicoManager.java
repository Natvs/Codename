package eu.telecomnancy.codingweek.codenames.dictionaryManager;

import java.io.IOException;

public class DicoManager {
    
    public static void manage(String[] scriptArgs) {
        // Path to your Python script
        String scriptPath = "src/main/resources/script/PythonDictionaryManager/dist/Script_DictionaryManager.exe";

        // Create a ProcessBuilder to run the Python script with arguments
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(scriptPath, scriptArgs[0], scriptArgs[1], scriptArgs[2], scriptArgs[3]);

        try {
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

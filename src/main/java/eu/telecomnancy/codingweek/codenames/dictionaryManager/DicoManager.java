package eu.telecomnancy.codingweek.codenames.dictionaryManager;

public class DicoManager {
    
    public static void manageDico(String[] args) {
        try {
            // Chemin vers l'exécutable
            String project_file_path = "src/main/java/eu/telecomnancy/codingweek/codenames/dictionaryManager/PythonDictionaryManager/";
            String executablePath = project_file_path + "dist/Script_DictionaryManager.exe";

            // Construire le processus
            ProcessBuilder processBuilder = new ProcessBuilder(executablePath);
            Process process = processBuilder.start();

            // Attendre la fin du processus
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

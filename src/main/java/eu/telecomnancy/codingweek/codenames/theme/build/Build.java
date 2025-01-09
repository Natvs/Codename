package eu.telecomnancy.codingweek.codenames.theme.build;

import java.util.ArrayList;


public class Build {
    
    private ArrayList<String> wordsList;
    private ArrayList<String> dicoTheme;

    Build(ArrayList<String> wordsList) {
        this.wordsList = wordsList;
        this.dicoTheme = Utility.getDicoTheme(wordsList);
    }

    ArrayList<String> getKeyWordList() {
        return this.wordsList;
    }

    ArrayList<String> getDicoTheme() {
        return this.dicoTheme;
    }

    int getDicoThemeLength() {
        return this.dicoTheme.size();
    }

}

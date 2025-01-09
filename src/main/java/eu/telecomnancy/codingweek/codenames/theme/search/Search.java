package eu.telecomnancy.codingweek.codenames.theme.search;

import java.util.ArrayList;


public class Search {
    
    private String keyWord;
    private ArrayList<String> dicoTheme;

    Search(String keyWord) {
        this.keyWord = keyWord;
        this.dicoTheme = Utility.getSearchDico(keyWord);
    }

    String getKeyWordList() {
        return this.keyWord;
    }

    ArrayList<String> getDicoTheme() {
        return this.dicoTheme;
    }

    int getDicoThemeLength() {
        return this.dicoTheme.size();
    }

}

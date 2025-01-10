package eu.telecomnancy.codingweek.codenames.ia.algo;

import java.util.ArrayList;

import eu.telecomnancy.codingweek.codenames.theme.Utility;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.board.Board;
import java.util.Random;


public class Algo {
    
    private Clue clue;
    private ArrayList<String> result;

    public Algo(Clue clue) {
        this.clue = clue;
        this.result = new ArrayList<String>();
    }

    public Clue getClue() {
        return this.clue;
    }

    public ArrayList<String> getResult() {
        return this.result;
    }

    public void simulate(Session session) {

        // On récupère les paramètres indices "word" et "count"
        String word = clue.getWord();
        int count = clue.getCount();

        // On récupère la liste des noms des carte "cardsNameList"
        ArrayList<String> cardsNameList = new ArrayList<>();
        Board board = session.getBoard();
        int height = board.getHeigth();
        int width = board.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String cardName = board.getCard(i,j).getName();
                cardsNameList.add(cardName);
            }
        }

        // On récupère la liste des champs lexicaus "lexicalFieldList" associé au mot "word"
        ArrayList<String> lexicalFieldList = Utility.getLexicalFieldList(word);
        
        // On récupère les "count" premières cartes qui sont dan la iste des champs lexicaus "lexicalFieldList" 
        ArrayList<String> result = new ArrayList<>();
        for (String LexicalFieldWord : lexicalFieldList) {
            if (result.size() < count && cardsNameList.contains(LexicalFieldWord)) {
                result.add(LexicalFieldWord);
            }
        }
        // en cas d'echecs on complète la liste avec des mots aléatoire
        int numberMissingCards = count - result.size();
        if (numberMissingCards > 0) {
            for (int k = 0; k < numberMissingCards; k++) {
                int r = -1;
                while (r == -1) {
                    Random random = new Random();
                    r = random.nextInt(height*width);
                    if (result.contains(cardsNameList.get(r))) {
                        r = -1;
                    }
                }
                String cardName = cardsNameList.get(r);
                result.add(cardName);
            }
        }
        this.result = result; 
    }

}

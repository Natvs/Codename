package eu.telecomnancy.codingweek.codenames.ia.algo;

import java.util.ArrayList;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;


public class ClueGuesser {
    
    private Clue clue;

    public ClueGuesser(Color color, Session session) {

        // importe les dico
        ArrayList<String> dico = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<String> dico_lexicalField = openCardsService.openFile("src/main/resources/words/lexical_field.txt");

        // On récupère la liste des noms des carte "cardsNameList" non encore révélées
        ArrayList<String> cardsNameList = new ArrayList<>();
        Board board = session.getBoard();
        int height = board.getHeigth();
        int width = board.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Card card = board.getCard(i,j);
                if (!(card.getRevealed()) && card.getColor() == color) {
                    String cardName = card.getName();
                    cardsNameList.add(cardName);
                }
            }
        }

        // Cherche le meilleur indice "clue"
        int count = -1;
        String word = null;
        for (String wordDico : dico) {
            if (!(cardsNameList.contains(wordDico))) {
                ArrayList<String> lexicalFieldList = UtilityIA.getLexicalFieldList(wordDico, dico, dico_lexicalField);
                ArrayList<String>  intersection = UtilityIA.intersectStringList(lexicalFieldList, cardsNameList);
                if (intersection.size() > count) {
                    count = intersection.size();
                    word = wordDico;
                }
            }
        }

        // On convertie "result_withCardsName" pour la sortie de l'ia
        this.clue = new Clue(word, count);

    }

    public Clue getClue() {
        return this.clue;
    }

}

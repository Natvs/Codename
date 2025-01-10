package eu.telecomnancy.codingweek.codenames.ia.algo;

import java.util.ArrayList;
import java.util.Random;

import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.utils.openCardsService;


public class CardGuesser {
    
    private final Clue clue;
    private final Card[] result;

    public CardGuesser(Session session) {

        // Initialise la sortie de l'ia
        this.clue = session.getCurrentColoredTeam().getCluesList().getLast();
        String word = this.clue.getWord();
        int count = this.clue.getCount();
        Card[] result = new Card[count];

        // On récupère la liste des noms des carte "cardsNameList" non encore révélées
        ArrayList<String> cardsNameList = new ArrayList<>();
        Board board = session.getBoard();
        int height = board.getHeigth();
        int width = board.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Card card = board.getCard(i,j);
                if (!(card.getRevealed())) {
                    String cardName = card.getName();
                    cardsNameList.add(cardName);
                }
            }
        }

        // importe les dico
        ArrayList<String> dico = openCardsService.openFile("src/main/resources/words/codenames_words.txt");
        ArrayList<String> dico_lexicalField = openCardsService.openFile("src/main/resources/words/lexical_field.txt");

        // On récupère la liste des champs lexicaus "lexicalFieldList" associé au mot "word"
        ArrayList<String> lexicalFieldList = UtilityIA.getLexicalFieldList(word, dico, dico_lexicalField);
        
        // On récupère les "count" premières cartes qui sont dan la iste des champs lexicaus "lexicalFieldList" 
        ArrayList<String> result_withCardsName = new ArrayList<>();
        for (String LexicalFieldWord : lexicalFieldList) {
            if (result_withCardsName.size() < count && cardsNameList.contains(LexicalFieldWord)) {
                result_withCardsName.add(LexicalFieldWord);
            }
        }

        // en cas d'echecs on complète la liste "result_withCardsName" avec des mots aléatoire
        int numberMissingCards = count - result_withCardsName.size();
        if (numberMissingCards > 0) {
            for (int k = 0; k < numberMissingCards; k++) {
                int r = -1;
                while (r == -1) {
                    Random random = new Random();
                    r = random.nextInt(cardsNameList.size());
                    if (result_withCardsName.contains(cardsNameList.get(r))) {
                        r = -1;
                    }
                }
                String cardName = cardsNameList.get(r);
                result_withCardsName.add(cardName);
            }
        }

        // On convertie "result_withCardsName" pour la sortie de l'ia
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Card card = board.getCard(i,j);
                String cardName = card.getName();
                if (result_withCardsName.contains(cardName)) {
                    result[result_withCardsName.indexOf(cardName)] = card;
                }
            }
        }
        this.result = result;
    }

    public Clue getClue() {
        return this.clue;
    }

    public Card[] getResult() {
        return this.result;
    }

}

package eu.telecomnancy.commands;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class GuessCardCommand implements Command {
    
    private final Session session;
    private final Card card;

    public GuessCardCommand(Card card, Session session) {
        this.session = session;
        this.card = card;
    }

    public void execute() {
        session.guessCard(card);
    }

    public void undo() {
        
    }

}

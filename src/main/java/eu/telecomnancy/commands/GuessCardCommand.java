package eu.telecomnancy.commands;

import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class GuessCardCommand implements Command {
    
    private final Session session;
    private final Card card;

    public GuessCardCommand(Card card, Session session) {
        this.session = session;
        this.card = card;
    }

    @Override
    public void execute() {
        session.nextRole();
        card.reveal();
        switch (card.getColor()) {
            case Color.BLUE:
                session.getBlueTeam().addScore(1);
                break;
            case Color.RED:
                session.getRedTeam().addScore(1);
                break;
            default:
                break;
        }
        if (session.getCurrentColor() != card.getColor()) {
            session.setCurrentColor();
        }
    }

    @Override
    public void undo() {
        
    }

}

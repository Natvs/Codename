package eu.telecomnancy.codingweek.codenames.commands;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
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
        if (session.getCurrentColor() == card.getColor()) {
            session.getCurrentColoredTeam().getCluesList().getLast().countDown();
            if (session.getCurrentColoredTeam().getCluesList().getLast().getCount() == 0) {
                session.nextRole();
                RootController.getInstance().changeView("/views/transition.fxml");
            }
        }
        else {
            session.nextRole();
            RootController.getInstance().changeView("/views/transition.fxml");
        }

        card.reveal();
        switch (card.getColor()) {
            case Color.BLUE -> session.getBlueTeam().addScore(1);
            case Color.RED -> session.getRedTeam().addScore(1);
            default -> {}
        }
    }

    @Override
    public void undo() {
        
    }

}

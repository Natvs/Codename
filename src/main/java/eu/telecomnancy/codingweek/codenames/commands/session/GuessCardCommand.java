package eu.telecomnancy.codingweek.codenames.commands.session;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.application.Platform;

public class GuessCardCommand extends SessionCommand {
    
    private final Card card;

    public GuessCardCommand(Card card, Session session) {
        super(session);
        this.card = card;
    }

    @Override
    public void execute() {
        Platform.runLater(( ) -> {
            if (session.getConfig().getTimer()) {
                session.getTimerService().cancel();
            }
        } );
        if (card == null) {
            session.nextRole();
            RootController.getInstance().changeView("/views/transition.fxml");
            return;
        }  
        card.reveal();
        switch (card.getColor()) {
            case BLUE -> session.getBlueTeam().addScore(1);
            case RED -> session.getRedTeam().addScore(1);
            default -> {}
        }
        if (session.getCurrentColor() == card.getColor()) {
            
        } else {
            session.nextRole();
            RootController.getInstance().changeView("/views/transition.fxml");
        }

        checkEnd();
    }

    private void checkEnd() {
        if (session.getBoard().getRemainingCards(Color.RED) == 0) {
            session.setCurrentColor(Color.RED);
            RootController.getInstance().changeView("/views/end.fxml");
        }
        else if (session.getBoard().getRemainingCards(Color.BLUE) == 0) {
            session.setCurrentColor(Color.BLUE);
            RootController.getInstance().changeView("/views/end.fxml");
        }
    }

}

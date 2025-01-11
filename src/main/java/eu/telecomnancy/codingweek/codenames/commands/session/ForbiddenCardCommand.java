package eu.telecomnancy.codingweek.codenames.commands.session;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.application.Platform;

public class ForbiddenCardCommand extends SessionCommand {
    private Card card;

    public ForbiddenCardCommand(Session session, Card card) {
        super(session);
        this.card = card;
    }

    @Override
    public void execute() {
        card.reveal();
        Platform.runLater(( ) -> {
            if (session.getConfig().getTimer()) {
                session.getTimerService().cancel();
            }
        } );
        session.nextRole();
        RootController.getInstance().changeView("/views/end.fxml");
    }

}

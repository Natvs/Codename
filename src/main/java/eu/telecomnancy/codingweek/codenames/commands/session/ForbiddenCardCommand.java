package eu.telecomnancy.codingweek.codenames.commands.session;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.application.Platform;

public class ForbiddenCardCommand extends SessionCommand {

    public ForbiddenCardCommand(Session session) {
        super(session);
    }

    @Override
    public void execute() {
        Platform.runLater(( ) -> {
            if (session.getActiveTimer()) {
                session.getTimerService().cancel();
            }
        } );
        session.nextRole();
        RootController.getInstance().changeView("/views/end.fxml");
    }

}

package eu.telecomnancy.codingweek.codenames.commands;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.application.Platform;

public class ForbiddenCardCommand implements Command {
    
    private Session session;

    public ForbiddenCardCommand(Session session) {
        this.session = session;
    }

    @Override
    public void execute() {
        Platform.runLater(( ) -> {
            if (session.getActiveTimer()) {
                session.getTask().cancel();
            }
        } );
        session.nextRole();
        RootController.getInstance().changeView("/views/end.fxml");
    }

}

package eu.telecomnancy.codingweek.codenames.commands;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class ForbiddenCardCommand implements Command {
    
    private Session session;

    public ForbiddenCardCommand(Session session) {
        this.session = session;
    }

    @Override
    public void execute() {
        session.nextRole();
        RootController.getInstance().changeView("/views/end.fxml");
    }

}

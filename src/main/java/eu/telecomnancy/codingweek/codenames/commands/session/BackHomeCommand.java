package eu.telecomnancy.codingweek.codenames.commands.session;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class BackHomeCommand extends SessionCommand {
    
    public BackHomeCommand(Session session) {
        super(session);
    }

    @Override
    public void execute() {
        RootController.getInstance().changeView("/views/home.fxml");
        session.getService().cancel();
    }

}

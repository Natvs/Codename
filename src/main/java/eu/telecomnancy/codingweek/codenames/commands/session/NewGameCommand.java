package eu.telecomnancy.codingweek.codenames.commands.session;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class NewGameCommand extends SessionCommand {

    public NewGameCommand(Session session) {
        super(session);
    }

    @Override
    public void execute() {
        session.initialize();
        RootController.getInstance().changeView("/views/transition.fxml");
    }

}

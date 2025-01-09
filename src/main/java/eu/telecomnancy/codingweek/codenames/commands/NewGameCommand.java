package eu.telecomnancy.codingweek.codenames.commands;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class NewGameCommand implements Command {
    
    private Session session;

    public NewGameCommand(Session session) {
        this.session = session;
    }

    @Override
    public void execute() {
        RootController.getInstance().changeView("/views/transition.fxml");
    }

    @Override
    public void undo() {

    }

}

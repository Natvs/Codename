package eu.telecomnancy.codingweek.codenames.commands;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class SetClueCommand implements Command {
    
    private final Clue clue;
    private final Session session;

    public SetClueCommand(Clue clue, Session session) {
        this.clue = clue;
        this.session = session;
    }

    @Override
    public void execute() {
        switch (session.getCurrentColor()) {
            case Color.BLUE -> session.getBlueTeam().addClue(clue);
            case Color.RED -> session.getRedTeam().addClue(clue);
            default -> {}
        }
        session.nextRole();
        RootController.getInstance().changeView("/views/transition.fxml");
    }

}

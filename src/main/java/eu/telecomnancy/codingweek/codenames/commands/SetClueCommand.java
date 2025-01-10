package eu.telecomnancy.codingweek.codenames.commands;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.application.Platform;

public class SetClueCommand implements Command {
    
    private final Clue clue;
    private final Session session;

    public SetClueCommand(Clue clue, Session session) {
        this.clue = clue;
        this.session = session;
    }

    @Override
    public void execute() {
        Platform.runLater(( ) -> {
            if (session.getActiveTimer()) {
                session.getService().cancel();
            }
        } );
        if (clue == null) {
            session.getCurrentColoredTeam().addClue(new Clue("Pas d'indice", 1));
        }
        else {
            session.getCurrentColoredTeam().addClue(clue);
        }
        session.nextRole();
        RootController.getInstance().changeView("/views/transition.fxml");
    }

}

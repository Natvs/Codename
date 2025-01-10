package eu.telecomnancy.codingweek.codenames.commands.session;

import eu.telecomnancy.codingweek.codenames.controller.RootController;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.application.Platform;

public class SetClueCommand extends SessionCommand {
    
    private final Clue clue;

    public SetClueCommand(Clue clue, Session session) {
        super(session);
        this.clue = clue;
    }

    @Override
    public void execute() {
        Platform.runLater(( ) -> {
            if (session.getActiveTimer()) {
                session.getTimerService().cancel();
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

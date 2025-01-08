package eu.telecomnancy.commands;

import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public class SetClueCommand implements Command {
    
    private final Clue clue;
    private final Session session;

    public SetClueCommand(Clue clue, Session session) {
        this.clue = clue;
        this.session = session;
    }

    public void execute() {
        session.addClue(clue);
    }

    public void undo() {

    }

}

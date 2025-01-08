package eu.telecomnancy.codingweek.codenames.commands;

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
        session.nextRole();
        switch (session.getCurrentColor()) {
            case Color.BLUE:
                session.getBlueTeam().addClue(clue);
                break;
            case Color.RED:
                session.getRedTeam().addClue(clue);
                break;
            default:
                break;
        }
    }

    @Override
    public void undo() {

    }

}

package eu.telecomnancy.codingweek.codenames.commands.session;

import eu.telecomnancy.codingweek.codenames.commands.Command;
import eu.telecomnancy.codingweek.codenames.model.game.Session;

public abstract class SessionCommand implements Command {
    
    protected final Session session;
    
    public SessionCommand(Session session) {
        this.session = session;
    }

}

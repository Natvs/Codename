package eu.telecomnancy.codingweek.codenames.model.team;

import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class Team {
    
    private Color color;
    private List<Player> playersList;
    private Session session;

    public Team(Color color, List<Player> playersList, Session session) {
        if ((color == Color.BLACK) || (color == Color.WHITE)) {
            this.color = null;
        } else {
            this.color = color;
        }
        this.playersList = playersList;
        this.session = session;
    }

    public Color getColor() {
        return this.color;
    }

    public List<Player> getPlayersList() {
        return this.playersList;
    }

    public Session getSession() {
        return this.session;
    }

}

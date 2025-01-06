package eu.telecomnancy.codingweek.codenames.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    
    private Color color;
    private List<Player> playersList = new ArrayList<>();
    private Session session;

    public Team(Color color, List<Player> playersList, Session session) {
        if ((color == Color.BLACK) || (color == Color.WHITE)) {
            this.color = color;
        } else {
            this.color = null;
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

}

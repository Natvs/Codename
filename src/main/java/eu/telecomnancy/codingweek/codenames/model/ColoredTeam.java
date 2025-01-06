package eu.telecomnancy.codingweek.codenames.model;

import java.util.ArrayList;
import java.util.List;

public class ColoredTeam {
    
    private Color color;
    private Session session;
    private List<Clue> cluesList = new ArrayList<>();

    public ColoredTeam(Color color, Session session) {
        this.color = color;
        this.session = session;
    }

    public Color getColor() {
        return this.color;
    }

    public List<Clue> getCluesList() {
        return this.cluesList;
    }

    public void addClue(Clue clue) {
        this.cluesList.add(clue);
    }

}

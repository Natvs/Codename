package eu.telecomnancy.codingweek.codenames.model.coloredTeam;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.team.AgentTeam;
import eu.telecomnancy.codingweek.codenames.model.team.Clue;
import eu.telecomnancy.codingweek.codenames.model.team.SpyTeam;

public class ColoredTeam {
    
    private Color color;
    private Session session;
    private List<Clue> cluesList = new ArrayList<>();
    private AgentTeam agentTeam;
    private SpyTeam spyTeam;

    public ColoredTeam(Color color, Session session) {
        this.color = color;
        this.session = session;
    }

    public Color getColor() {
        return this.color;
    }

    public Session getSession() {
        return this.session;
    }

    public List<Clue> getCluesList() {
        return this.cluesList;
    }

    public AgentTeam getAgentTeam() {
        return this.agentTeam;
    }

    public SpyTeam getSpyTeam() {
        return this.spyTeam;
    }

}

package eu.telecomnancy.codingweek.codenames.model.coloredTeam;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.team.AgentTeam;
import eu.telecomnancy.codingweek.codenames.model.team.SpyTeam;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class ColoredTeam {
    
    private Color color;
    private List<Clue> cluesList;
    private AgentTeam agentTeam;
    private SpyTeam spyTeam;

    public ColoredTeam(Color color, List<Player> agentsTeam, List<Player> spiesTeam) {
        if ((color == Color.BLACK) || (color == Color.WHITE)) {
            this.color = Color.NULL;
        } else {
            this.color = color;
        }
        this.cluesList = new ArrayList<>();
        this.agentTeam = new AgentTeam(agentsTeam, cluesList);
        this.spyTeam = new SpyTeam(spiesTeam, cluesList);
    }

    public Color getColor() {
        return this.color;
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

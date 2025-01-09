package eu.telecomnancy.codingweek.codenames.model.coloredTeam;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.player.Player;
import eu.telecomnancy.codingweek.codenames.model.team.AgentTeam;
import eu.telecomnancy.codingweek.codenames.model.team.SpyTeam;

public class ColoredTeam {
    
    private final Color color;
    private final List<Clue> cluesList;
    private final AgentTeam agentTeam;
    private final SpyTeam spyTeam;
    private int score = 0;

    public ColoredTeam(Color color, List<Player> agentsTeam, List<Player> spiesTeam) {
        if ((color == Color.BLACK) || (color == Color.WHITE)) {
            this.color = Color.NULL;
        } else {
            this.color = color;
        }
        this.cluesList = new ArrayList<>();
        this.agentTeam = new AgentTeam(agentsTeam);
        this.spyTeam = new SpyTeam(spiesTeam);
    }
    public ColoredTeam(@JsonProperty("color") Color color, @JsonProperty("cluesList") List<Clue> cluesList, @JsonProperty("agentTeam") AgentTeam agentTeam, @JsonProperty("spyTeam") SpyTeam spyTeam) {
        this.color = color;
        this.cluesList = cluesList;
        this.agentTeam = agentTeam;
        this.spyTeam = spyTeam;
    }

    public void clone(ColoredTeam target) {
        this.score = target.getScore();
        this.cluesList.clear();
        this.cluesList.addAll(target.getCluesList());
        this.agentTeam.clone(target.getAgentTeam());
        this.spyTeam.clone(target.getSpyTeam());
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

    public int getScore() {
        return this.score;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void addClue(Clue clue) {
        getCluesList().add(clue);
    }

}

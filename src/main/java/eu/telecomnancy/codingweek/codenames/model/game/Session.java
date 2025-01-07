package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;
import eu.telecomnancy.codingweek.codenames.model.team.AgentTeam;
import eu.telecomnancy.codingweek.codenames.model.team.SpyTeam;
import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.color.Color;

public class Session {
    
    private ColoredTeam redTeam;
    private ColoredTeam blueTeam;
    private Board board;

    public Session(GameConfig config) {
        AgentTeam redAgentsList = new AgentTeam(config.redAgentsList);
        SpyTeam redSpiesList = new SpyTeam(config.redSpiesList);
        this.redTeam = new ColoredTeam(Color.RED, redAgentsList, redSpiesList);

        AgentTeam blueAgentsList = new AgentTeam(config.blueAgentsList);
        SpyTeam blueSpiesList = new SpyTeam(config.blueSpiesList);
        this.blueTeam = new ColoredTeam(Color.BLUE, blueAgentsList, blueSpiesList);

        this.board = new Board(config.width, config.height);
    }

    public ColoredTeam getRedTeam() {
        return this.redTeam;
    }

    public ColoredTeam getBlueTeam() {
        return this.blueTeam;
    }

    public Board getBoard() {
        return this.board;
    }

}

package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;
import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.color.Color;

public class Session {
    
    private static Session instance;

    private ColoredTeam redTeam;
    private ColoredTeam blueTeam;
    private Board board;
    private GameConfig config;


    static public Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    private Session() {
        GameConfig config = new GameConfig();
        this.config = config;

        AgentTeam redAgentTeam = new AgentTeam(config.redAgentTeam);
        SpyTeam redSpyTeam = new SpyTeam(config.redSpyTeam);
        this.redTeam = new ColoredTeam(Color.RED, redAgentTeam, redSpyTeam);

        AgentTeam blueAgentTeam = new AgentTeam(config.blueAgentTeam);
        SpyTeam blueSpyTeam = new SpyTeam(config.blueSpyTeam);
        this.blueTeam = new ColoredTeam(Color.BLUE, blueAgentTeam, blueSpyTeam);

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

    public GameConfig getConfig() {
        return this.config;
    }

}

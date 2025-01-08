package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;

public class Session {
    
    private static Session instance;

    private ColoredTeam redTeam;
    private ColoredTeam blueTeam;
    private Color currentColor;
    private boolean agent = true;
    private Board board;
    private GameConfig config;

    static public Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    private Session() {
        this.config = new GameConfig();
        this.config = new GameConfig();
        this.redTeam = new ColoredTeam(Color.RED, config.redAgentsList, config.redSpiesList);
        this.blueTeam = new ColoredTeam(Color.BLUE, config.blueAgentsList, config.blueSpiesList);
        this.board = new Board(config.heigth, config.width);
        if (board.getRemainingCards(Color.BLUE) > board.getRemainingCards(Color.RED)) {
            currentColor = Color.BLUE;
        }
        else {
            currentColor = Color.RED;
        }
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

    public Color getCurrentColor() {
        return this.currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public boolean isAgent() {
        return this.agent;
    }

    public void changeRole(boolean agent) {
        this.agent = agent;
    }

}

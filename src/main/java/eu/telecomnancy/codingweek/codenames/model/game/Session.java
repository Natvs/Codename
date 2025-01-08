package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;

public class Session {

    private GameConfig config;
    private final  ColoredTeam redTeam;
    private final  ColoredTeam blueTeam;
    private final Board board;
    private Color currentColor;
    private Boolean agent = true;

    public Session() {
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

    public void guessCard(Card card) {
        card.reveal();
        switch (card.getColor()) {
            case Color.BLUE:
                getBlueTeam().addScore(1);
                break;
            case Color.RED:
                getRedTeam().addScore(1);
                break;
            default:
                throw new AssertionError();
        }
    }


    public void addClue(Clue clue) {
        switch (getCurrentColor()) {
            case Color.BLUE:
                getBlueTeam().addClue(clue);
                break;
            case Color.RED:
                getRedTeam().addClue(clue);
                break;
            default:
                break;
        }
    }

}

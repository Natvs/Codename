package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;
import eu.telecomnancy.codingweek.codenames.observers.game.SessionColorObserver;
import eu.telecomnancy.commands.Executer;
import eu.telecomnancy.commands.GuessCardCommand;
import eu.telecomnancy.commands.SetClueCommand;

public class Session {

    private GameConfig config;
    private final  ColoredTeam redTeam;
    private final  ColoredTeam blueTeam;
    private final Board board;
    private Color currentColor;
    private Boolean agent = true;
    private Executer executer = new Executer();

    private SessionColorObserver colorObserver = null;

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
        if (colorObserver != null) {
            colorObserver.handle();
        }
    }

    public boolean isAgent() {
        return this.agent;
    }

    public void changeRole(boolean agent) {
        this.agent = agent;
    }

    public void setColorObserver(SessionColorObserver observer) {
        this.colorObserver = observer;
    }

    public Executer getExecuter() {
        return this.executer;
    }

    public void guessCard(Card card) {
        getExecuter().addCommand(new GuessCardCommand(card, this));
        getExecuter().executeAll();
    }

    public void setNextTeamColor() {
        switch (getCurrentColor()) {
            case Color.BLUE:
                setCurrentColor(Color.RED);
                break;
            case Color.RED:
                setCurrentColor(Color.BLUE);
                break;
            default:
                throw new AssertionError();
        }
    }
    public void addClue(Clue clue) {
        getExecuter().addCommand(new SetClueCommand(clue, this));
        getExecuter().executeAll();
    }


}

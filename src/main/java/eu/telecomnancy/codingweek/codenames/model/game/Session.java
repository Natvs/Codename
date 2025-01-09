package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.commands.Executer;
import eu.telecomnancy.codingweek.codenames.commands.GuessCardCommand;
import eu.telecomnancy.codingweek.codenames.commands.SetClueCommand;
import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import eu.telecomnancy.codingweek.codenames.observers.game.ColorSetObserver;
import eu.telecomnancy.codingweek.codenames.observers.game.RoleSetObserver;

public class Session {

    private GameConfig config;
    private final  ColoredTeam redTeam;
    private final  ColoredTeam blueTeam;
    private final Board board;
    private Color currentColor;
    private Boolean agent = true;
    private Executer executer = new Executer();

    private RoleSetObserver roleObserver = null;
    private ColorSetObserver colorObserver = null;

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

    public ColoredTeam getCurrentColoredTeam() {
        return switch (getCurrentColor()) {
            case Color.RED -> getRedTeam();
            case Color.BLUE -> getBlueTeam() ;
            default -> null;
        };
    }
    public Team getCurrentTeam() {
        var coloredTeam = switch (getCurrentColor()) {
            case Color.BLUE -> getBlueTeam();
            case Color.RED -> getRedTeam();
            default -> null;
        };
        if (coloredTeam != null) {
            if (isAgent()) return coloredTeam.getAgentTeam();
            else return coloredTeam.getSpyTeam();
        }
        return null;
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

    public void setCurrentColor() {
        if (this.currentColor == Color.RED){
            this.currentColor = Color.BLUE;
        } else {
            this.currentColor = Color.RED;
        }
        if (colorObserver != null) {
            colorObserver.handle();
        }
    }

    public void setRoleObserver(RoleSetObserver observer) {
        this.roleObserver = observer;
    }

    public void setColorObserver(ColorSetObserver observer) {
        this.colorObserver = observer;
    }

    public boolean isAgent() {
        return this.agent;
    }

    public void changeRole(boolean agent) {
        this.agent = agent;
        if (roleObserver != null) {
            roleObserver.handle();
        }
    }

    public void nextRole() {
        if (isAgent()){
            changeRole(false);
        } else {
            setCurrentColor();
            changeRole(true);
        }
    }

    public Executer getExecuter() {
        return this.executer;
    }

    public void guessCard(Card card) {
        getExecuter().addCommand(new GuessCardCommand(card, this));
        getExecuter().executeAll();
    }

    public void addClue(Clue clue) {
        getExecuter().addCommand(new SetClueCommand(clue, this));
        getExecuter().executeAll();
    }


}

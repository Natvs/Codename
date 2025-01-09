package eu.telecomnancy.codingweek.codenames.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import eu.telecomnancy.codingweek.codenames.commands.Executer;
import eu.telecomnancy.codingweek.codenames.commands.ForbiddenCardCommand;
import eu.telecomnancy.codingweek.codenames.commands.GuessCardCommand;
import eu.telecomnancy.codingweek.codenames.commands.NewGameCommand;
import eu.telecomnancy.codingweek.codenames.commands.SetClueCommand;
import eu.telecomnancy.codingweek.codenames.model.board.Board;
import eu.telecomnancy.codingweek.codenames.model.board.Card;
import eu.telecomnancy.codingweek.codenames.model.clue.Clue;
import eu.telecomnancy.codingweek.codenames.model.color.Color;
import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;
import eu.telecomnancy.codingweek.codenames.model.team.Team;
import eu.telecomnancy.codingweek.codenames.observers.game.ColorSetObserver;
import eu.telecomnancy.codingweek.codenames.observers.game.RoleSetObserver;
import eu.telecomnancy.codingweek.codenames.observers.game.TimeObserver;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class Session {

    private GameConfig config;
    private final  ColoredTeam redTeam;
    private final  ColoredTeam blueTeam;
    private final Board board;
    private Color currentColor;
    private Boolean agent = true;
    private Executer executer = new Executer();
    private ScheduledService<Void> service;
    private int time;
    private RoleSetObserver roleObserver = null;
    private ColorSetObserver colorObserver = null;
    private TimeObserver timeObserver = null;
    private boolean activeTimer = true;

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

    public void clone(Session target) {
        this.config = target.getConfig();
        this.redTeam.clone(target.getRedTeam());
        this.blueTeam.clone(target.getBlueTeam());
        this.board.clone(target.getBoard());
        this.currentColor = target.getCurrentColor();
        this.agent = target.isAgent();
    }

    public ColoredTeam getRedTeam() {
        return this.redTeam;
    }

    public ColoredTeam getBlueTeam() {
        return this.blueTeam;
    }

    @JsonIgnore
    public ColoredTeam getCurrentColoredTeam() {
        return switch (getCurrentColor()) {
            case Color.RED -> getRedTeam();
            case Color.BLUE -> getBlueTeam() ;
            default -> null;
        };
    }

    @JsonIgnore
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

    public boolean isAgent() {
        return this.agent;
    }

    private void setCurrentColor() {
        if (this.currentColor == Color.RED){
            this.currentColor = Color.BLUE;
        } else {
            this.currentColor = Color.RED;
        }
        if (colorObserver != null) {
            colorObserver.handle();
        }
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
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

    public void setTimeObserver(TimeObserver observer) {
        this.timeObserver = observer;
    }

    @JsonIgnore
    private void changeRole(boolean agent) {
        this.agent = agent;
        if (roleObserver != null) {
            roleObserver.handle();
        }
    }

    @JsonIgnore
    public void nextRole() {
        if (isAgent()){
            changeRole(false);
        } else {
            setCurrentColor();
            changeRole(true);
        }
    }

    @JsonIgnore
    public Executer getExecuter() {
        return this.executer;
    }

    public void startNewGame() {
        getExecuter().addCommand(new NewGameCommand(this));
        getExecuter().executeAll();
    }

    public void guessCard(Card card) {
        if (card != null && card.getColor() == Color.BLACK) getExecuter().addCommand(new ForbiddenCardCommand(this));
        else getExecuter().addCommand(new GuessCardCommand(card, this));
        getExecuter().executeAll();
    }

    @JsonIgnore
    public void addClue(Clue clue) {
        getExecuter().addCommand(new SetClueCommand(clue, this));
        getExecuter().executeAll();
    }

    public boolean getActiveTimer() {
        return activeTimer;
    }

    public void setActiveTimer(boolean activeTimer){
        this.activeTimer = activeTimer;
    }

    @JsonIgnore
    public ScheduledService<Void> getTimer(){
        return this.service;
    }

    public void setTimer() {
        // Create a ScheduledService to run periodically
        time = 0;
        service = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        // Update the UI from the background task
                        for (int i=0;i<60;i++){
                            Platform.runLater(() -> {
                                time ++;
                                if (timeObserver != null) {
                                    timeObserver.handle();
                                }
                            });
                            Thread.sleep(1_000);
                        }
                        Platform.runLater(() -> {
                            if (activeTimer){
                                nextRole();
                                time=0;
                                this.cancel();
                            }
                        });
                        return null;
                    }
                };
            }
        };
        //service.setPeriod(Duration.seconds(10));
    }
    public int getTime(){
        return this.time;
    }
    public void resetTime(){
        this.time=0;
    }
}

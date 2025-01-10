package eu.telecomnancy.codingweek.codenames.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import eu.telecomnancy.codingweek.codenames.commands.Executer;
import eu.telecomnancy.codingweek.codenames.commands.session.BackHomeCommand;
import eu.telecomnancy.codingweek.codenames.commands.session.ForbiddenCardCommand;
import eu.telecomnancy.codingweek.codenames.commands.session.GuessCardCommand;
import eu.telecomnancy.codingweek.codenames.commands.session.NewGameCommand;
import eu.telecomnancy.codingweek.codenames.commands.session.SetClueCommand;
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
import eu.telecomnancy.codingweek.codenames.music.Music;

public class Session {

    private Music music;

    private GameConfig config;
    private final ColoredTeam redTeam;
    private final ColoredTeam blueTeam;
    private final Board board;
    private Color currentColor;
    private Boolean agent = true;
    private final Executer executer = new Executer();


    private RoleSetObserver roleObserver = null;
    private ColorSetObserver colorObserver = null;
    private TimeObserver timeObserver = null;

    private ScheduledService<Void> timerService;
    private int time;
    private boolean activeTimer = true;

    public Session() {
        this.config = new GameConfig();
        this.redTeam = new ColoredTeam(Color.RED, config.redAgentsList, config.redSpiesList);
        this.blueTeam = new ColoredTeam(Color.BLUE, config.blueAgentsList, config.blueSpiesList);
        this.board = new Board(config.heigth, config.width);
        initialize();
        setTimerService();
        music = new Music();
        music.NewMusic("/music/apt.mp3");
    }

    public void initialize() {
        agent = true;
        if (board.getRemainingCards(Color.BLUE) > board.getRemainingCards(Color.RED)) {
            currentColor = Color.BLUE;
        }
        else {
            currentColor = Color.RED;
        }
    }

    private void setTimerService() {
        time = 0;
        Session session = this;
        timerService = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        // Update the UI from the background task
                        int timeMax = session.isAgent() ? session.getConfig().timerAgent*10 : session.getConfig().timerSpy*10;
                        for (int i=0;i<timeMax ;i++){
                            Platform.runLater(() -> {
                                time --;
                                if (timeObserver != null) {
                                    timeObserver.handle();
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e){
                                e.getStackTrace();
                            }
                            if (isCancelled()) {
                                break;
                            }
                            if (time == 0) {
                                Platform.runLater(() -> {
                                    if (activeTimer){
                                        if (session.isAgent()) getExecuter().addCommand(new SetClueCommand(null, session));
                                        else getExecuter().addCommand(new GuessCardCommand(null, session));
                                        getExecuter().executeAll();
                                        time=0;
                                    }
                                });
                            }
                         }
                        return null;
                    }
                };
            }
        };
        //service.setPeriod(Duration.seconds(10));
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

    public void setConfig(GameConfig config) {
        this.config = config;
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

    public void quitGame() {
        getExecuter().addCommand(new BackHomeCommand(this));
        getExecuter().executeAll();
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
    public ScheduledService<Void> getTimerService(){
        return this.timerService;
    }

    public int getTime(){
        return this.time;
    }
    public void resetTime(){
        this.time = isAgent() ? getConfig().timerAgent*10 : getConfig().timerSpy*10;
    }
    public Music getMusic() {
        return music;
    }
}

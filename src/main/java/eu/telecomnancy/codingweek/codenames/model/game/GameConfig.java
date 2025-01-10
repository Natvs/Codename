package eu.telecomnancy.codingweek.codenames.model.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.player.Player;
import eu.telecomnancy.codingweek.codenames.observers.newconfig.ImageModeObserver;
import eu.telecomnancy.codingweek.codenames.observers.newconfig.TimerObserver;

public class GameConfig {

    public int heigth = 5;
    public int width = 5;
    public List<Player> blueAgentsList = new ArrayList<>(Arrays.asList(new Player("joueur 1"), new Player("joueur 2")));
    public List<Player> blueSpiesList = new ArrayList<>(Arrays.asList(new Player("joueur 3"), new Player("joueur 4")));
    public List<Player> redAgentsList = new ArrayList<>(Arrays.asList(new Player("joueur 5"), new Player("joueur 6")));
    public List<Player> redSpiesList = new ArrayList<>(Arrays.asList(new Player("joueur 7"), new Player("joueur 8")));
    public int agentTime = 40;
    public int spyTime = 30;
    private boolean timer = true;
    private boolean imageMode = false;

    private ImageModeObserver imageModeObserver = null;
    private TimerObserver timerObserver = null;

    public void setImageMode(boolean imageMode) {
        this.imageMode = imageMode;
        if (imageModeObserver != null) {
            imageModeObserver.handle();
        }
    }
    public boolean getImageMode() {
        return this.imageMode;
    }

    public void setTimer(boolean timer) {
        this.timer = timer;
        if (timerObserver != null) {
            timerObserver.handle();
        }
    }
    public boolean getTimer() {
        return this.timer;
    }

    public void setImageModeObserver(ImageModeObserver observer) {
        this.imageModeObserver = observer;
    }
    public void setTimerObserver(TimerObserver observer) {
        this.timerObserver = observer;
    }

}

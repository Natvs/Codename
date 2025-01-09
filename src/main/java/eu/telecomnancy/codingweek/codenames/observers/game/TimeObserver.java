package eu.telecomnancy.codingweek.codenames.observers.game;

import eu.telecomnancy.codingweek.codenames.controller.GameHeaderController;

public class TimeObserver {
    private GameHeaderController controller;
    public TimeObserver(GameHeaderController controller) {
        this.controller = controller;
    }

    public void handle(){
        controller.setTimeLabel();
    }
}

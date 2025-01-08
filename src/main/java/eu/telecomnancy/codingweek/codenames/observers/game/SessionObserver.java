package eu.telecomnancy.codingweek.codenames.observers.game;

import eu.telecomnancy.codingweek.codenames.controller.GameController;
import eu.telecomnancy.codingweek.codenames.observers.Observer;

public abstract  class SessionObserver implements Observer {
    
    protected GameController controller;

    public SessionObserver(GameController controller) {
        this.controller = controller;
    }

}

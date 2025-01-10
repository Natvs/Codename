package eu.telecomnancy.codingweek.codenames.observers.newconfig;

import eu.telecomnancy.codingweek.codenames.controller.NewConfigController;
import eu.telecomnancy.codingweek.codenames.observers.Observer;

public abstract class NewConfigObserver implements Observer {
    
    protected final NewConfigController controller;

    public NewConfigObserver(NewConfigController controller) {
        this.controller = controller;
    }

}

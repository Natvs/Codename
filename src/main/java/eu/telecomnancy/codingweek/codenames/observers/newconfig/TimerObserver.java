package eu.telecomnancy.codingweek.codenames.observers.newconfig;

import eu.telecomnancy.codingweek.codenames.controller.NewConfigController;

public class TimerObserver extends NewConfigObserver {
    
    public TimerObserver(NewConfigController controller) {
        super(controller);
    }

    @Override
    public void handle() {
        controller.checkTimers();
    }

}

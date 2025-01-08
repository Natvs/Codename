package eu.telecomnancy.codingweek.codenames.observers.board;

import eu.telecomnancy.codingweek.codenames.controller.GameCardController;
import eu.telecomnancy.codingweek.codenames.observers.Observer;;;

public class CardNameObserver implements Observer {

    private GameCardController controller;

    public CardNameObserver(GameCardController controller) {
        this.controller = controller;
    }

    @Override
    public void handle() {
        controller.setName();
    }
    
}

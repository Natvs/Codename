package eu.telecomnancy.codingweek.codenames.observers.board;

import eu.telecomnancy.codingweek.codenames.controller.GameCardController;
import eu.telecomnancy.codingweek.codenames.observers.Observer;

public class CardColorObserver implements Observer {

    private final GameCardController controller;

    public CardColorObserver(GameCardController controller) {
        this.controller = controller;
    }

    @Override
    public void handle() {
        controller.setColor();
    }

}
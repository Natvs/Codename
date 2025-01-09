package eu.telecomnancy.codingweek.codenames.observers.board;

import eu.telecomnancy.codingweek.codenames.controller.GameCardController;

public class CardColorObserver extends CardObserver {

    public CardColorObserver(GameCardController controller) {
        super(controller);
    }

    @Override
    public void handle() {
        controller.setColor();
    }

}
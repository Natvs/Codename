package eu.telecomnancy.codingweek.codenames.observers.board;

import eu.telecomnancy.codingweek.codenames.controller.GameCardController;

public class CardNameObserver extends CardObserver {

    public CardNameObserver(GameCardController controller) {
        super(controller);
    }

    @Override
    public void handle() {
        controller.setName();
    }
    
}

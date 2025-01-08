package eu.telecomnancy.codingweek.codenames.observers.board;

import eu.telecomnancy.codingweek.codenames.controller.GameCardController;

public class CardRevealedObserver extends CardObserver {
    
    public CardRevealedObserver(GameCardController controller) {
        super(controller);
    }

    @Override
    public void handle() {
        controller.setColor();
    }

}

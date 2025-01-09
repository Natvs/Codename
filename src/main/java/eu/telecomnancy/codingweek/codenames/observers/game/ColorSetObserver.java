package eu.telecomnancy.codingweek.codenames.observers.game;

import eu.telecomnancy.codingweek.codenames.controller.GameController;

public class ColorSetObserver extends SessionObserver {
    
    public ColorSetObserver(GameController controller) {
        super(controller);
    }

    @Override
    public void handle() {
        controller.setHeader();
        controller.setCardsBoard();
    }

}

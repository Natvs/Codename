package eu.telecomnancy.codingweek.codenames.observers.game;

import eu.telecomnancy.codingweek.codenames.controller.GameController;

public class RoleSetObserver extends SessionObserver {

    public RoleSetObserver(GameController controller) {
        super(controller);
    }

    @Override
    public void handle() {
        controller.setLabel();
        controller.setCardsBoard();
        controller.setFooter();
    }

}

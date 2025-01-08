package eu.telecomnancy.codingweek.codenames.observers.board;

import eu.telecomnancy.codingweek.codenames.controller.GameCardController;
import eu.telecomnancy.codingweek.codenames.observers.Observer;

public abstract class CardObserver implements  Observer {

    protected final GameCardController controller;

    public CardObserver(GameCardController controller) {
        this.controller = controller;
    }
    
}

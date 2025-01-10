package eu.telecomnancy.codingweek.codenames.observers.newconfig;

import eu.telecomnancy.codingweek.codenames.controller.NewConfigController;

public class ImageModeObserver extends NewConfigObserver {
    
    public ImageModeObserver(NewConfigController controller) {
        super(controller);
    }

    @Override
    public void handle() {
        controller.setMaxBoardSize();
    }

}

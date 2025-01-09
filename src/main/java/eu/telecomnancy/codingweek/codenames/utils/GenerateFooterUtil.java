package eu.telecomnancy.codingweek.codenames.utils;

import java.net.URL;

import eu.telecomnancy.codingweek.codenames.controller.GameFooterController;
import eu.telecomnancy.codingweek.codenames.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class GenerateFooterUtil {
    public static Node generateFooter(GameController gameController, boolean isAgent) {
        URL fxmlURL;
        if (isAgent){
            fxmlURL = GenerateCardUtil.class.getResource("/views/components/gameFooterAgent.fxml");
            if (fxmlURL == null) {
                System.err.println("Could not find /views/components/gameFooterAgent.fxml");
                return null;
            }
        } else {
            fxmlURL = GenerateCardUtil.class.getResource("/views/components/gameFooterSpy.fxml");
            if (fxmlURL == null) {
                System.err.println("Could not find /views/components/gameFooterSpy.fxml");
                return null;
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            loader.setControllerFactory(iC -> new GameFooterController(gameController,isAgent));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

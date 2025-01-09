package eu.telecomnancy.codingweek.codenames.utils;

import java.net.URL;
import eu.telecomnancy.codingweek.codenames.controller.GameHeaderController;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class GenerateHeaderUtil {
    public static Node generateHeader(Session session) {
        URL fxmlURL;
        fxmlURL = GenerateCardUtil.class.getResource("/views/components/gameHeader.fxml");
        if (fxmlURL == null) {
            System.err.println("Could not find /views/components/gameHeader.fxml");
            return null;
        }
        try {
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            loader.setControllerFactory(iC -> new GameHeaderController(session));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

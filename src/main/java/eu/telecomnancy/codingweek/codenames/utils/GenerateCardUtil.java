package eu.telecomnancy.codingweek.codenames.utils;

import java.net.URL;

import eu.telecomnancy.codingweek.codenames.controller.GameCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class GenerateCardUtil {
    public static Node generateCard(String word) {
        URL fxmlURL = GenerateCardUtil.class.getResource("/views/components/gameCard.fxml");
        if (fxmlURL == null) {
            System.err.println("Could not find /views/components/gameCard.fxml");
            return null;
        }
        try {
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            loader.setControllerFactory(_ -> new GameCardController(word));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

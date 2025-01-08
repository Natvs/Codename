package eu.telecomnancy.codingweek.codenames.utils;

import java.net.URL;

import eu.telecomnancy.codingweek.codenames.controller.NewConfigController;
import eu.telecomnancy.codingweek.codenames.controller.PlayerFieldController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class GeneratePlayerField {
    public static Node generateField(String name, NewConfigController newConfigController, int column) {
        URL fxmlURL = GenerateCardUtil.class.getResource("/views/components/playerField.fxml");
        if (fxmlURL == null) {
            System.err.println("Could not find /views/components/playerField.fxml");
            return null;
        }
        try {
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            loader.setControllerFactory(iC -> new PlayerFieldController(name, newConfigController, column));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

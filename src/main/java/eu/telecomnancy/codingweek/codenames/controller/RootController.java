package eu.telecomnancy.codingweek.codenames.controller;

import java.io.IOException;
import java.net.URL;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.music.Music;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class RootController {
    private static RootController instance;
    private Node activeView;
    private Session session;
    private Music music;
    @FXML
    private BorderPane root;

    public RootController() {
        session = new Session();
        instance = this;
        // Tackle music
        music = new Music();
        music.NewMusic("/music/apt.mp3");
        session.setMusic(music);
    }

    @FXML
    private void initialize() {
        if (root == null) {
            throw new RuntimeException("BorderPane 'root' not injected by FXML loader");
        }
        changeView("/views/home.fxml");
    }

    public static RootController getInstance() {
        if (instance == null) {
            throw new RuntimeException("RootController not yet initialized by FXML loader");
        }
        return instance;
    }

    public void changeView(String fxml) {
        if (root == null) {
            throw new RuntimeException("BorderPane 'root' is null");
        }
        
        URL fxmlURL = getClass().getResource(fxml);
        if (fxmlURL == null) {
            System.err.println("Could not find " + fxml);
            return;
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            loader.setControllerFactory(iC -> {
                if (iC == HomeController.class) {
                    return new HomeController(session);
                } else if (iC == NewConfigController.class) {
                    return new NewConfigController(session);
                } else if (iC == GameController.class) {
                    return new GameController(session);
                } else if (iC == TransitionController.class) {
                    return new TransitionController(session);
                } else if (iC == EndController.class) {
                    return new EndController(session);
                } else if (iC == CreditController.class) {
                    return new CreditController(session);
                } else {
                    return null;
                }
            });
            activeView = loader.load();
            root.setCenter(activeView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
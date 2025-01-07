package eu.telecomnancy.codingweek.codenames.controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class RootController {
    private static RootController instance;
    private Node activeView;

    @FXML
    private BorderPane root;

    public RootController() {
        instance = this;
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
            activeView = FXMLLoader.load(fxmlURL);
            root.setCenter(activeView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
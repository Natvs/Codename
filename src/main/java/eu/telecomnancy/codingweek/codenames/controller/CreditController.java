package eu.telecomnancy.codingweek.codenames.controller;

import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.music.Music;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CreditController {
    private final Session session;
    @FXML
    private GridPane creditView;

    @FXML
    private ImageView imageView;
    public CreditController(Session session){
        this.session = session;
    }

    @FXML
    private void onRose(){
        Music music = session.getMusic();
        music.NewMusic("/music/apt.mp3");
    }
    
    @FXML
    private void onKrokmou(){
        Music music = session.getMusic();
        music.NewMusic("/music/pokemon.mp3");
    }

    @FXML
    private void onJames() {
        Music music = session.getMusic();
        music.NewMusic("/music/James.mp3");
    }

    @FXML
    private void onBack() {
        RootController.getInstance().changeView("/views/home.fxml");
    }
}
package eu.telecomnancy.codingweek.codenames.model.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class GameConfig {
    
    public int heigth = 5;
    public int width = 5;
    public List<Player> blueAgentsList = new ArrayList<>(Arrays.asList(new Player("joueur 1"), new Player("joueur 2")));
    public List<Player> blueSpiesList = new ArrayList<>(Arrays.asList(new Player("joueur 3"), new Player("joueur 4")));
    public List<Player> redAgentsList = new ArrayList<>(Arrays.asList(new Player("joueur 5"), new Player("joueur 6")));
    public List<Player> redSpiesList = new ArrayList<>(Arrays.asList(new Player("joueur 7"), new Player("joueur 8")));

}

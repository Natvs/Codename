package eu.telecomnancy.codingweek.codenames.model.game;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class GameConfig {
    
    public int length = 5;
    public int width = 5;
    public List<Player> blueAgentsList = new ArrayList<>();
    public List<Player> blueSpiesList = new ArrayList<>();
    public List<Player> redAgentsList = new ArrayList<>();
    public List<Player> redSpiesList = new ArrayList<>();

}

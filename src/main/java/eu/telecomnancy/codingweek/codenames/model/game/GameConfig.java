package eu.telecomnancy.codingweek.codenames.model.game;

import java.util.ArrayList;
import java.util.List;

import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class GameConfig {
    
    public int width;
    public int height;
    public List<Player> blueAgentTeam = new ArrayList<>();
    public List<Player> blueSpyTeam = new ArrayList<>();
    public List<Player> redAgentTeam = new ArrayList<>();
    public List<Player> redSpyTeam = new ArrayList<>();

}

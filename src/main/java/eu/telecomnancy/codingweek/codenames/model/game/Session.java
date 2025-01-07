package eu.telecomnancy.codingweek.codenames.model.game;

import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;

public class Session {
    
    private ColoredTeam redTeam;
    private ColoredTeam blueTeam;
    private Board board;

    public Session(GameConfig config) {
        // A_REVOIR
    }

    public ColoredTeam getRedTeam() {
        return this.redTeam;
    }

    public ColoredTeam getBlueTeam() {
        return this.blueTeam;
    }

    public Board getBoard() {
        return this.board;
    }

}

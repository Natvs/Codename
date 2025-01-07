package eu.telecomnancy.codingweek.codenames.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import eu.telecomnancy.codingweek.codenames.model.coloredTeam.ColoredTeam;
import eu.telecomnancy.codingweek.codenames.model.game.GameConfig;
import eu.telecomnancy.codingweek.codenames.model.game.Session;
import eu.telecomnancy.codingweek.codenames.model.player.Player;

public class testSaveFile {
    @Test
    void testSave() {
        String fileName = "src/main/resources/saves/save.json";
        setGameConfig();
        SaveFile.saveData(fileName);
        assertEquals(true, true);
    }

    @Test
    void testLoad() {
        // TODO do more tests
        String fileName = "src/main/resources/saves/save.json";
        SaveFile.erraseData(fileName);
        setGameConfig();
        SaveFile.saveData(fileName);
        Session session = Session.getInstance();
        ColoredTeam redTeam = session.getRedTeam();
        String luca = redTeam.getAgentTeam().getPlayersList().getFirst().getName();
        redTeam.getAgentTeam().getPlayersList().clear();
        redTeam.getAgentTeam().getPlayersList().add(new Player("Pierre"));
        SaveFile.loadData(fileName);
        assertEquals(luca,redTeam.getAgentTeam().getPlayersList().getFirst().getName());

        GameConfig gameConfig = session.getConfig();
        String lucas = gameConfig.blueAgentsList.getFirst().getName();
        gameConfig.blueAgentsList.clear();
        gameConfig.blueAgentsList.add(new Player("Pierre"));
        SaveFile.loadData(fileName);
        assertEquals(lucas,gameConfig.blueAgentsList.getFirst().getName());
    }

    @Test
    void testEraseData() {
        String fileName = "src/main/resources/saves/save.json";
        setGameConfig();
        SaveFile.saveData(fileName);
        SaveFile.erraseData(fileName);
        File file = new File(fileName);
        assertEquals(false, file.exists());
    }
    public void setGameConfig() {
        Session session = Session.getInstance();
        GameConfig gameConfig = session.getConfig();
        gameConfig.blueAgentsList.add(new Player("Lucas"));
        gameConfig.redAgentsList.add(new Player("Luca"));
        gameConfig.blueSpiesList.add(new Player("Nathan"));
        gameConfig.redSpiesList.add(new Player("Arthur"));
    }
}

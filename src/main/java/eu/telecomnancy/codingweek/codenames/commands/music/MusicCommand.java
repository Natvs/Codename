package eu.telecomnancy.codingweek.codenames.commands.music;

import eu.telecomnancy.codingweek.codenames.commands.Command;
import eu.telecomnancy.codingweek.codenames.music.Music;

public abstract class MusicCommand implements Command{
    protected final Music music;
    public MusicCommand(Music music){
        this.music = music;
    }
}

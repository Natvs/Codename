package eu.telecomnancy.codingweek.codenames.commands.music;

import eu.telecomnancy.codingweek.codenames.music.Music;

public class MuteCommand extends MusicCommand{
    public MuteCommand(Music music){
        super(music);
    }
    @Override
    public void execute(){
        music.mute();
    }
}
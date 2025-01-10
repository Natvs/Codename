package eu.telecomnancy.codingweek.codenames.commands.music;

import eu.telecomnancy.codingweek.codenames.music.Music;

public class NextMusicCommand extends MusicCommand{
    private String nextPath;
    public NextMusicCommand(Music music,String nextPath){
        super(music);
        this.nextPath = nextPath;
    }
    @Override
    public void execute(){
        music.NewMusic(nextPath);
    }
}

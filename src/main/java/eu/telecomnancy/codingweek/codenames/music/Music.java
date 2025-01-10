package eu.telecomnancy.codingweek.codenames.music;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

public class Music {
    private String path;
    private MediaPlayer mediaPlayer;
    private Thread musicThread;
    public Music(String path){
        this.path=path;
    }

    public void startMusic(){
        String musicFile = getClass().getResource(path).toString();
        try{
                Media media = new Media(musicFile);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setOnReady(() -> mediaPlayer.play());
                mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.stop());

            } catch (NullPointerException e) {
                // Handle invalid file path or unsupported format
                System.out.println("Error: The media file could not be loaded. Check the file path or format.");
                e.printStackTrace();
    
            } catch (MediaException e) {
                // Handle other general exceptions (e.g., issues during playback)
                System.out.println("An error occurred while trying to play the media.");
                e.printStackTrace();
            }
            musicThread = new Thread(() -> {
                try {
                    // Load and play the audio clip in the background
                    playAudio();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            musicThread.setDaemon(true);  // Make it a daemon thread so it won't block program termination
            musicThread.start();
        }
        
        private void playAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
            mediaPlayer.play();
            
    }
}

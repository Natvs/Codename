package eu.telecomnancy.codingweek.codenames.music;


import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

public class Music {
    private String path;
    private MediaPlayer mediaPlayer;
        
    private void playAudio(){// throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String musicFile = getClass().getResource(path).toString();
        try{
            Media media = new Media(musicFile);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setOnReady(() -> mediaPlayer.play());
        } catch (NullPointerException e) {
            // Handle invalid file path or unsupported format
            System.out.println("Error: The media file could not be loaded. Check the file path or format.");
            e.printStackTrace();

        } catch (MediaException e) {
            // Handle other general exceptions (e.g., issues during playback)
            System.out.println("An error occurred while trying to play the media.");
            e.printStackTrace();
        }  
    }

    public void NewMusic(String path){
        this.path = path;
        if (mediaPlayer != null){
            mediaPlayer.stop();
        }
        playAudio();
    }

    public void mute(){
        if (mediaPlayer != null){
            mediaPlayer.setMute(true);
        }
    }
    public void unMute(){
        if (mediaPlayer != null){
            mediaPlayer.setMute(false);
        }
    }
}

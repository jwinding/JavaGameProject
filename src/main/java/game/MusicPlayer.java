package game;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;

public class MusicPlayer {


    private MediaPlayer mPlayer;
    private Media music;
    private String filename;

    public MusicPlayer(String filename){
        JFXPanel fxPanel = new JFXPanel();
        this.filename=filename;

        music = new Media(new File(filename).toURI().toString());
        mPlayer = new MediaPlayer(music);

        mPlayer.setVolume(0.2);
        mPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mPlayer.seek(Duration.ZERO);
            }
        });
    }
public void playFile (String filename){
    this.filename=filename;
    mPlayer.stop();
    music = new Media(new File(filename).toURI().toString());
    mPlayer = new MediaPlayer(music);

    mPlayer.setVolume(0.4);
    mPlayer.setOnEndOfMedia(new Runnable() {
        public void run() {
            mPlayer.seek(Duration.ZERO);
        }
    });

    mPlayer.play();

}

    public void playMusic(){
        mPlayer.play();
    }
}

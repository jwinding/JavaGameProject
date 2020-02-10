package scene;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoundEffectPlayer {


    private Map<String,MediaPlayer> mPlayers;


    public SoundEffectPlayer(){
        JFXPanel fxPanel = new JFXPanel();

        mPlayers = new HashMap<>();

    }

    public void addEffect(String name, String filename){

        Media effect = new Media(new File(filename).toURI().toString());
        MediaPlayer mPlayer = new MediaPlayer(effect);
        mPlayer.setVolume(0.2);
        mPlayers.put(name,mPlayer);
    }

    public void playEffect(String s ){
        if(mPlayers.containsKey(s)){
            mPlayers.get(s).play();
        }
    }
}

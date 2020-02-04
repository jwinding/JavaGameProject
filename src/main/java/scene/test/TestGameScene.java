package scene.test;

import EntitySystem.*;
import EntitySystem.Components.SimplePlayerComponent;
import EntitySystem.Components.SimpleWallComponent;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import scene.AbstractGameScene;
import scene.Key;
import scene.test.TestMenu;

import java.io.IOException;

public class TestGameScene extends AbstractGameScene {

    SimplePlayerComponent playerComponent;

    public TestGameScene(Screen screen) throws InterruptedException, IOException {
        super(screen);

        SimplePlayerComponent playerComponent = new SimplePlayerComponent('X', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK, state);
        Entity player = new Entity( playerComponent );
        state.addEntity(player);
        this.playerComponent = playerComponent;

        SimpleWallComponent wall = new SimpleWallComponent(new Position(10,20),5,5,TextColor.ANSI.RED);
        Entity wall1 = new Entity(wall );
        state.addEntity(wall1);
    }


    @Override
    public void handleInput() {
        super.handleInput();

        double constVel = 0.08;

        var keysPressed = getKeysPressed();
        if(keysPressed.getOrDefault(Key.W, Boolean.FALSE)){
            playerComponent.setVelocity(new Position(-constVel,0));
        } else if(keysPressed.getOrDefault(Key.S, Boolean.FALSE)){
            playerComponent.setVelocity(new Position(constVel,0));
        } else if(keysPressed.getOrDefault(Key.D, Boolean.FALSE)){
            playerComponent.setVelocity(new Position(0,constVel));
        } else if(keysPressed.getOrDefault(Key.A, Boolean.FALSE)){
            playerComponent.setVelocity(new Position(0,-constVel));
        }
        else if(keysPressed.getOrDefault(Key.ESC, Boolean.FALSE)){
            try {
                setNextScene(new TestMenu(getScreen()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            playerComponent.setVelocity(new Position(0,0));
        }

    }
}

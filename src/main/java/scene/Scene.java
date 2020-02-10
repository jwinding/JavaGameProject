package scene;

import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public abstract class Scene {

    private Screen screen;
    private Scene nextScene;

    private String name;

    private Map<Key, Boolean> keysPressed = new EnumMap<>(Key.class);


    public Map<Key,Boolean> getKeysPressed(){
        synchronized (Scene.class){
            return keysPressed;
        }
    }

    public Scene(Screen screen){
        this(screen,"Scene");
    }

    public Scene(Screen screen,String name){
        this.screen=screen;
        this.name=name;
        nextScene = this;



        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (Scene.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            for(Key k: Key.values()){
                                if(ke.getKeyCode() == k.getCode())
                                    keysPressed.put(k, true);
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            for(Key k: Key.values()){
                                if(ke.getKeyCode() == k.getCode())
                                    keysPressed.put(k, false);
                            }
                            break;
                    }
                    return false;
                }
            }
        });


    }


    public Scene getNextScene(){
        return nextScene;
    }

    public void setNextScene(Scene next){
        this.nextScene = next;
    }


    public abstract void render();

    public abstract void update(double time);

    public abstract void checkTriggers();

    public abstract void handleInput();

    public Screen getScreen() {
        return screen;
    }

    public String getName(){ return name; }

    @Override
    public String toString(){
        return "Scene: "+name;
    }
}


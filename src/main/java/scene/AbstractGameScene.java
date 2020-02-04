package scene;

import EntitySystem.Drawable;
import EntitySystem.Updateable;
import com.googlecode.lanterna.screen.Screen;


import java.io.IOException;

public abstract class AbstractGameScene extends Scene {



    protected GameState state;

    public AbstractGameScene(Screen screen) throws InterruptedException, IOException {
        this(screen, "GameScene");
    }

    public AbstractGameScene(Screen screen, String name) throws InterruptedException, IOException {
        super(screen, name);
        state = new GameState();
    }

    @Override
    public void render() {
        getScreen().clear();
        for(Drawable d: state.getDrawables()){
            d.render(getScreen());
        }
        try {
            getScreen().refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(double time) {
        for(Updateable u: state.getUpdateables()){
            u.update(time);
        }

    }

    @Override
    public void handleInput() {

    }

    @Override
    public String toString(){
        return "GameScene "+ getName();
    }


}

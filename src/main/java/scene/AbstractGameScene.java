package scene;

import EntitySystem.Components.DiscoveredDrawer;
import EntitySystem.Components.LineOfSightBlock;
import EntitySystem.Components.Player;
import EntitySystem.Interfaces.Drawable;
import EntitySystem.Entity;
import EntitySystem.Interfaces.Triggerable;
import EntitySystem.Interfaces.Updateable;
import EntitySystem.Position;
import com.googlecode.lanterna.screen.Screen;


import java.io.IOException;

public abstract class AbstractGameScene extends Scene {



    protected Position startingPosition;
    protected GameState state;

    protected Player player;

    private LineOfSightBlock lineOfSightBlock;
    private DiscoveredDrawer discoveredDrawer;

    private int width = 80;
    private int height = 30;

    public AbstractGameScene(Screen screen) throws InterruptedException, IOException {
        this(screen, "GameScene");
    }

    public AbstractGameScene(Screen screen, String name) throws InterruptedException, IOException {
        super(screen, name);
        state = new GameState();

        lineOfSightBlock = new LineOfSightBlock(state);
        Entity los = new Entity(lineOfSightBlock);
        state.addEntity(los);

        discoveredDrawer = new DiscoveredDrawer(width,height,state);
        Entity dd = new Entity(discoveredDrawer);
        state.addEntity(dd);

    }

    public void removeDiscoveredTerrain(){
        state.removeFromEverywhere(discoveredDrawer);
    }

    public void setPlayer(Player p){

        if(player != null){
            state.removeFromEverywhere(player);
        }

        player = p;
        Entity player1 = new Entity( player );
        state.addEntity(player1);
        state.getSidePanel().setPlayer(player);
        state.getSidePanel().updateInventory(player.getInventory());
    }

    public Player getPlayer(){
        return player;
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
        state.removeThings();

        for(Updateable u: state.getUpdateables()){
            u.update(time);
        }

    }

    @Override
    public void checkTriggers(){
        for(Triggerable t: state.getTriggers()){
            if(t.checkSteppedOn(player.getPosition())){
                t.trigger(this);
            }
        }
    }


    @Override
    public void handleInput() {

    }

    @Override
    public String toString(){
        return "GameScene "+ getName();
    }


    public GameState getState(){
        return state;
    }

    public void setStartingPosition(Position p){
        startingPosition=p;
    }

    public Position getStartingPosition(){
        return startingPosition;
    }
}

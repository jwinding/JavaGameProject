package scene.concreteScenes;

import EntitySystem.*;
import EntitySystem.Components.*;
import EntitySystem.Components.GUI.EventLog;
import EntitySystem.Components.GUI.SidePanel;
import EntitySystem.Components.Items.Armor;
import EntitySystem.Components.Items.Hammer;
import EntitySystem.Components.Items.Shield;
import EntitySystem.Components.Items.Sword;
import EntitySystem.Components.Monsters.*;
import EntitySystem.Interfaces.MapLvl;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import scene.AbstractGameScene;
import scene.Key;

import java.io.IOException;
import java.util.Random;

public class Level1 extends AbstractGameScene {

    public Level1(Screen screen) throws InterruptedException, IOException {
        super(screen);
    }

    public void setupLevel(){
        loadMapFromBMP("Images/level1.bmp");
        setupPlayer( new Position(5,5));
        addMonsters();
        addItems();

        addEventLog();
        addSidePanel();

        addPortal();
    }

    private void addPortal(){
        try{
            Position pos = getRandomMapPosition();
//            Position pos = new Position(7,7);
            var nextScene = new Level2(getScreen());

            Portal p = new Portal(pos, nextScene);
            Entity portal = new Entity(p);
            state.addEntity(portal);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void loadMapFromBMP(String filename){
        Map2 m = new Map2(80,30);
        try {
            m.readFromBitmap(filename);
        } catch (IOException e) {
            System.out.println("Can't load map file! Make sure it's in the right place and is a bmp-file. ");
            e.printStackTrace();
        }
        Entity map = new Entity(m);
        state.addEntity(map);
    }

    private void addItems(){
        Random r = new Random();

        Position p1 = getRandomMapPosition();
        Hammer hammer = new Hammer(p1, TextColor.ANSI.WHITE, TextColor.ANSI.YELLOW);
        Entity hammer1 = new Entity(hammer);
        state.addEntity(hammer1);

        Position p3 = getRandomMapPosition();
        Shield shield = new Shield(p3, TextColor.ANSI.WHITE, TextColor.ANSI.YELLOW);
        Entity shield1 = new Entity(shield);
        state.addEntity(shield1);
    }

    Position getRandomMapPosition(){
        Random r = new Random();
        Position p = new Position(r.nextInt(80), r.nextInt(30));
        for(MapLvl mapComp: state.getMapComponents()){
            if(mapComp.checkIfIn(p)){
                return p;
            }
        }
        return getRandomMapPosition();
    }

    public void addMonsters(){




        Position p5 = getRandomMapPosition();
        Rat rat = new Rat(p5, state);
        Entity rat1 = new Entity( rat );
        state.addEntity(rat1);

        Position p6 = getRandomMapPosition();
        Orc orc = new Orc(p6 ,state);
        Entity orc1 = new Entity( orc );
        state.addEntity(orc1);

        Position p7 = getRandomMapPosition();
        Spider spider = new Spider(p7, state);
        Entity spider1 = new Entity(spider);
        state.addEntity(spider1);

        Position p8 = getRandomMapPosition();
        Toad toad = new Toad(p8, state);
        Entity toad1 = new Entity(toad);
        state.addEntity(toad1);
    }

    protected void setupPlayer(Position p){

        setStartingPosition(p);
        Player player = new Player(p, state);

        Entity player1 = new Entity( player );
        state.addEntity(player1);
        this.player = player;

    }

    public void addSidePanel() {
        try {
            SidePanel sidepanel = new SidePanel(state, player);
            Entity sp = new Entity(sidepanel);
            state.addEntity(sp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    EventLog eventLog;
    public void addEventLog () {
        eventLog = new EventLog(new Position(4, 32), 78, 7,TextColor.ANSI.WHITE, state) ;
        Entity eventlog1 = new Entity(eventLog);
        state.addEntity(eventlog1);
    }

    @Override
    public void handleInput() {
        super.handleInput();

        double constVel = 0.2;

        var keysPressed = getKeysPressed();
        if(keysPressed.getOrDefault(Key.W, Boolean.FALSE)){
            player.setVelocity(new Position(0,-constVel));
        } else if(keysPressed.getOrDefault(Key.S, Boolean.FALSE)){
            player.setVelocity(new Position(0,constVel));
        } else if(keysPressed.getOrDefault(Key.D, Boolean.FALSE)){
            player.setVelocity(new Position(constVel,0));
        } else if(keysPressed.getOrDefault(Key.A, Boolean.FALSE)){
            player.setVelocity(new Position(-constVel,0));
        }
        else if(keysPressed.getOrDefault(Key.ESC, Boolean.FALSE)){
            try {

                InGameMenu menu = new InGameMenu(getScreen(), this);
                setNextScene(menu);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            player.setVelocity(new Position(0,0));
        }

    }

}

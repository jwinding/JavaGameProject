package scene.concreteScenes;

import EntitySystem.Components.Items.Armor;
import EntitySystem.Components.Items.Sword;
import EntitySystem.Components.Monsters.*;
import EntitySystem.Components.Player;
import EntitySystem.Entity;
import EntitySystem.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class LabyrintLevel extends Level1 {

    Player playerComponent;

    public LabyrintLevel(Screen screen) throws InterruptedException, IOException {
        super(screen);

    }

    public void setupLevel(){
        loadMapFromBMP("Images/labyrint.bmp");
        setupPlayer(new Position(2, 2));
        addMonsters();

        addEventLog();
        addSidePanel();

        addItems();
        addPortal();
        removeDiscoveredTerrain();

    }

    public void addPortal(){

    }


    public void addItems(){

    }


    @Override
    public void addMonsters(){


        int numberOfMonster = 15;

        for(int i=0; i<numberOfMonster; i++){
            Position p = getRandomMapPosition();
            Ghoul ghoul = new Ghoul(p ,state);
            Entity ghoul1 = new Entity( ghoul );
            state.addEntity(ghoul1);
        }

        Position p = new Position(39,13);
        Boss boss = new Boss(p, state,2,2);
        Entity bb = new Entity(boss);
        state.addEntity(bb);


    }

}

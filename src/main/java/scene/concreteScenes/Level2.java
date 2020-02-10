package scene.concreteScenes;

import EntitySystem.Components.Items.Armor;
import EntitySystem.Components.Items.Sword;
import EntitySystem.Components.Monsters.*;
import EntitySystem.Components.Player;
import EntitySystem.Components.Portal;
import EntitySystem.Entity;
import EntitySystem.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Level2 extends Level1 {

    Player playerComponent;

    public Level2(Screen screen) throws InterruptedException, IOException {
        super(screen);

    }

    public void setupLevel(){
        loadMapFromBMP("Images/level2.bmp");
        setupPlayer(new Position(68, 20));
        addMonsters();

        addEventLog();
        addSidePanel();

        addItems();
        addPortal();


    }

    public void addPortal(){

        try{
            Position pos = new Position(5,5) ;
//            Position pos = new Position(67,20); //For testing purposes, close to player spawn.
//            var nextScene = new Level1(getScreen());
            var nextScene = new LabyrintLevel(getScreen());

            Portal p = new Portal(pos, nextScene);
            Entity portal = new Entity(p);
            state.addEntity(portal);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addItems(){


        Position p5 = getRandomMapPosition();
        Sword sword = new Sword(p5, TextColor.ANSI.WHITE, TextColor.ANSI.YELLOW);
        Entity sword1 = new Entity(sword);
        state.addEntity(sword1);

        Position p6 = getRandomMapPosition();
        Armor armor = new Armor(p6, TextColor.ANSI.WHITE, TextColor.ANSI.YELLOW);
        Entity armor1 = new Entity(armor);
        state.addEntity(armor1);
    }

    @Override
    public void addMonsters(){

        Rat rat = new Rat(new Position(8,8),state);
        Entity rat1 = new Entity( rat );
        state.addEntity(rat1);

        Ghoul ghoul = new Ghoul(getRandomMapPosition(), state);
        Entity ghoul1 = new Entity( ghoul );
        state.addEntity(ghoul1);

        Position p5 = getRandomMapPosition();
        Rat rat2 = new Rat(p5, state);
        Entity rat3 = new Entity( rat2 );
        state.addEntity(rat3);

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

}

package EntitySystem.Components.GUI;

import EntitySystem.Components.Items.Item;
import EntitySystem.Components.Player;
import EntitySystem.Entity;
import EntitySystem.Position;
import EntitySystem.Interfaces.Updateable;
import com.googlecode.lanterna.TextColor;
import scene.GameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SidePanel implements Updateable {
    private GameState state;

    private PrintText hp;
    private PrintText xp;
    private PrintText level;
    private PrintText damage;
    private PrintText armor;

    private Player player;
    List<PrintText> inventoryLines;

    private Position upperLeftCorner;

    public SidePanel(GameState state, Player player) throws IOException {
        this.player = player;
        this.state= state;
        upperLeftCorner = new Position(80, 5);

        addWindows();
        addTexts();
        addInventoryLines();


    }




    public void addWindows() throws IOException {
        PlayerWindow background = new PlayerWindow(upperLeftCorner, 15,18, TextColor.ANSI.WHITE,100 );
        Entity window1 = new Entity(background);
        state.addEntity(window1);
        PlayerWindow window2 = new PlayerWindow(upperLeftCorner.add(new Position(1,1)), 13, 1, TextColor.ANSI.YELLOW,90);
        Entity window3 = new Entity(window2);
        state.addEntity(window3);
        PlayerWindow window4 = new PlayerWindow(upperLeftCorner.add(new Position(1,3)), 13, 1, TextColor.ANSI.RED,90);
        Entity window5 = new Entity(window4);
        state.addEntity(window5);
        PlayerWindow window6 = new PlayerWindow(upperLeftCorner.add(new Position(1,5)), 13, 1, TextColor.ANSI.RED,90);
        Entity window7 = new Entity(window6);
        state.addEntity(window7);

        PlayerWindow extraInfo = new PlayerWindow(upperLeftCorner.add(new Position(1,7)), 13, 2, TextColor.ANSI.RED,90);
        Entity info = new Entity(extraInfo);
        state.addEntity(info);

        PlayerWindow inventory = new PlayerWindow(upperLeftCorner.add(new Position(1,10)), 13, 7, TextColor.ANSI.RED,90);
        Entity window9 = new Entity(inventory);
        state.addEntity(window9);


    }
    public void addTexts() {

        level = new PrintText(new Position(83,6),"Level "+player.getLevel(), TextColor.ANSI.BLACK,TextColor.ANSI.YELLOW);
        Entity printText3 = new Entity(level);
        state.addEntity(printText3);
        hp = new PrintText(new Position(83,8),"HP "+ player.getHP() +"/" + player.getMaxLife(), TextColor.ANSI.BLACK,TextColor.ANSI.RED);
        Entity printText5 = new Entity(hp);
        state.addEntity(printText5);
        xp = new PrintText(new Position(83,10),"XP 0/10", TextColor.ANSI.BLACK,TextColor.ANSI.RED);
        Entity printText7 = new Entity(xp);
        state.addEntity(printText7);


        armor = new PrintText(new Position(83,12),"AC "+ player.getAC() , TextColor.ANSI.BLACK,TextColor.ANSI.RED);
        Entity arm = new Entity(armor);
        state.addEntity(arm);
        damage = new PrintText(new Position(83,13),"Dmg  1d"+player.getDamageDie() + " +"+player.getDamageBonus(), TextColor.ANSI.BLACK,TextColor.ANSI.RED);
        Entity dmg = new Entity(damage);
        state.addEntity(dmg);


        Position inv = upperLeftCorner.add(new Position(1,10));
        PrintText printText8 = new PrintText(inv,"INVENTORY", TextColor.ANSI.BLACK,TextColor.ANSI.RED);
        Entity printText9 = new Entity(printText8);
        state.addEntity(printText9);



    }




    public void addInventoryLines() {
        Position start = upperLeftCorner.add(new Position(1,11));
        inventoryLines= new ArrayList<>();
        for (int i = 0; i <6; i++) {
            var line = new PrintText(start.add(new Position(0, i)), "", TextColor.ANSI.BLACK, TextColor.ANSI.RED);
            Entity printText1 = new Entity(line);
            state.addEntity(printText1);
            inventoryLines.add(line);

        }

    }

    public void updateInventory(List<Item> inventory) {
        for (int i = 0; i <6; i++) {
            if(i < inventory.size() ) {
                inventoryLines.get(i).setText("" + (i+1) + ". " + inventory.get(i).getName());
            } else {
                inventoryLines.get(i).setText("");
            }
            
        }
    }

    @Override
    public void update(double timeU) {
        level.setText("Level "+player.getLevel());
        xp.setText("XP "+ player.getXp() + "/10");
        hp.setText("HP "+ player.getHP() + "/" + player.getMaxLife());

        armor.setText("AC "+ player.getAC());
        damage.setText("Dmg 1d"+player.getDamageDie() + " +"+player.getDamageBonus());


    }

    public void setPlayer(Player player){
        this.player = player;
    }
}

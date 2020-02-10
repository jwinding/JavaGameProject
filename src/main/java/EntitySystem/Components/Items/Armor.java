package EntitySystem.Components.Items;

import EntitySystem.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Armor extends Item {


    public Armor(Position position, TextColor color, TextColor backgroundColor) {
        super("Armor +5", position, new TextCharacter('\u26FB', color, backgroundColor), color, backgroundColor);

        setExtraAC(5);
        setExtraHP(5);
        setHealthOnPickup(5);
    }


    @Override
    public boolean checkIfIn(Position p) {
        return false;
    }
}

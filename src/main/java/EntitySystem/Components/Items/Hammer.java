package EntitySystem.Components.Items;

import EntitySystem.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Hammer extends Weapon {


    public Hammer(Position position, TextColor color, TextColor backgroundColor) {
        super("Hammer +1", position, new TextCharacter('\u26CF',color,backgroundColor), color, backgroundColor);

        setAttackBonus(1);
        setDamageBonus(1);
        setDamageDie(8);

    }

    @Override
    public boolean checkIfIn(Position p) {
        return false;
    }
}

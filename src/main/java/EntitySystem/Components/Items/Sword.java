package EntitySystem.Components.Items;

import EntitySystem.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Sword extends Weapon {

    public Sword(Position position, TextColor color, TextColor backgroundColor) {
        super("Sword +2", position, new TextCharacter('\u2694',color,backgroundColor), color, backgroundColor);
        setAttackBonus(2);
        setDamageBonus(2);
        setDamageDie(10);
    }

    @Override
    public boolean checkIfIn(Position p) {
        return false;
    }
}

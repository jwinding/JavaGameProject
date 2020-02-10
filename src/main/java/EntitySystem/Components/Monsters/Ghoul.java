package EntitySystem.Components.Monsters;

import EntitySystem.Position;
import com.googlecode.lanterna.TextColor;
import scene.GameState;

public class Ghoul extends Monster {

    public Ghoul(Position pos, GameState state) {
        super("Ghoul", pos, 'G', TextColor.ANSI.BLACK, TextColor.ANSI.YELLOW, state);

        setHp(18);
        setArmorClass(15);
        setAttackBonus(6);
        setXpValue(5);
    }

    @Override
    public int getDamage() {
        return (int)(Math.random()*6)+3;
    }

}

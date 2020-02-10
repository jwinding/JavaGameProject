package EntitySystem.Components.Monsters;

import EntitySystem.Position;
import com.googlecode.lanterna.TextColor;
import scene.GameState;

public class Rat extends Monster {

    public Rat(Position pos, GameState state) {
        super("Rat", pos, 'R', TextColor.ANSI.BLACK, TextColor.ANSI.YELLOW, state);

        setHp(2);
        setArmorClass(10);
        setAttackBonus(0);
        setXpValue(3);
    }

    @Override
    public int getDamage() {
        return (int)(Math.random()*4);
    }

}

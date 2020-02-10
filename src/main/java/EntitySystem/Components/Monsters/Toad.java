package EntitySystem.Components.Monsters;

import EntitySystem.Position;
import com.googlecode.lanterna.TextColor;
import scene.GameState;

public class Toad extends Monster {

    public Toad (Position pos, GameState state) {
        super("Toad", pos, 'T', TextColor.ANSI.GREEN, TextColor.ANSI.YELLOW, state);

        setHp(8);
        setArmorClass(11);
        setAttackBonus(1);
        setXpValue(6);
    }
    @Override
    public int getDamage() {
        return (int)(Math.random()*4);

    }
}

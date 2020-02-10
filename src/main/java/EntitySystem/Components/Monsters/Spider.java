package EntitySystem.Components.Monsters;

import EntitySystem.Position;
import com.googlecode.lanterna.TextColor;
import scene.GameState;

public class Spider extends Monster{

    public Spider (Position pos, GameState state) {
        super("Spider", pos, 'S', TextColor.ANSI.BLACK, TextColor.ANSI.YELLOW, state);

        setHp(4);
        setArmorClass(11);
        setAttackBonus(1);
        setXpValue(4);
    }
    @Override
    public int getDamage() {
        return (int)(Math.random()*4);

    }
}


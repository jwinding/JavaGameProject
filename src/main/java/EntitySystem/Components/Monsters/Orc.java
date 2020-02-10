package EntitySystem.Components.Monsters;

import EntitySystem.Position;
import com.googlecode.lanterna.TextColor;
import scene.GameState;

public class Orc extends Monster {

    public Orc(Position pos, GameState state) {
        super("Orc", pos, 'O', TextColor.ANSI.BLACK, TextColor.ANSI.YELLOW, state);

        setHp(12);
        setArmorClass(12);
        setAttackBonus(2);
        setXpValue(10);
    }

    @Override
    public int getDamage() {
        return (int)(Math.random()*6)+3;
    }

}

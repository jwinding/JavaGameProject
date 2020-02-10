package EntitySystem.Components.Items;

import EntitySystem.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

abstract class Weapon extends Item {
    public Weapon(String name, Position position, TextCharacter itemCharacter, TextColor color, TextColor backgroundColor) {
        super(name,position, itemCharacter, color, backgroundColor);
    }
}

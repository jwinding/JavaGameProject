package EntitySystem.Components.Items;
import EntitySystem.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Shield extends Item {


    public Shield(Position position, TextColor color, TextColor backgroundColor) {
        super("Shield +2", position, new TextCharacter('\u2B59',color,backgroundColor), color, backgroundColor);

        setExtraAC(2);

    }

    @Override
    public boolean checkIfIn(Position p) {
        return false;
    }


}

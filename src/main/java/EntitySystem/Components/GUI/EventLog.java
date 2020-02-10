package EntitySystem.Components.GUI;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Entity;
import EntitySystem.Position;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import scene.GameState;

import java.util.ArrayList;
import java.util.List;

public class EventLog implements Drawable {

    private Position posUpperLeft;
    private int width, height;
    private Position posLowerRight;
    private GameState state;
    private PrintText line, line1, line2, line3;


    private char fillChar = '\u2588';
    private TextColor color;
    private List<String> stringList;
    private TextGraphics textGraphics = null;

    private int z = 90;

    public EventLog(Position upperLeftCorner, int width, int height, TextColor color, GameState state) {
        this.color = color;
        this.state = state;
        this.posUpperLeft = upperLeftCorner;
        this.width = width;
        this.height = height;
        this.stringList = new ArrayList<>();
        stringList.add("");
        stringList.add("");
        stringList.add("");
        addPrintText();
        posLowerRight = upperLeftCorner.add(new Position(width, height));

    }

    public void addLine(String s) {
        stringList.add(s);

        line.setText(stringList.get(stringList.size() - 4));
        line1.setText(stringList.get(stringList.size() - 3));
        line2.setText(stringList.get(stringList.size() - 2));
        line3.setText(stringList.get(stringList.size() - 1));
    }



    public void addPrintText() {

        line = new PrintText(new Position(5, 33), "Event Log", TextColor.ANSI.BLACK, TextColor.ANSI.WHITE);
        Entity printText1 = new Entity(line);
        state.addEntity(printText1);
        line1 = new PrintText(new Position(5, 34), "", TextColor.ANSI.BLACK, TextColor.ANSI.WHITE);
        Entity printText2 = new Entity(line1);
        state.addEntity(printText2);
        line2 = new PrintText(new Position(5, 35), "", TextColor.ANSI.BLACK, TextColor.ANSI.WHITE);
        Entity printText3 = new Entity(line2);
        state.addEntity(printText3);
        line3 = new PrintText(new Position(5, 36), "", TextColor.ANSI.BLACK, TextColor.ANSI.WHITE);
        Entity printText4 = new Entity(line3);
        state.addEntity(printText4);
    }


    @Override
    public void render(Screen screen) {
        if (textGraphics == null) {
            textGraphics = screen.newTextGraphics();
        }
        textGraphics.setForegroundColor(color);

        textGraphics.fillRectangle(
                posUpperLeft.toTerminalPosition(), new TerminalSize(width, height), '\u2588');

    }

    @Override
    public int getZ() {
        return z;
    }
}
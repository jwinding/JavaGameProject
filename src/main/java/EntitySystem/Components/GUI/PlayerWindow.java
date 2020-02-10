package EntitySystem.Components.GUI;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Position;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class PlayerWindow implements Drawable{

    private Position posUpperLeft;
    private int width, height;
    private Position posLowerRight;

    private char fillChar = '\u2588';
    private TextColor color;

    private TextGraphics textGraphics = null;

    private int z = 90;

    public PlayerWindow(Position upperLeftCorner, int width, int height, TextColor color, int z) {
        this.color = color;
        this.z = z;
        this.posUpperLeft = upperLeftCorner;
        this.width = width;
        this.height = height;

        posLowerRight = upperLeftCorner.add(new Position(width, height));
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
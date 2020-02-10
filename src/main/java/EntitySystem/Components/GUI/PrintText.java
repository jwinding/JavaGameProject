package EntitySystem.Components.GUI;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Position;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class PrintText implements Drawable{

    private Position posUpperLeft;
    private int width, height;
    private Position posLowerRight;
    private String s;


    private char fillChar = '\u2588';
    private TextColor color;
    private TextColor backgroundcolor;

    private TextGraphics textGraphics = null;

    private int z = 50;

    public PrintText(Position upperLeftCorner, String s, TextColor color, TextColor backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
        this.color = color;
        this.posUpperLeft = upperLeftCorner;
        this.s = s;
        posLowerRight = upperLeftCorner.add(new Position(width, height));
    }

    public void setText(String s){
        this.s = s;

    }

    @Override
    public void render(Screen screen) {
        if (textGraphics == null) {
            textGraphics = screen.newTextGraphics();
        }
        textGraphics.setForegroundColor(color);
        textGraphics.setBackgroundColor(backgroundcolor);

        textGraphics.putString(posUpperLeft.getXAsInt(),posUpperLeft.getYAsInt(), s);


    }

    @Override
    public int getZ() {
        return z;
    }
}


package EntitySystem.Components;

import EntitySystem.Drawable;
import EntitySystem.Physical;
import EntitySystem.Position;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class SimpleWallComponent implements Drawable, Physical {

    private Position posUpperLeft;
    private int width, height;
    private Position posLowerRight;

    private char fillChar = '\u2588';
    private TextColor color;

    private TextGraphics textGraphics=null;

    public SimpleWallComponent(Position upperLeftCorner, int width, int height, TextColor color){
        this.color = color;

        this.posUpperLeft = upperLeftCorner;
        this.width=width;
        this.height=height;

        posLowerRight = upperLeftCorner.add(new Position(width,height));
    }

    @Override
    public void render(Screen screen) {
        if(textGraphics==null){
            textGraphics = screen.newTextGraphics();
        }
        textGraphics.setForegroundColor(color);

        textGraphics.fillRectangle(
                posUpperLeft.toTerminalPosition(), new TerminalSize(width,height), '\u2588');

    }

    @Override
    public boolean checkCollision(Position pos) {
//        System.out.println( "X: " + pos.getX() + "  " + posUpperLeft.getX() + " " + posLowerRight.getX() );
//        System.out.println( "Y: " + pos.getY() + "  " + posUpperLeft.getY() + " " + posLowerRight.getY() );
        if( pos.getXAsInt() >= posUpperLeft.getXAsInt() && pos.getXAsInt() < posLowerRight.getXAsInt() ) {
            if( pos.getYAsInt()>= posUpperLeft.getYAsInt() && pos.getYAsInt() < posLowerRight.getYAsInt() ) {

                return true;
            }
        }
        return false;
    }
}

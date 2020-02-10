package EntitySystem.Components;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Interfaces.Updateable;
import EntitySystem.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import scene.GameState;

public class LineOfSightBlock implements Drawable {

    private int z = 5;
    private GameState state;
    private int radius = 3;

    private int width, heigth;

    private TextGraphics tg = null;

    private TextColor blockColor = new TextColor.RGB(41, 40, 40);

    public LineOfSightBlock(GameState state){
        this.state=state;
        this.width=80;
        this.heigth=30;

    }

    @Override
    public void render(Screen screen) {


        if (tg == null) {
            tg = screen.newTextGraphics();
            tg.setForegroundColor(blockColor);
        }

        for(int x=0; x<width; x++){
            for(int y=0; y<heigth; y++){
                Position p = state.getPlayer().getPosition();

                if( Math.abs(p.getXAsInt()-x) < radius && Math.abs(p.getYAsInt()-y) < radius ){
                    continue;
                }
                tg.setCharacter(new TerminalPosition(x,y), '\u2588');

            }
        }


    }

    @Override
    public int getZ() {
        return 0;
    }


}

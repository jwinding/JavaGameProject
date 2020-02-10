package EntitySystem.Components;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Interfaces.MapLvl;
import EntitySystem.Interfaces.Updateable;
import EntitySystem.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import javafx.geometry.Pos;
import scene.GameState;

public class DiscoveredDrawer implements Drawable, Updateable {

    private int z = -1;

    private int width, height;

    private TextColor[][] discoveredMap;
    private TextGraphics tg = null;
    private GameState state;
    private int radius = 3;

    private TextColor discoveredRoomColor = new TextColor.RGB(94, 55, 0);
    private TextColor discoveredWallColor = TextColor.ANSI.BLACK;

    public DiscoveredDrawer(int width, int height, GameState state){
        this.state=state;
        this.width=width;
        this.height = height;

        discoveredMap = new TextColor[width][height];
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                discoveredMap[x][y] = null;
            }
        }
    }


    @Override
    public void render(Screen screen) {
        if (tg == null) {
            tg = screen.newTextGraphics();
        }

        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                Position p = state.getPlayer().getPosition();

                if( Math.abs(p.getXAsInt()-x) < radius && Math.abs(p.getYAsInt()-y) < radius ){
                    continue;
                }
                if(discoveredMap[x][y] != null){
                    tg.setForegroundColor(discoveredMap[x][y]);
                    tg.setCharacter(new TerminalPosition(x,y), '\u2588');
                }
            }
        }
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public void update(double timeU) {
        Position p = state.getPlayer().getPosition();
        int x = p.getXAsInt();
        int y = p.getYAsInt();
        for(int dx = -radius+1; dx< radius; dx++){
            for(int dy = -radius+1; dy< radius; dy++) {

                if(x +dx < 0 || x+dx >= width || y+dy < 0 || y+dy>=height ){
                    continue;
                }

                if(discoveredMap[x+dx][y+dy] == null){
                    for(MapLvl map: state.getMapComponents()){
                        if(map.checkIfIn(new Position(x+dx,y+dy))){
                            discoveredMap[x+dx][y+dy] = discoveredRoomColor;
                        } else{
                            if(discoveredMap[x+dx][y+dy] != discoveredRoomColor )
                            discoveredMap[x+dx][y+dy] = discoveredWallColor;
                        }
                    }
                }
            }
        }
    }
}

package EntitySystem.Components;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Interfaces.MapLvl;
import EntitySystem.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map2 implements Drawable, MapLvl {


    private int width;
    private int height;

    private int z = 100;
    private int[][] bitmap;

    private BufferedImage mapImage;

    private TextGraphics tg = null;

    private TextColor mapBackgroundColor = TextColor.ANSI.BLACK;
    private TextColor roomColor = TextColor.ANSI.YELLOW;
    private TextColor doorColor = TextColor.ANSI.BLUE;

    public Map2(int width, int height){
        this.width=width;
        this.height=height;

        bitmap = new int[height][width];
    }



    public void readFromBitmap(String filename) throws IOException {
        mapImage = ImageIO.read(new File(filename));
    }

    public void printBitmap(){
        for(int x = 0; x<mapImage.getWidth(); x++){
            for(int y = 0; y<mapImage.getHeight(); y++){
                System.out.print( mapImage.getRGB(x,y) + " ");

            }
            System.out.println();
        }
    }

    @Override
    public void render(Screen screen) {

        if (tg == null) {
            tg = screen.newTextGraphics();
        }


        for(int x = 0; x<mapImage.getWidth(); x++){
            for(int y = 0; y<mapImage.getHeight(); y++){
                int rgb = mapImage.getRGB(x,y);

                if( rgb == -16777216 ){

                    tg.setForegroundColor(mapBackgroundColor);
                    tg.setCharacter(new TerminalPosition(x,y), '\u2588');

                } else if(rgb==-1){
                    tg.setForegroundColor(roomColor);
                    tg.setCharacter(new TerminalPosition(x,y), '\u2588');
                } else if(rgb == -12629812){
                    tg.setForegroundColor(doorColor);
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
    public boolean checkIfIn(Position p) {
        int rgb = mapImage.getRGB(p.getXAsInt(), p.getYAsInt());
        return rgb != -16777216 ;
    }
}

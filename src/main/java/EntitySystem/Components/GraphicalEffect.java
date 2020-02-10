package EntitySystem.Components;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphicalEffect implements Drawable {

    private int width;
    private int height;

    private int z = -100;

    private BufferedImage mapImage;

    private TextGraphics tg = null;

    private TextColor color1 = TextColor.ANSI.RED;
    private TextColor color2 = TextColor.ANSI.BLACK;

    private List<String> message;

    private Position textPosition;

    public GraphicalEffect(String filename,Position textPosition,  String... messages){

        this.textPosition = textPosition;
        message = new ArrayList<>();
        for(String s:messages){
            message.add(s);
        }

        try {
            readFromBitmap(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void readFromBitmap(String filename) throws IOException {
        mapImage = ImageIO.read(new File(filename));

        width = mapImage.getWidth();
        height = mapImage.getHeight();
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

                int red = (rgb>>16)&0x0ff;
                int green=(rgb>>8) &0x0ff;
                int blue= (rgb)    &0x0ff;

                if(red > 100 && blue > 120 && green>120){
                    continue;
                }

                tg.setForegroundColor(new TextColor.RGB(red,green,blue));
                tg.setCharacter(new TerminalPosition(x,y), '\u2588');
            }
        }

//        Position p = new Position(25,15);
        int rgb = mapImage.getRGB(textPosition.getXAsInt(),textPosition.getYAsInt());

        int red = (rgb>>16)&0x0ff;
        int green=(rgb>>8) &0x0ff;
        int blue= (rgb)    &0x0ff;
        tg.setForegroundColor(TextColor.ANSI.BLACK);
        tg.setBackgroundColor(new TextColor.RGB(red,green,blue));

        for(int i = 0; i< message.size(); i++){
            tg.putString( textPosition.add(new Position(0,i)).toTerminalPosition(), message.get(i));
        }

    }

    @Override
    public int getZ() {
        return z;
    }

}

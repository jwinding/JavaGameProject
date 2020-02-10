package scene.concreteScenes;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.TextGUIGraphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class MenuBackground extends EmptySpace {
    private final TerminalSize size;

    private TextColor color;
    private TextColor color2;

    private BufferedImage mapImage;
    private int width,height;

    private TextGraphics tg = null;
    private List<String> message;

    public MenuBackground(TerminalSize size, TextColor color, TextColor color2) {
        this.size = size;
        this.color = color;
        this.color2 = color2;
    }

    public MenuBackground(String filename, TerminalSize size){
        this.size = size;
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

    protected ComponentRenderer<EmptySpace> createDefaultRenderer() {
        return new ComponentRenderer<EmptySpace>() {
            public TerminalSize getPreferredSize(EmptySpace component) {
                return size;
            }

            public void drawComponent(TextGUIGraphics graphics, EmptySpace component) {
                graphics.applyThemeStyle(component.getThemeDefinition().getNormal());
                if (color != null) {
                    graphics.setBackgroundColor(color);

                }

                for(int x = 0; x<mapImage.getWidth(); x++){
                    for(int y = 0; y<mapImage.getHeight(); y++){
                        int rgb = mapImage.getRGB(x,y);
                        int red = (rgb>>16)&0x0ff;
                        int green=(rgb>>8) &0x0ff;
                        int blue= (rgb)    &0x0ff;
//                        if(red > 100 && blue > 120 && green>120){
//                            continue;
//                        }
                        graphics.setForegroundColor(new TextColor.RGB(red,green,blue));
                        graphics.setCharacter(x,y,'\u2588');
                        String gameName = "Dragons in dungeons";
                        graphics.putString(10,10,gameName);
                    }
                }

            }
        };
    }

}

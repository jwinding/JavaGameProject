package scene;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import scene.concreteScenes.MenuBackground;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenu extends Scene {

    private MultiWindowTextGUI gui;
    private Panel panel;

    public AbstractMenu(Screen screen) throws InterruptedException, IOException {
        this(screen, "Menu");
    }

    public AbstractMenu(Screen screen, String name) throws InterruptedException, IOException {
        super(screen, name);

        panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));
    }

    public void addButton(Button button){
        panel.addComponent(button);

    }

    public void createGUI(){
        this.createGUI(5);
    }

    public void createGUI(int theme){
        BasicWindow window = new BasicWindow();
        int y = getScreen().getTerminalSize().getColumns()/2;


        window.setPosition(new TerminalPosition(y,100));
        window.setComponent(panel);
        List<String> availableThemes = new ArrayList<>(LanternaThemes.getRegisteredThemes());
//        System.out.println(availableThemes.size());
        String themeName = availableThemes.get(theme);

        window.setTheme(LanternaThemes.getRegisteredTheme(themeName));

        List<Window.Hint> hints = new ArrayList<>();
        hints.add(Window.Hint.CENTERED);
        window.setHints(hints);

//        MenuBackgroundComponent background = new MenuBackgroundComponent(getScreen().getTerminalSize(), TextColor.ANSI.YELLOW,TextColor.ANSI.BLUE );
        MenuBackground background = new MenuBackground("Images/bg3_pic.bmp", getScreen().getTerminalSize() );

        gui = new MultiWindowTextGUI(getScreen(), new DefaultWindowManager(), background);
        gui.addWindowAndWait(window); //waits for input: changing scenes has to happen in the called functions.


    }

    public MultiWindowTextGUI getGUI(){
        return gui;
    }

    @Override
    public String toString(){
        return "MenuScene "+ getName();
    }

}

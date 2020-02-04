package scene.test;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import scene.AbstractMenu;

import java.io.IOException;

public class TestMenu extends AbstractMenu {

    public TestMenu(Screen screen) throws InterruptedException, IOException {
        super(screen);

        addButton(new Button("Play", new Runnable(){
            @Override
            public void run() {
                getGUI().getActiveWindow().close();
                System.out.println("Start game");
                try {
                    System.out.println("setting next scene");
                    setNextScene(new TestGameScene(getScreen()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
        addButton(new Button("Settings", new Runnable(){
            @Override
            public void run() {
                System.out.println("multiplayer");;
            }
        }));
        addButton(new Button("Exit", new Runnable(){
            @Override
            public void run() {
                getGUI().getActiveWindow().close();
                System.out.println("closed window");
                setNextScene(null);
            }
        }));

        createGUI();
    }

    @Override
    public void render() {

    }

    @Override
    public void update(double time) {

    }

    @Override
    public void handleInput() {

    }



}

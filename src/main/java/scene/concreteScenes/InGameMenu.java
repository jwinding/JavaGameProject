package scene.concreteScenes;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.screen.Screen;
import scene.AbstractGameScene;
import scene.AbstractMenu;

import java.io.IOException;

public class InGameMenu extends AbstractMenu {

    private AbstractGameScene currentGame;

    public InGameMenu(Screen screen, AbstractGameScene scene) throws InterruptedException, IOException {
        super(screen);
        currentGame = scene;

        addButton(new Button("Resume", new Runnable(){
            @Override
            public void run() {
                getGUI().getActiveWindow().close();
                System.out.println("Resumed game");
                setNextScene(currentGame);

            }
        }));
        addButton(new Button("Restart", new Runnable(){
            @Override
            public void run() {
                getGUI().getActiveWindow().close();
                System.out.println("Restarted game");
                try {
                    System.out.println("setting next scene");
                    var gameScene = new Level1(getScreen());
                    gameScene.setupLevel();
                    setNextScene(gameScene);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
    public void checkTriggers(){

    }

    @Override
    public void handleInput() {

    }



}

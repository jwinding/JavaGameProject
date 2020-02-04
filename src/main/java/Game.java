import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import scene.test.TestMenu;
import scene.Scene;

import java.io.IOException;

public class Game {

    private Terminal terminal;
    private Screen screen;

    private double FPS = 60, UPS=60;
    private boolean running = true;
    private boolean print_fps = false;

    int width=80, height=30;

    private Scene scene;

    private String title = "Game example";


    public Game() throws IOException, InterruptedException {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        TerminalSize size = new TerminalSize(width,height);
        terminalFactory.setInitialTerminalSize(size);
        terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        screen = new TerminalScreen(terminal);
        screen.startScreen();

        scene = new TestMenu(screen);

        runGame();

    }




    public void runGame() throws IOException {

        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while(running) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;


            if (deltaU >= 1) {
                handleInput();
                update(timeU);

                ticks++;
                deltaU--;
            }

            if(deltaF >= 1) {
                render();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                if(print_fps) {
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                }
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }


    private void render() throws IOException {
        scene.render();
    }

    private void update(double timeU){
        scene.update(timeU);

        if(scene.getNextScene() != scene) {
            scene = scene.getNextScene();

            if(scene == null){
                exit();
            }
        }
    }

    public void exit(){
        running=false;
        try {
            terminal.close();
            System.exit(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleInput(){
        scene.handleInput();
    }



}

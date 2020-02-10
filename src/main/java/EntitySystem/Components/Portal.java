package EntitySystem.Components;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Position;
import EntitySystem.Interfaces.Triggerable;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import scene.AbstractGameScene;
import scene.concreteScenes.Level1;

public class Portal implements Drawable, Triggerable {

    private Position position;

    private Level1 nextScene;

    private TextCharacter character;

    private int z=10;

    public Portal(Position p, Level1 nextScene){
        this.position = p;
        this.nextScene = nextScene;

        var Char='%';
        var backgroundColor = TextColor.ANSI.BLUE;
        var color = TextColor.ANSI.WHITE;

        character = new TextCharacter(Char, color, backgroundColor, SGR.BOLD );

    }

    @Override
    public void render(Screen screen) {
        screen.setCharacter(position.getXAsInt(),position.getYAsInt(), character);
    }

    @Override
    public int getZ() {
        return z;
    }



    @Override
    public boolean checkSteppedOn(Position position) {
        return position.equalsInt(this.position);
    }

    @Override
    public void trigger(AbstractGameScene currentScene) {
        System.out.println("Entering next level!");
        nextScene.setupLevel();
        currentScene.getPlayer().setState(nextScene.getState());
        currentScene.getPlayer().setPosition(nextScene.getStartingPosition());

        nextScene.setPlayer(currentScene.getPlayer());

        nextScene.getState().getEventLog().addLine("You climb down one level further!");
        currentScene.setNextScene(nextScene);

    }
}

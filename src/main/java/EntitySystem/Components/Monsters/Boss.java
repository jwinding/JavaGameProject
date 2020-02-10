package EntitySystem.Components.Monsters;

import EntitySystem.Components.GraphicalEffect;
import EntitySystem.Entity;
import EntitySystem.Interfaces.Drawable;
import EntitySystem.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import game.Game;
import scene.GameState;

public class Boss extends Monster implements Drawable {

    private Position posUpperLeft;
    private int width, height;
    private Position posLowerRight;
    private char fillChar = 'B';

    public Boss(Position upperLeftCorner, GameState state, int width, int height) {
        super("Big Baddie", upperLeftCorner, 'B', TextColor.ANSI.BLUE, TextColor.ANSI.WHITE, state);
        this.width = width;
        this.height = height;
        this.posUpperLeft = upperLeftCorner;
        posLowerRight = upperLeftCorner.add(new Position(width, height));
        setHp(60);
        setArmorClass(15);
        setAttackBonus(5);
        setXpValue(20);
    }

    @Override
    public int getDamage() {
        return (int) (Math.random() * 6) + 5;
    }

    Position p =getPosition();
    @Override
    public void render(Screen screen) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                screen.setCharacter(p.add(new Position(i,j)).toTerminalPosition(), monsterCharacter);
            }
        }
    }

    @Override
    public boolean checkCollision(Position pos) {
        return ((pos.getXAsInt() == p.getXAsInt() || pos.getXAsInt() == p.getXAsInt()+1) &&
                (pos.getYAsInt()==p.getYAsInt() || pos.getYAsInt()==p.getYAsInt()+1));
    }

    void die(){
        super.die();
        String message = String.format("You slayed the Big Baddie Boss!!!");
        GraphicalEffect winnerWinnerChickenDinner = new GraphicalEffect("Images/win.bmp",new Position(35,18),
                "You WON!",
                "You reached level " + getState().getPlayer().getLevel() + "!" );
        Entity winGraphics = new Entity(winnerWinnerChickenDinner);
        getState().addEntity(winGraphics);

        getState().getEventLog().addLine("You have defeated the final boss!");
        Game.getMusicPlayer().playFile("music/win fanfar.mp3");
    }
}


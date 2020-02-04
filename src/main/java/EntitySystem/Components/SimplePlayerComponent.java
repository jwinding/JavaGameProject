package EntitySystem.Components;

import EntitySystem.Drawable;
import EntitySystem.Physical;
import EntitySystem.Position;
import EntitySystem.Updateable;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import scene.GameState;

import java.util.List;

public class SimplePlayerComponent implements Drawable, Updateable {

    private Position position;
    private Position velocity;


    private char playerChar;
    private TextColor color;
    private TextColor backgroundColor;

    private TextCharacter playerCharacter;

    private TextCharacter scoreCharacter;

    private TextGraphics textGraphics=null;

    private GameState state;

    public SimplePlayerComponent(char playerChar, TextColor color, TextColor backgroundColor,GameState state ){
        this.playerChar = playerChar;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.state = state;

        this.position = new Position(10,10);
        this.velocity = new Position(0,0);
        playerCharacter = new TextCharacter(playerChar, color, backgroundColor,SGR.BOLD );


    }

    @Override
    public void render(Screen screen) {

        screen.setCharacter(position.getYAsInt(),position.getXAsInt(), playerCharacter);

    }

    @Override
    public void update(double timeU) {
        var newPos = position.add( velocity);
        for(Physical u: state.getPhysicals()){
            if( u.checkCollision(newPos) )
                return;
        }
        position = newPos;

    }

    public void setVelocity(Position newVelocity){
        velocity = newVelocity;
    }

    public void addVelocity(Position dV){
        velocity = velocity.add(dV);
    }

    public Position getVelocity(){ return velocity; }
    public Position getPosition(){ return position; }
}

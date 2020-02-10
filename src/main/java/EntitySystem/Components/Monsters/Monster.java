package EntitySystem.Components.Monsters;

import EntitySystem.*;
import EntitySystem.Interfaces.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import scene.GameState;

public abstract class Monster implements Drawable, Physical, Updateable, Fightable {

    private String name;

    private int hp;
    private int z = 10;

    private int xpValue;

    private Position position;
    private Position velocity;

    private char monsterChar;
    private TextColor color;
    private TextColor backgroundColor;

    private GameState state;
    protected TextCharacter monsterCharacter;

    private int armorClass;
    private int attackBonus;


    public Monster( String name,  Position pos, char monsterChar, TextColor color, TextColor backgroundColor, GameState state ){
        this.name = name;
        this.monsterChar = monsterChar;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.state = state;

        this.position = pos;
        this.velocity = new Position(0,0);
        monsterCharacter = new TextCharacter(monsterChar, color, backgroundColor, SGR.FRAKTUR );


    }

    @Override
    public void render(Screen screen) {
        screen.setCharacter(position.toTerminalPosition(),monsterCharacter);
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public void update(double timeU) {
        changeVelocity();
        if(checkIfDead()){
            die();
            return;
        }

        var newPos = position.add( velocity);

        for(Physical u: state.getPhysicals()){
            if( u.checkCollision(newPos) )
                return;
        }

        for(MapLvl mapComp: state.getMapComponents()){
            if(mapComp.checkIfIn(newPos)){
                position=newPos;
                return;
            }
        }

    }
    private int moveCount=0;
    public void changeVelocity() {
        moveCount++;
        double angle = 2*Math.PI*Math.random();
        if (moveCount > 60) {
            double dx = (Math.cos(angle)) * 0.7;
            double dy = (Math.sin(angle)) * 0.7;
            velocity = new Position(dx, dy);
            moveCount = 0;
        }
    }

    @Override
    public boolean checkCollision(Position pos) {
        return (pos.getXAsInt() == position.getXAsInt() && pos.getYAsInt()==position.getYAsInt());
    }

    public void setVelocity(Position newVelocity){
        velocity = newVelocity;
    }

    public void addVelocity(Position dV){
        velocity = velocity.add(dV);
    }

    public Position getVelocity(){ return velocity; }
    public Position getPosition(){ return position; }

    public int makeAttack(){
        return (int)(1+Math.random() * 20) + attackBonus;
    }

    public abstract int getDamage();

    public int getHp(){
        return hp;
    }

    public int getArmorClass(){
        return armorClass;
    }

    public void setHp(int hp){
        this.hp=hp;
    }
    public void setArmorClass(int ac){
        this.armorClass=ac;
    }
    public void setXpValue(int xp){
        this.xpValue= xp;
    }


    public void setAttackBonus(int attackBonus){
        this.attackBonus=attackBonus;
    }

    public int getXpValue(){
        return xpValue;
    }

    public boolean checkIfDead(){
        return hp<= 0;
    }

    public void takeDamage(int damage){
        hp -= damage;
    }

    public String getName(){
        return name;
    }

    void die(){
        state.addToRemoveQueue(this);
    }

    public GameState getState(){
        return state;
    }


}

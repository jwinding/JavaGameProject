package EntitySystem.Components;

import EntitySystem.*;
import EntitySystem.Components.Items.Item;
import EntitySystem.Interfaces.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import scene.GameState;
import scene.SoundEffectPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Drawable, Updateable {


    private int level = 1;
    private int xp = 0;

    private int maxLife = 10;
    private int currentLife = 10;
    private boolean dead = false;
    private int shield = 0;
    private int armorClass = 10;


    private int attackBonus = 3;

    private int damageDie = 4;
    private int damageBonus = 1;

    private List<Item> inventory;

    private char playerChar;
    private TextColor color;
    private TextColor backgroundColor;
    private GameState state;

    private TextCharacter playerCharacter;

    private Position position;
    private Position velocity;

    private Random randomGen;

    private int z = 1;
    private int fightCounter = 0;


    private SoundEffectPlayer soundEffects;

    public Player(Position pos, GameState state ){

        playerChar='☻';
        backgroundColor = TextColor.ANSI.YELLOW;
        color = TextColor.ANSI.WHITE;
        position = pos;
        this.state = state;

        soundEffects = new SoundEffectPlayer();
        soundEffects.addEffect("onHit", "oof.mp3");
        soundEffects.addEffect("die", "music/lose.mp3");

        this.velocity = new Position(0,0);
        playerCharacter = new TextCharacter(playerChar, color, backgroundColor, SGR.BOLD );
        randomGen = new Random();

        inventory = new ArrayList<>();



    }

    @Override
    public void render(Screen screen) {
        screen.setCharacter(position.getXAsInt(),position.getYAsInt(), playerCharacter);
    }

    @Override
    public int getZ() {
        return z;
    }



    @Override
    public void update(double timeU) {
        lvlUp();
        if(dead){ return;}

        pickUpItem();
        var newPos = position.add( velocity);

        fightCounter++; 
        if(fightCounter > 20){

            for(Fightable monster: state.getMonsters()){
                if(monster.checkCollision(newPos) && !monster.checkIfDead()){
                    fightCounter=0;
                    fight(monster);
                }
            }

        }

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
    public int makeAttack(){
        return 1+randomGen.nextInt(20) + attackBonus;
    }
    public int getHitDamage() {
         return 1+randomGen.nextInt(damageDie) + damageBonus;
    }

    public void setVelocity(Position newVelocity){
        velocity = newVelocity;
    }
    public void addVelocity(Position dV){
        velocity = velocity.add(dV);
    }

    public Position getVelocity(){ return velocity; }
    public Position getPosition(){ return position; }

    private void fight(Fightable monster){
        if(makeAttack() >= monster.getArmorClass() ){
            int damage = getHitDamage();
            monster.takeDamage(damage);
            state.getEventLog().addLine("You hit the "+ monster.getName().toLowerCase() + " for "+damage + " damage!");
//            System.out.println("You hit the "+ monster.getName().toLowerCase() + " for "+damage + " damage!");
        }else {
            state.getEventLog().addLine("You miss the "+ monster.getName().toLowerCase()+"!");
        }
        if(!monster.checkIfDead()){
            if(monster.makeAttack() >= armorClass){
                int damage = monster.getDamage();
                currentLife -= damage;
                state.getEventLog().addLine("The " + monster.getName().toLowerCase() + " hits you for "+damage + " damage!");
                soundEffects.playEffect("onHit");

                if(currentLife <= 0){
                    die(monster, damage);
                }



            } else {
                state.getEventLog().addLine("The "+ monster.getName().toLowerCase() + " misses!");
            }
        } else {
            xp += monster.getXpValue();
            state.getEventLog().addLine("You killed the " + monster.getName().toLowerCase() + "!");
            state.getEventLog().addLine("Got " + monster.getXpValue() + " XP!");
        }
    }

    public void pickUpItem() {
        Item itemToPickUp = null;
        for(Item item: state.getItems() ){
            if( item.position.getXAsInt() == position.getXAsInt() && item.position.getYAsInt() == position.getYAsInt() ){
                itemToPickUp = item;
                break;
            }
        }
        if(itemToPickUp!=null){
            inventory.add(itemToPickUp);
            state.removeItem(itemToPickUp);
            state.getEventLog().addLine("You found a " + itemToPickUp.getName()+"!");
            updateStats(itemToPickUp);
            state.getSidePanel().updateInventory(inventory);
        }


    }

    public void lvlUp() {
        if (xp>=10) {
            level = level+1;
            xp-=10;
            maxLife=maxLife+5;
            currentLife=maxLife;

            state.getEventLog().addLine("You reached level " + level + "!");
        }
    }

    private void die(Fightable monster, int damage){
        dead = true;

        String message = String.format("You died!%nThe %s hit you for %d damage, killing you.%nYou reached level %d.",
                monster.getName().toLowerCase(), damage, level);

        GraphicalEffect bloodSpatter = new GraphicalEffect("Images/blood1.bmp",new Position(25,15),
                "You died!",
                "The vicious "+monster.getName().toLowerCase() + " killed you!",
                "You reached level " + level + ".");
        Entity blood = new Entity(bloodSpatter);
        state.addEntity(blood);
        soundEffects.playEffect("die");

        //Write out some message on top of blood spatter here.


    }

    private void updateStats(Item i){

        armorClass += i.getExtraAC();
        attackBonus += i.getAttackBonus();
        if(i.getDamageDie() > damageDie){
            damageDie = i.getDamageDie();
        }

        damageBonus += i.getDamageBonus();

        currentLife += i.getExtraHP();
        maxLife += i.getExtraHP();

    }

    public int getHP(){
        return currentLife;
    }

    public int getMaxLife(){
        return maxLife;
    }

    public int getXp(){
        return xp;
    }

    public int getLevel(){
        return level;
    }

    public int getDamageDie(){
        return damageDie;
    }

    public int getDamageBonus(){
        return damageBonus;
    }

    public int getAC(){
        return armorClass;
    }

    public void setState(GameState newState){
        state = newState;

    }

    public List<Item> getInventory(){
        return inventory;
    }

    public void setPosition(Position newPos){
        for(MapLvl m: state.getMapComponents()){
            if(m.checkIfIn(newPos)){
                position=newPos;
                return;
            }
        }
    }

}

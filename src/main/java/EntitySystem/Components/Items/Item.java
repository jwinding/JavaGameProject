package EntitySystem.Components.Items;

import EntitySystem.Interfaces.Drawable;
import EntitySystem.Interfaces.MapLvl;
import EntitySystem.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

public abstract class Item implements Drawable, MapLvl {

public Position position;

    int z = 10;
    private String name;

    private int extraAC = 0;
    private int extraHP = 0;
    private int damageDie = 0;
    private int attackBonus = 0;
    private int healthOnPickup = 0;
    private int damageBonus = 0;

    public int getDamageBonus() {
        return damageBonus;
    }

    public void setDamageBonus(int damageBonus) {
        this.damageBonus = damageBonus;
    }

    public Item(String name, Position position, TextCharacter itemCharacter, TextColor color, TextColor backgroundColor) {
        this.name=name;
        this.position = position;
        this.itemCharacter = itemCharacter;
        this.color = color;
        this.backgroundColor = backgroundColor;
    }

    private TextCharacter itemCharacter;
    private TextColor color;
    private TextColor backgroundColor;

    public String getName(){
        return name;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public void render(Screen screen) {

        screen.setCharacter(position.getXAsInt(),position.getYAsInt(), itemCharacter);
        }



    public int getExtraAC() {
        return extraAC;
    }

    public void setExtraAC(int extraAC) {
        this.extraAC = extraAC;
    }

    public int getExtraHP() {
        return extraHP;
    }

    public void setExtraHP(int extraHP) {
        this.extraHP = extraHP;
    }

    public int getDamageDie() {
        return damageDie;
    }

    public void setDamageDie(int damageDie) {
        this.damageDie = damageDie;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public int getHealthOnPickup() {
        return healthOnPickup;
    }

    public void setHealthOnPickup(int healthOnPickup) {
        this.healthOnPickup = healthOnPickup;
    }




    }



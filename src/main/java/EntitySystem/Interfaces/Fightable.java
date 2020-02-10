package EntitySystem.Interfaces;

import EntitySystem.Position;

public interface Fightable {

    boolean checkCollision(Position pos);

    int makeAttack();
    int getDamage();

    int getArmorClass();

    void takeDamage(int damage);

    boolean checkIfDead();
    int getXpValue();

    String getName();
}

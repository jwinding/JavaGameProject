package EntitySystem.Interfaces;

import EntitySystem.Position;

public interface Physical {

    boolean checkCollision(Position pos);
}

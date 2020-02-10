package EntitySystem.Interfaces;

import EntitySystem.Position;
import scene.AbstractGameScene;

public interface Triggerable {


    boolean checkSteppedOn(Position position);

    void trigger(AbstractGameScene currentScene);


}

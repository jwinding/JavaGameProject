package scene;

import EntitySystem.Drawable;
import EntitySystem.Entity;
import EntitySystem.Physical;
import EntitySystem.Updateable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameState {


    private Set<Drawable> drawables;
    private Set<Updateable> updateables;
    private Set<Physical> physicals;

    private Map<Long,Entity> entities;

    public GameState() {
        drawables = new HashSet<>();
        updateables = new HashSet<>();
        physicals = new HashSet<>();
        entities = new HashMap<>();
    }

    public void addEntity(Entity e){
        entities.put(e.getId(), e);

        for(Object component: e.getComponents()){
            addComponent(component);
        }
    }

    private void addComponent( Object obj){
        if(obj instanceof Drawable)
            drawables.add((Drawable) obj);
        if(obj instanceof Updateable)
            updateables.add((Updateable) obj);
        if(obj instanceof Physical)
            physicals.add((Physical) obj);
    }


    public Set<Drawable> getDrawables(){
        return drawables;
    }
    public Set<Updateable> getUpdateables(){
        return updateables;
    }
    public Set<Physical> getPhysicals(){
        return physicals;
    }



}

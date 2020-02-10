package scene;

import EntitySystem.*;
import EntitySystem.Components.GUI.EventLog;
import EntitySystem.Components.Items.Item;
import EntitySystem.Components.GUI.SidePanel;
import EntitySystem.Components.Player;
import EntitySystem.Interfaces.*;

import java.util.*;

public class GameState {


    private List<Drawable> drawables;
    private Set<Updateable> updateables;
    private Set<Physical> physicals;
    private Set<MapLvl> mapComponents;
    private Set<Fightable> monsters;
    private Set<Triggerable> triggers;
    private Set<Item> items;

    private EventLog eventLog;
    private SidePanel sidePanel;

    private Player player;

    private Set<Object> thingsToRemove;



    private Map<Long,Entity> entities;

    public GameState() {
        drawables = new ArrayList<>();
        updateables = new HashSet<>();
        physicals = new HashSet<>();
        mapComponents = new HashSet<>();
        monsters = new HashSet<>();
        items = new HashSet<>();
        triggers = new HashSet<>();

        thingsToRemove = new HashSet<>();

        entities = new HashMap<>();
    }

    public void addEntity(Entity e){
        entities.put(e.getId(), e);

        for(Object component: e.getComponents()){
            addComponent(component);
        }
    }

    private void addComponent( Object obj){
        if(obj instanceof Drawable) {
            drawables.add((Drawable) obj);
            drawables.sort( (o1, o2) -> o2.getZ() - o1.getZ());
        }
        if(obj instanceof Updateable)
            updateables.add((Updateable) obj);
        if(obj instanceof Physical)
            physicals.add((Physical) obj);
        if(obj instanceof MapLvl)
            mapComponents.add((MapLvl) obj);
        if(obj instanceof Item)
            items.add((Item) obj);
        if(obj instanceof Fightable)
            monsters.add((Fightable) obj);
        if(obj instanceof Triggerable)
            triggers.add((Triggerable) obj);

        if(obj instanceof EventLog)
            eventLog = (EventLog) obj;
        if(obj instanceof SidePanel)
            sidePanel = (SidePanel) obj;

        if(obj instanceof Player)
            player = (Player) obj;

    }


    public List<Drawable> getDrawables(){
        return drawables;
    }
    public Set<Updateable> getUpdateables(){
        return updateables;
    }
    public Set<Physical> getPhysicals(){
        return physicals;
    }
    public Set<MapLvl> getMapComponents(){
        return mapComponents;
    }
    public Set<Fightable> getMonsters(){
        return monsters;
    }
    public Set<Triggerable> getTriggers(){
        return triggers;
    }
    public Set<Item> getItems(){
        return items;
    }
    public SidePanel getSidePanel() {
        return sidePanel;
    }
    public EventLog getEventLog() {
        return eventLog;
    }

    public void removeItem(Item item){
        addToRemoveQueue(item);
    }
    public void addToRemoveQueue(Object o){
        thingsToRemove.add(o);
    }

    public void removeThings(){
        for(Object o: thingsToRemove){
            removeFromEverywhere(o);
        }
        thingsToRemove.clear();
    }

    public void removeFromEverywhere(Object o){
        drawables.remove(o);
        updateables.remove(o);
        physicals.remove(o);
        monsters.remove(o);
        items.remove(o);

    }

    public Player getPlayer(){
        return player;
    }


}

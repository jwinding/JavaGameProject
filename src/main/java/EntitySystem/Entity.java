package EntitySystem;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private static long lastId=1;

    private static List<Object> components;

    private final long id;

    public long getId(){ return id;}
    public List<Object> getComponents() {
        return components;
    }

    public Entity(){
        components = new ArrayList<>();
        id = lastId++;
    }
    public Entity(List<Object> components){
        this.components = components;
        id = lastId++;
    }

    public Entity(Object... components){
        this.components = List.of( components );
        id = lastId++;
    }

    public void addComponent(Object component){
        components.add(component);
    }

    @Override
    public String toString(){
        String s="Entity " + id + ", components: ";
        for(Object o:components)
            s += o.toString();
        return s;
    }

}

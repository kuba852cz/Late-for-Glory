package Models;

import java.util.ArrayList;

public class Room {

    private String id;
    private String name;
    private String description;
    private ArrayList<String> neighbors;
    private ArrayList<String> lootTable;

    public Item addItem(){
        return null;
    }

    public Item removeItem(){
        return null;
    }

    public String getDescription(){
        return null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "game.Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", neighbors=" + neighbors +
                ", lootTable=" + lootTable +
                '}';
    }

}

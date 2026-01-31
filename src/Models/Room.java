package Models;

import Models.Characters.NPC;

import java.util.ArrayList;

public class Room {

    private String id;
    private String name;
    private String description;
    private ArrayList<String> neighbors;
    private ArrayList<String> items;
    private ArrayList<String> npcs;

    public Room() {
        this.neighbors = new ArrayList<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    public void addItem(String itemId){
        this.items.add(itemId);
    }

    public void removeItem(String itemId){
        this.items.remove(itemId);
    }

    public String getDescription(){
        return description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public ArrayList<String> getNpcs() {
        return npcs;
    }


    @Override
    public String toString() {
        return "game.Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", neighbors=" + neighbors +
                ", items=" + items +
                '}';
    }

}

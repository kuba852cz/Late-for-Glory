package Models;


import java.util.ArrayList;
/**
 * Represents a location within the game world.
 * This class stores information about the room's description,
 * its connections to other rooms (neighbors), and the IDs of items and NPCs present.
 */
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

    /**
     * @return a list of IDs for all rooms connected to this one.
     */
    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    /**
     * Adds an item ID to/from the room's collection.
     * @param itemId the unique identifier of the item.
     */
    public void addItem(String itemId){
        this.items.add(itemId);
    }

    /**
     * Removes an item ID to/from the room's collection.
     * @param itemId the unique identifier of the item.
     */
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

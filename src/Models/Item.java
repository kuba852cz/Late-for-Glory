package Models;

/**
 * Represents an object in the game world.
 * Items can be collected by the player, stored in the inventory,
 * and used to progress through the story or complete quests.
 * @author Jakub Kubíček
 */
public class Item {

    private String id;
    private String name;
    private String description;

    public String getName (){
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}

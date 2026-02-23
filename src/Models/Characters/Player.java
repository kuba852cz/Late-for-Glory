package Models.Characters;

import Logic.GameData;
import Models.Item;
import Models.Room;

import java.util.ArrayList;

/**
 * Represents the player character (Jon "The Wonder" Oliveira).
 * Manages the inventory and various status flags that track the player's
 * physical condition and equipment for the final fight.
 */
public class Player {

    private ArrayList<Item> inventory;
    private String id;
    private String name;
    private Room currentRoom;
    private String homeLocationId;
    private boolean isFit = false;
    private boolean knowsWinningMove = false;
    private boolean hasGlovesOn = false;
    private boolean hasGuardOn = false;
    private boolean drankProtein = false;

    public Player() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Identifies the required items that the player has not yet collected.
     * It compares a predefined list of necessary items (gloves, guard, contract)
     * against the player's current inventory and equipped status,
     * filtering out the items already possessed or used.
     *
     * @return an {@code ArrayList<Item>} containing the required items still missing
     */
    public ArrayList<String> stillNeed(GameData gameData) {
        ArrayList<String> neededItems = new ArrayList<>();
        Item gloves = gameData.findItem("item_gloves");
        Item guard = gameData.findItem("item_guard");
        Item contract = gameData.findItem("item_contract");

        if (!hasGlovesOn) {
            neededItems.add(gloves.getName());
        }
        if (!hasGuardOn) {
            neededItems.add(guard.getName());
        }

       neededItems.add(contract.getName());

        neededItems.removeIf(needed -> {
            for (Item invItem : inventory) {
                if (needed.equals(invItem.getName())) {
                    return true;
                }
            }
            return false;
        });
        return neededItems;
    }

    /**
     * Adds an item to the player's inventory.
     * @param item the item to be added
     * @return true if the item was successfully added
     */
    public boolean pickUpItem(Item item){
        inventory.add(item);
        return true;
    }
    /**
     * Removes an item from the player's inventory.
     * @param item the item to be removed
     */
    public void dropItem(Item item){
        inventory.remove(item);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public String getHomeLocationId() {
        return homeLocationId;
    }

    public boolean isFit() {
        return isFit;
    }

    public void setFit(boolean fit) {
        isFit = fit;
    }

    public boolean isKnowsWinningMove() {
        return knowsWinningMove;
    }

    public void setKnowsWinningMove(boolean knowsWinningMove) {
        this.knowsWinningMove = knowsWinningMove;
    }

    public boolean isHasGlovesOn() {
        return hasGlovesOn;
    }

    public void setHasGlovesOn(boolean hasGlovesOn) {
        this.hasGlovesOn = hasGlovesOn;
    }

    public boolean isHasGuardOn() {
        return hasGuardOn;
    }

    public void setHasGuardOn(boolean hasGuardOn) {
        this.hasGuardOn = hasGuardOn;
    }

    public boolean isDrankProtein() {
        return drankProtein;
    }

    public void setDrankProtein(boolean drankProtein) {
        this.drankProtein = drankProtein;
    }
}

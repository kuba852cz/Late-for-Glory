package Models.Characters;

import Logic.GameData;
import Models.Item;
import Models.Room;

import java.util.ArrayList;

public class Player {

    private ArrayList<Item> inventory;
    private String id;
    private String name;
    private Room currentRoom;
    private String homeLocationId;
    private boolean isFit = false;
    private boolean knowsWinningMove = false;

    public Player() {
        this.inventory = new ArrayList<>();
    }

    public boolean pickUpItem(Item item){
        inventory.add(item);
        return true;
    }

    public void dropItem(Item item){
        inventory.remove(item);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getId() {
        return id;
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
}

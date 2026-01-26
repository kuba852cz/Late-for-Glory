package Models.Characters;

import Models.Item;
import Models.Room;

import java.util.ArrayList;

public class Player extends Character {

    private ArrayList<Item> inventory;

    public Player(String id, String name, String role, Room startingRoom, String notes) {
        super(id, name, role, startingRoom, notes);
        this.inventory = new ArrayList<>();
    }

    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }

    public boolean pickUpItem(Item item){
        return false;
    }

    public void dropItem(Item item){

    }

    public boolean hasItem(Item item){
        return false;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
}

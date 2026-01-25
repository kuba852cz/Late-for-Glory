package Models.Characters;

import Models.Item;
import Models.Room;

public class Player extends Character {

    public void setRoom(Room room) {
        this.room = room;
    }

    private Room room;

    public void setCurrentRoom(Room currentRoom){}

    public boolean pickUpItem(Item item){
        return false;
    }

    public void dropItem(Item item){}

    public boolean hasItem(Item item){
        return false;
    }



}

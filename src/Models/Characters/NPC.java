package Models.Characters;

import Models.Item;
import Models.Room;

public class NPC extends Character {


    public NPC(String id, String name, String role, Room startingRoom, String notes) {
        super(id, name, role, startingRoom, notes);
    }

    public String getDialogue(){
        return null;
    }

    public void giveItem(Item item){
    }

}

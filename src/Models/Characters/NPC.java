package Models.Characters;

import Models.Item;
import Models.Room;

public class NPC extends Character {

    public NPC(String name, String role, Room startingRoom) {
        super(name, role, startingRoom);
    }

    public String getDialogue(){
        return null;
    }

    public void giveItem(Item item){
    }

}

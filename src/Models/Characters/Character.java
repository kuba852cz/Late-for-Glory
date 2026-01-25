package Models.Characters;

import Models.Room;

public abstract class Character {

    protected String name;
    protected String role;
    protected Room currentRoom;

    public Character(String name, String role, Room startingRoom) {
        this.name = name;
        this.role = role;
        this.currentRoom = startingRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

}

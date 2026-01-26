package Models.Characters;

import Models.Room;

public abstract class Character {

    protected String id;
    protected String name;
    protected String role;
    protected Room currentRoom;
    protected String notes;

    public Character(String id, String name, String role, Room startingRoom, String notes) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.currentRoom = startingRoom;
        this.notes = notes;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

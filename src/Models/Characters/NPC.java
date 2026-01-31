package Models.Characters;

import Models.Item;
import Models.Room;

public class NPC {

    private String id;
    private String name;
    private String role;
    private Room currentRoom;
    private String notes;
    private String starterDialogue;
    private boolean questFinished;

    public NPC() {
    }

    public String getDialogue(){
        return null;
    }

    public void giveItem(Item item){
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

    public boolean isQuestFinished() {
        return questFinished;
    }

    public void setQuestFinished(boolean questFinished) {
        this.questFinished = questFinished;
    }

    public String getStarterDialogue() {
        return starterDialogue;
    }

    public void setStarterDialogue(String starterDialogue) {
        this.starterDialogue = starterDialogue;
    }
}

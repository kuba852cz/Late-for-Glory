package Models.Characters;

import Models.Room;

/**
 * Represents a Non-Player Character (NPC) in the game.
 * This class stores data about characters, their roles, and their interaction states.
 * @author Jakub Kubíček
 */
public class NPC {

    private String id;
    private String name;
    private String role;
    private String homeLocationId;
    private String notes;
    private String starterDialogue;
    private boolean questFinished;

    public NPC() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
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

}

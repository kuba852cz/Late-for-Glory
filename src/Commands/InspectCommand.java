package Commands;

import Logic.GameData;
import Models.Characters.NPC;
import Models.Characters.Player;
import Models.Item;

public class InspectCommand implements Command {
    private Player player;
    private GameData gameData;

    public InspectCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    /**
     * Inspects a specific item or provides details about the current room.
     *
     * @param targetingName the item the player wants to inspect (if blank, the player will inspect the room)
     * @return Description of the item or, if targetingName is blank, a description of the room where the player is located.
     */
    @Override
    public String execute(String targetingName) {

        if (targetingName.isEmpty()) {
            String popis = "Jsi v: " + player.getCurrentRoom().getName() + "\n" + player.getCurrentRoom().getDescription();

            if (!player.getCurrentRoom().getItems().isEmpty()) {
                popis += "\n" + "Vidis tu predmety: ";
                for (String itemId : player.getCurrentRoom().getItems()) {
                    Item item = gameData.findItem(itemId);
                    popis += item.getName() + ", ";
                }
                popis = popis.substring(0, popis.length() - 2);
            }

            if (!player.getCurrentRoom().getNpcs().isEmpty()) {
                popis += "\n" + "Vidis tu postavy: ";
                for (String npcId : player.getCurrentRoom().getNpcs()) {
                    NPC npc = gameData.findNPC(npcId);
                    popis += npc.getName() + ", ";
                }
                popis = popis.substring(0, popis.length() - 2);
            }

            return popis;
        }

        for (String inspectedItemID : player.getCurrentRoom().getItems()) {
            Item item = gameData.findItem(inspectedItemID);
            if (item.getName().equalsIgnoreCase(targetingName)) {
                return item.getDescription();
            }
        }

        for (String npcId : player.getCurrentRoom().getNpcs()) {
            NPC npc = gameData.findNPC(npcId);
            if (npc.getName().equalsIgnoreCase(targetingName)) {
                return npc.getNotes();
            }
        }



        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(targetingName)) {
                return item.getDescription();
            }
        }
        return "Nic zajimaveho se zde nenachazi.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

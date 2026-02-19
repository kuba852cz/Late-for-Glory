package Commands;

import Logic.GameData;
import Models.Characters.Player;
import Models.Item;

public class TakeCommand implements Command {

    private Player player;
    private GameData gameData;

    public TakeCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    /**
     * Adds an item from the current room to the player's inventory.
     * The player cannot pick up specific heavy items like "item_machines".
     *
     * @param targetingName the name of the item the player wants to pick up
     * @return A confirmation message that the item was added to the inventory, or an error if it cannot be picked up.
     */
    @Override
    public String execute(String targetingName) {
        if (targetingName.isEmpty()) {
            return "Co chceš sebrat? (Napiš: vezmi <předmět>)";
        }

        for (String addingItemID : player.getCurrentRoom().getItems()) {
            Item item = gameData.findItem(addingItemID);
            if (item.getName().equalsIgnoreCase(targetingName) && item.getId().equalsIgnoreCase("item_machines")) {
                return "Tento predmet nelze sebrat";
            }
            if (item.getName().equalsIgnoreCase(targetingName) && player.getInventory().size()<3) {
                player.pickUpItem(item);
                player.getCurrentRoom().removeItem(item.getId());
                return "Predmet " + targetingName + " byl sebran";
            }else{
                return "nemas dostatek mistav inventari";
            }
        }
        return "Predmet " + targetingName + " neni v teto mistnosti.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

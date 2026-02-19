package Commands;

import Models.Characters.Player;
import Models.Item;

public class InventoryCommand implements Command {

    private Player player;
    public InventoryCommand(Player player) {
        this.player = player;
    }

    /**
     * Displays the contents of the player's inventory.
     *
     * @param targetingName not used for this command (ignored)
     * @return A list of items the player currently has in their inventory.
     */
    @Override
    public String execute(String targetingName) {
        if (player.getInventory().isEmpty()){
            return "Inventar je prazdny";
        }

        String items = "V Inventari mas: ";
        for (Item item : player.getInventory()){
            items += item.getName() + ", ";
        }
        if (items.endsWith(", ")) {
            items = items.substring(0, items.length() - 2);
        }

        return items;
    }

    @Override
    public boolean exit() {
        return false;
    }
}

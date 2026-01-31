package Commands;

import Models.Characters.Player;
import Models.Item;

public class InventoryCommand implements Command {

    private Player player;
    public InventoryCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String cilovyNazev) {
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

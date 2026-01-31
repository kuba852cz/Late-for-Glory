package Commands;

import Models.Characters.Player;
import Models.Item;

public class DropCommand implements Command {

    private Player player;

    public DropCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String targetingName) {
        if (targetingName.isEmpty()) {
            return "Co chceš vyhodit? (Napiš: poloz <předmět>)";
        }

        for (Item removingItem : player.getInventory()) {
            if (removingItem.getName().equalsIgnoreCase(targetingName)){
                player.dropItem(removingItem);
                player.getCurrentRoom().addItem(removingItem.getId());
                return "Predmet " + targetingName + " byl odstranen";
            }
        }
        return "Predmet " + targetingName +" nemas v inventari.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

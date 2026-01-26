package Commands;

import Models.Characters.Player;
import Models.Item;

public class TakeCommand implements Command {

    private Player player;

    public TakeCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String targetingName) {
        if (targetingName.isEmpty()) {
            return "Co chceš sebrat? (Napiš: vezmi <předmět>)";
        }

        for (Item addingItem : player.getInventory()) {

            if (addingItem.getName().equalsIgnoreCase(targetingName)){
                player.pickUpItem(addingItem);
                player.getCurrentRoom().removeItem(addingItem.getId());
                return "Predmet " + targetingName + " byl sebran";
            }
        }
        return "Predmet neni v teto mistnosti.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

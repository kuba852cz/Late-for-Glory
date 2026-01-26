package Commands;

import Logic.GameData;
import Models.Characters.Player;
import Models.Item;
import jdk.jshell.spi.SPIResolutionException;

public class DropCommand implements Command {

    private Player player;
    private GameData gameData;

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
        return "Predmet nemas v inventari.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

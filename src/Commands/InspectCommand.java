package Commands;

import Logic.Game;
import Logic.GameData;
import Models.Characters.Player;
import Models.Item;

public class InspectCommand implements Command {
    private Player player;
    private GameData gameData;

    public InspectCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    @Override
    public String execute(String targetingName) {
        if (targetingName.isEmpty()) {
            String popis = "Jsi v: " + player.getCurrentRoom().getName() + "\n" +player.getCurrentRoom().getDescription();
            if (!player.getCurrentRoom().getItems().isEmpty()) {
                popis += "\n" + "Vidis tu: ";
                for (String itemId : player.getCurrentRoom().getItems()){
                    Item item = gameData.findItem(itemId);
                    popis += item.getName() + ", ";
                }
                popis = popis.substring(0, popis.length() - 2);
            }else{
                popis += "Nic zajimaveho tady nevidis.";
            }
            return popis;
        }

        for (String addingItemID : player.getCurrentRoom().getItems()) {
            Item item = gameData.findItem(addingItemID);
            if (item.getName().equals(targetingName)) {
                return item.getDescription();
            }
        }

        for (Item item : player.getInventory()) {
            if (item.getName().equals(targetingName)) {
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

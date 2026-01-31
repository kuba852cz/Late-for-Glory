package Commands;

import Logic.Game;
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

    @Override
    public String execute(String targetingName) {
        if (targetingName.isEmpty()) {
            return "Co chceš sebrat? (Napiš: vezmi <předmět>)";
        }

        for (String addingItemID : player.getCurrentRoom().getItems()) {
            Item item = gameData.findItem(addingItemID);
            if (item.getName().equals(targetingName)) {
                player.pickUpItem(item);
                player.getCurrentRoom().removeItem(item.getId());
                return "Predmet " + targetingName + " byl sebran";
            }
        }
        return "Predmet " + targetingName + " neni v teto mistnosti.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

package Commands;

import Models.Characters.Player;
import Models.Item;

public class InspectCommand implements Command {
    private Player player;

    public InspectCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String targetingName) {
        return player.getCurrentRoom().getDescription();
    }

    @Override
    public boolean exit() {
        return false;
    }
}

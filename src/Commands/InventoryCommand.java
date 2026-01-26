package Commands;

import Models.Characters.Player;

public class InventoryCommand implements Command {
    public InventoryCommand(Player player) {
    }

    @Override
    public String execute(String cilovyNazev) {

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

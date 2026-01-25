package Commands;

import Models.Characters.Player;

public class DropCommand implements Command {

    public DropCommand(Player player) {
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

package Commands;

import Models.Characters.Player;

public class UseCommand implements Command {
    public UseCommand(Player player) {
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

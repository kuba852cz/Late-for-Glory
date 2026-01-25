package Commands;

import Models.Characters.Player;

public class TakeCommand implements Command {
    public TakeCommand(Player player) {
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

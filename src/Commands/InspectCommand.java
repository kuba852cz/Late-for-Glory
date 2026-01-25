package Commands;

import Models.Characters.Player;

public class InspectCommand implements Command {
    public InspectCommand(Player player) {
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

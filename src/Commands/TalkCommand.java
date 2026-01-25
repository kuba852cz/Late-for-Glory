package Commands;

import Models.Characters.Player;

public class TalkCommand implements  Command {
    public TalkCommand(Player player) {
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

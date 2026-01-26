package Commands;

import Models.Characters.Player;

public class TalkCommand implements  Command {
    public TalkCommand(Player player) {
    }

    @Override
    public String execute(String targetingName) {

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

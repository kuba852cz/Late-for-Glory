package Commands;

import Logic.Game;

public class HelpCommand implements Command {
    public HelpCommand(Game game) {
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

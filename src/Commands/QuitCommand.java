package Commands;

import Logic.Game;

public class QuitCommand implements Command {
    public QuitCommand(Game game) {
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

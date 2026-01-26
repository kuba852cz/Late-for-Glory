package Commands;

import Logic.Game;

public class QuitCommand implements Command {
    public QuitCommand(Game game) {
    }

    @Override
    public String execute(String cilovyNazev) {
        exit();
        return "Hra ukoncena.";
    }

    @Override
    public boolean exit() {
        System.exit(0);
        return true;
    }
}

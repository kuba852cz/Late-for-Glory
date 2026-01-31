package Commands;

import Logic.Game;

public class QuitCommand implements Command {

    private Game game;
    public QuitCommand(Game game) {
        this.game = game;
    }

    @Override
    public String execute(String cilovyNazev) {
        game.setGameOver(true);
        return "Hra ukoncena.";
    }

    @Override
    public boolean exit() {
        return true;
    }
}

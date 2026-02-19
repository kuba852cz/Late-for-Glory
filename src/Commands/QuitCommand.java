package Commands;

import Logic.Game;

public class QuitCommand implements Command {

    private Game game;
    public QuitCommand(Game game) {
        this.game = game;
    }

    /**
     * Ends the current game session by setting the exit flag to true.
     *
     * @param targetingName not used for this command (ignored)
     * @return A message confirming the end of the game.
     */
    @Override
    public String execute(String targetingName) {
        return "Hra ukoncena.";
    }

    @Override
    public boolean exit() {
        return true;
    }
}

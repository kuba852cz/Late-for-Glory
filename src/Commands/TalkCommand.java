package Commands;

import Logic.Game;
import Logic.GameData;
import Models.Characters.NPC;
import Models.Characters.Player;

public class TalkCommand implements  Command {

    private Player player;
    private GameData gameData;

    public TalkCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    @Override
    public String execute(String targetingName) {
        if (targetingName.isEmpty()) {
            return "S kým chceš mluvit?";
        }



        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}

package Commands;

import Logic.GameData;
import Models.Characters.Player;
import Models.Room;

public class GoCommand implements Command {

    private Player player;
    private GameData gameData;

    public GoCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    @Override
    public String execute(String targetingName) {
        if (targetingName.isEmpty()) {
            return "Kam chceš jít? (Napiš: jdi <mistnost>)";
        }

        for (String idNeighbor : player.getCurrentRoom().getNeighbors()) {

            Room neighborRoom = gameData.findRoom(idNeighbor);

            if (neighborRoom.getName().equalsIgnoreCase(targetingName)) {
                player.setCurrentRoom(neighborRoom);
                return "Přesunul ses do: " + neighborRoom.getName() + "\n" +
                        neighborRoom.getDescription() + "\n" + neighborRoom.getNeighbors();

            } else if (player.getCurrentRoom().getName().equalsIgnoreCase(targetingName)) {
                return "V této místnosti právě ted jsi.";
            }
        }

        return "Do mistnosti " + targetingName + " se odtud nedostanes.";
    }


    @Override
    public boolean exit() {
        return false;
    }
}

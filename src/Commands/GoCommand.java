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
    public String execute(String cilovyNazev) {
        if (cilovyNazev.isEmpty()) {
            return "Kam chceš jít? (Napiš: jdi <mistnost>)";
        }

        for (String idSouseda : player.getCurrentRoom().getNeighbors()) {

            Room sousedniMistnost = gameData.findRoom(idSouseda);

            if (sousedniMistnost.getName().equalsIgnoreCase(cilovyNazev)) {
                player.setCurrentRoom(sousedniMistnost);
                return "Přesunul ses do: " + sousedniMistnost.getName() + "\n" +
                        sousedniMistnost.getDescription() + "\n" + sousedniMistnost.getNeighbors();

            } else if (player.getCurrentRoom().getName().equalsIgnoreCase(cilovyNazev)) {
                return "V této místnosti právě ted jsi.";
            }
        }

        return "Tam se odsud jít nedá. (Zkontroluj, jestli jsi název napsal správně)";
    }


    @Override
    public boolean exit() {
        return false;
    }
}

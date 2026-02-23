package Commands;

import Logic.GameData;
import Models.Characters.NPC;
import Models.Characters.Player;
import Models.Item;
import Models.Room;

public class GoCommand implements Command {

    private Player player;
    private GameData gameData;

    public GoCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    /**
     * Moves the player to the specified location. Blocks entrance to the ring if the player hasn't met the requirements.
     *
     * @param targetingName name of the location the player wants to enter
     * @return Description of the new room, or an error message if the movement is blocked or invalid.
     * @author Jakub Kubíček
     */
    @Override
    public String execute(String targetingName) {

        if (targetingName.isEmpty()) {
            return "Kam chceš jít? (Napiš: jdi <mistnost>)";
        }

        for (String idNeighbor : player.getCurrentRoom().getNeighbors()) {

            Room neighborRoom = gameData.findRoom(idNeighbor);

            if (neighborRoom.getName().equalsIgnoreCase(targetingName)) {
                if (neighborRoom.getId().equalsIgnoreCase("room_ring")) {
                    NPC trener = gameData.findNPC("NPC_trainer");
                    if (!trener.isQuestFinished()) {
                        return "Trenér ti zastoupil cestu: \"Nikam nejdeš, mladej! Nejdřív mi ukaž, že na to máš!\"";
                    }
                }
                player.setCurrentRoom(neighborRoom);
                String result = "Přesunul ses do: " + neighborRoom.getName() + "\n" + neighborRoom.getDescription() + "\nPředměty v této místnosti: ";

                if (neighborRoom.getItems().isEmpty()){
                    result += "Žádné";
                }else{
                    for (String itemsID : neighborRoom.getItems()){
                        Item item = gameData.findItem(itemsID);
                        result += item.getName() + ", ";
                    }
                    result = result.substring(0, result.length()-2);
                }

                result += "\nPostavy v této místnosti: ";
                if (neighborRoom.getNpcs().isEmpty()){
                    result += "Žádné";
                }else{
                    for (String npcID : neighborRoom.getNpcs()){
                        NPC npc = gameData.findNPC(npcID);
                        result += npc.getName() + ", ";
                    }
                    result = result.substring(0, result.length()-2);
                }

                result += "\nSousední místnosti: ";
                for (String neighborsID : neighborRoom.getNeighbors()){
                    Room r = gameData.findRoom(neighborsID);
                    result += r.getName() + ", ";
                }
                result = result.substring(0, result.length()-2);
                return result;

            } else if (player.getCurrentRoom().getName().equalsIgnoreCase(targetingName)) {
                return "V této místnosti právě teď jsi.";
            }
        }

        return "Do místnosti " + targetingName + " se odtud nedostaneš.";
    }


    @Override
    public boolean exit() {
        return false;
    }
}

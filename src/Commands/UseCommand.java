package Commands;

import Logic.Game;
import Logic.GameData;
import Models.Characters.Player;
import Models.Item;

public class UseCommand implements Command {

    private Player player;
    private GameData gameData;

    public UseCommand(Player player,  GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    /**
     * Executes an action with a specific item or object.
     * The result depends on the type of object and whether the player meets the necessary requirements.
     *
     * @param targetingName the name of the item or object the player wants to use
     * @return A message confirming the object's activation or an error if requirements are not met.
     */
    @Override
    public String execute(String targetingName) {

        if (targetingName.isEmpty()) {
            return "Co chceš použít? (Napiš: použij <předmět>)";
        }

        Item itemToUse = null;

        for(Item item : player.getInventory()){
            if(item.getName().equalsIgnoreCase(targetingName)){
                itemToUse = item;
                break;
            }
        }

        if(itemToUse == null){
            for(String itemID : player.getCurrentRoom().getItems()){
                Item item = gameData.findItem(itemID);
                if(item != null && item.getName().equalsIgnoreCase(targetingName)){
                    if (item.getId().equals("item_machines")) {
                        itemToUse = item;
                    } else {
                        return "Předmět '" + item.getName() + "' leží na zemi. Musíš ho nejdřív sebrat (příkaz: vezmi " + item.getName() + ").";
                    }
                    break;
                }
            }
        }

        if (itemToUse == null) {
            return "Takový předmět tu nemáš, ani ho nevidíš kolem sebe.";
        }

        switch (itemToUse.getId()){
            case "item_contract_signed":
                return "Už máš podepsanou smlouvu.";
            case "item_contract":
                return "Nemůžeš zfalšovat podpis manažera!";
            case "item_machines":
                if(player.isDrankProtein()){
                    player.setFit(true);
                    return "Dal jsi si pořádně do těla a nyní jsi fyzicky připraven utkat se s Ryanem Garciou.";
                }
                return "Nejdřív si musíš dát protein, abys toho využil na maximum!";
            case "item_photo":
                player.setKnowsWinningMove(true);
                player.getInventory().remove(itemToUse);
                return "Prohlédl jsi si fotku a na zadní straně ti nechal Mike radu, jak porazit Ryana.";
            case "item_guard":
                player.setHasGuardOn(true);
                player.getInventory().remove(itemToUse);
                return "Nasadil jsi si chránič na zuby.";
            case "item_gloves":
                player.setHasGlovesOn(true);
                player.getInventory().remove(itemToUse);
                return "Nasadil jsi si na sebe rukavice.";
            case "item_protein":
                player.setDrankProtein(true);
                player.getInventory().remove(itemToUse);
                return "Vypil jsi protein a nyní můžeš naplno využít svou sílu!";
            default:
                return "Tento předmět nelze použít.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
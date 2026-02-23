package Commands;

import Logic.Game;
import Logic.GameData;
import Models.Characters.Player;

public class HelpCommand implements Command {

    private Player player;
    private GameData gameData;

    public HelpCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    /**
     * Displays the game map and a list of available commands.
     *
     * @param targetingName not used for this command (ignored)
     * @return The map and a list of commands the player can use
     */
    @Override
    public String execute(String targetingName) {
        String dvojiCara = "====================================================";
        String tenkaCara = "----------------------------------------------------";

        String mapa = """
                                     [  RING  ]
                                         ║
                                         ║
               [ KANCELÁŘ ] ═══════ [ TURNAJ ]         [ SPRCHY ]
                                         ║                  ║
                                         ║                  ║
               [  OBCHOD  ] ═══════ [ NÁMĚSTÍ] ═══════ [TĚLOCVIČNA]
                                         ║                  ║
                                         ║                  ║
                                     [  DOMOV  ]       [  ŠATNA  ]
                                         
                                     
                """;
        String aktualniPoloha = (" AKTUÁLNĚ SE NACHÁZÍŠ V: " + "[" + player.getCurrentRoom().getName() + "]");

        String prikazy = "Dostupné příkazy:\n" +
                "- jdi <místnost>\n" +
                "- seber <předmět>\n" +
                "- poloz <předmět>\n" +
                "- pouzij <předmět>\n"+
                "- prozkoumat <předmět>\n" +
                "- mluv <npc>\n" +
                "- pomoc\n"+
                "- inventar\n" +
                "- ukoncit";

        return "\n" + aktualniPoloha + "\n" + tenkaCara + "\n" + dvojiCara + "\n" + mapa + dvojiCara + "\n" + tenkaCara + "\n" + prikazy + "\n" + player.stillNeed(gameData);
    }

    @Override
    public boolean exit() {
        return false;
    }
}

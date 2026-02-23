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

        String prikazy = "Dostupné příkazy:\n\n" +
                "PŘEDMĚTY: \n" +

                "- seber <předmět>                - Přidání předmětu do inventáře.\n" +
                "- poloz <předmět>                - Položení předmětu z inventáře zpět do místosti.\n" +
                "- pouzij <předmět>               - Použití předmětu, vyvolá specifickou akci.\n"+
                "- inventar                       - Výpis předmětů ve tvém batohu.\n\n" +
                "LOKACE: \n" +

                "- jdi <místnost>                 - Vstup do sousední místnosti.\n" +
                "- prozkoumat <předmět>           - Zobrazí popis místnosti či předmětu.\n" +
                "- mluv <NPC>                     - Spustí dialog s daným NPC.\n\n" +
                "SYSTÉM: \n" +

                "- pomoc                          - Vypíše se tato nápověda.\n"+
                "- inventar                       - Výpis předmětů ve tvém batohu.\n" +
                "- ukoncit                        - Předčasné ukončení hry.";

        String playerNeed = "POTŘEBUJEŠ: " + player.stillNeed(gameData);

        return "\n" + aktualniPoloha + "\n" + tenkaCara + "\n" + dvojiCara + "\n" + mapa + dvojiCara + "\n" + tenkaCara + "\n" + prikazy + "\n" + playerNeed;
    }

    @Override
    public boolean exit() {
        return false;
    }
}

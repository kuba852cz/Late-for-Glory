package Commands;

import Logic.Game;
import Models.Characters.Player;

public class HelpCommand implements Command {

    private Player player;

    public HelpCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String cilovyNazev) {
        String dvojiCara = "====================================================";
        String tenkaCara = "----------------------------------------------------";

        String mapa = """
                                     [  RING  ]
                                         ║
                                         ║
               [ KANCELÁŘ ] ═══════ [ TURNAJ ] ═══════ [ SPRCHY ]
                                         ║
                                         ║
               [  OBCHOD  ] ═══════ [ NÁMĚSTÍ] ═══════ [TĚLOCVIČNA]
                                         ║
                                         ║
                                     [  DOMOV  ]
                                         ║
                                         ║
                                     [  ŠATNA  ]
                                     
                                     
                """;
        String aktualniPoloha = (" AKTUÁLNĚ SE NACHÁZÍŠ V: " + "[" + player.getCurrentRoom().getName() + "]");

        String prikazy = "Dostupné příkazy:\n" +
                "- jdi <místnost>\n" +
                "- vezmi <předmět>\n" +
                "- poloz <předmět>\n" +
                "- inventar\n" +
                "- prozkoumat <předmět>\n" +
                "- mluv <npc>\n" +
                "- ukoncit";

        return "\n" + aktualniPoloha + "\n" + tenkaCara + "\n" + dvojiCara + "\n" + mapa + prikazy;
    }

    @Override
    public boolean exit() {
        return false;
    }
}

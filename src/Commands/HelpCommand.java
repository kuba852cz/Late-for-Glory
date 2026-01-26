package Commands;

import Logic.Game;
import Models.Characters.Player;

public class HelpCommand implements Command {

    private Player player;

    public HelpCommand(Game game) {
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
        System.out.println(" AKTUÁLNĚ SE NACHÁZÍŠ V: " + "[" + player.getCurrentRoom() + "]");
        System.out.println(tenkaCara);
        System.out.println(dvojiCara);

        return mapa;
    }

    @Override
    public boolean exit() {
        return false;
    }
}

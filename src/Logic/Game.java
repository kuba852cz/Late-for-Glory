package Logic;

import Commands.*;
import Models.Characters.Player;
import Models.Room;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private boolean gameOver = false;
    private GameData world;
    private Player player;
    private HashMap<String, Command> commands;

    public void inicialization(){
        commands = new HashMap<>();

        world = GameData.loadGameDataFromResources("/gamedata.json");
        this.player = world.player;
        this.player.setCurrentRoom(world.findRoom(player.getHomeLocationId()));

        commands.put("poloz", new DropCommand(player));
        commands.put("jdi", new GoCommand(player, world));
        commands.put("pomoc", new HelpCommand(player));
        commands.put("prozkoumat", new InspectCommand(player, world));
        commands.put("inventar", new InventoryCommand(player));
        commands.put("ukoncit", new QuitCommand(this));
        commands.put("vezmi", new TakeCommand(player, world));
        commands.put("mluv", new TalkCommand(player));
        commands.put("pouzij", new UseCommand(player));
    }

    //prozatimni vygenerovana smycka (nasledne si ji udelam sam)
    public void start() {
        inicialization();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- JSI V MÍSTNOSTI: " + player.getCurrentRoom().getName() + " ---");
        System.out.println(player.getCurrentRoom().getDescription());
        System.out.println(player.getCurrentRoom().getNeighbors());

        while (!gameOver) {
            System.out.print("\n> ");
            // .trim() odstraní mezery na začátku a konci, aby to neblblo
            String input = scanner.nextLine().trim();

            // Pojistka, kdyby hráč jen odentroval prázdný řádek
            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split(" ");
            String commandName = parts[0];

            if (commands.containsKey(commandName)) {

                // PŘÍPRAVA PARAMETRU (String)
                String argument = "";

                // Pokud hráč napsal víc než jedno slovo (např. "jdi sklep"),
                // vezmeme to druhé slovo jako argument.
                if (parts.length > 1) {
                    argument = parts[1];
                }

                // Teď voláme execute a posíláme jen ten jeden String (nebo prázdný, pokud nic nenapsal)
                String result = commands.get(commandName).execute(argument);
                System.out.println(result);

            } else {
                System.out.println("Neznámý příkaz. Zkus: jdi <mistnost>");
            }
        }
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}

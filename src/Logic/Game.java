package Logic;

import Commands.*;
import Models.Characters.NPC;
import Models.Characters.Player;
import Models.Item;
import Models.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Game {

    private boolean gameOver = false;
    private GameData world;
    private Player player;
    private HashMap<String, Command> commands;

    /**
     * Initializes the game world by loading data from the JSON resource and
     * registering all available game commands.
     * @author Jakub Kubíček
     */
    public void inicialization(){
        commands = new HashMap<>();

        world = GameData.loadGameDataFromResources("/gameData.json");
        this.player = world.player;
        this.player.setCurrentRoom(world.findRoom(player.getHomeLocationId()));

        commands.put("poloz", new DropCommand(player));
        commands.put("jdi", new GoCommand(player, world));
        commands.put("pomoc", new HelpCommand(player, world));
        commands.put("prozkoumat", new InspectCommand(player, world));
        commands.put("inventar", new InventoryCommand(player));
        commands.put("ukoncit", new QuitCommand(this));
        commands.put("seber", new TakeCommand(player, world));
        commands.put("mluv", new TalkCommand(player, world));
        commands.put("pouzij", new UseCommand(player, world));
    }

    /**
     * Provides comprehensive information about the player's current room upon starting the game.
     * It lists the room's description, items, NPCs, and available exits.
     * * @return A formatted string detailing the current room's state, matching the output of GoCommand.
     * @author Jakub Kubíček
     */
    public String homeInfo(){
        String result ="";

        result = "Jsi v místnosti: " + player.getCurrentRoom().getName() + "\n" + player.getCurrentRoom().getDescription() + "\nPředměty v této místnosti: ";
        for (String itemsID : player.getCurrentRoom().getItems()){
            Item item = world.findItem(itemsID);
            result += item.getName() + ", ";
        }
        result = result.substring(0, result.length()-2);
        result += "\nPostavy v této místnosti: ";
        if (player.getCurrentRoom().getNpcs().isEmpty()){
            result += "Žádné";
        }else{
            for (String npcID : player.getCurrentRoom().getNpcs()){
                NPC npc = world.findNPC(npcID);
                result += npc.getName() + ", ";
            }
            result = result.substring(0, result.length()-2);
        }
        result += "\nSousední místnosti: ";
        for (String neighborsID : player.getCurrentRoom().getNeighbors()){
            Room r = world.findRoom(neighborsID);
            result += r.getName() + ", ";
        }
        result = result.substring(0, result.length()-2);

        return result;
    }

    /**
     * Starts the main game loop. It displays the prologue, initializes the starting room info,
     * and continuously processes player input until the game ends.
     */
    public void start() {
        inicialization();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/prologue.txt"))))){
            System.out.println();
            String text = "";
            while((text = reader.readLine()) != null){
                System.out.println(text);
            }
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println(homeInfo());

        while(!gameOver){
            System.out.print("\n>>> ");
            String input = scanner.nextLine();

            if (input.isEmpty()){
                System.out.println("Nic si nenapsal.");
            }

            String[] parts = input.split(" ");
            String commandName = parts[0];
            String targetingName = "";
            if (parts.length>1){
                targetingName = parts[1];
            }

            if(commands.containsKey(commandName)){
                Command command = commands.get(commandName);
                String result = commands.get(commandName).execute(targetingName);
                System.out.println(result);
                if(command.exit()){
                    gameOver = true;
                }
            } else{
                System.out.println("Neznamý příkaz.");
            }
        }
        System.out.println("\nDěkuji za zahraní mé hry <3.");
    }
}

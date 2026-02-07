package Logic;

import Commands.*;
import Models.Characters.NPC;
import Models.Characters.Player;
import Models.Item;
import Models.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        commands.put("seber", new TakeCommand(player, world));
        commands.put("mluv", new TalkCommand(player, world));
        commands.put("pouzij", new UseCommand(player, world));
    }

    public String homeInfo(){
        String result ="";

        result = "Jsi v mistnosti: " + player.getCurrentRoom().getName() + "\n" + player.getCurrentRoom().getDescription() + "\nPredmety v teto mistnosti: ";
        for (String itemsID : player.getCurrentRoom().getItems()){
            Item item = world.findItem(itemsID);
            result += item.getName() + ", ";
        }
        result = result.substring(0, result.length()-2);
        result += "\nPostavy v teto mistnosti: ";
        if (player.getCurrentRoom().getNpcs().isEmpty()){
            result += "Zadne";
        }else{
            for (String npcID : player.getCurrentRoom().getNpcs()){
                NPC npc = world.findNPC(npcID);
                result += npc.getName() + ", ";
            }
            result = result.substring(0, result.length()-2);
        }
        result += "\nSousedni mistnosti: ";
        for (String neighborsID : player.getCurrentRoom().getNeighbors()){
            Room r = world.findRoom(neighborsID);
            result += r.getName() + ", ";
        }
        result = result.substring(0, result.length()-2);

        return result;
    }

    public void start() {
        inicialization();
        try(BufferedReader reader = new BufferedReader(new FileReader("res/prologue.txt"))){
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
                System.out.println("nic si nenapsal.");
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
                System.out.println("Neznamy prikaz.");
            }
        }
        System.out.println("\nDekuji za zahrani me hry <3.");
    }

}

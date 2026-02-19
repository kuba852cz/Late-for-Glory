package Logic;

import Models.Characters.NPC;
import Models.Characters.Player;
import Models.Item;
import Models.Room;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Represents the game data loaded from a JSON file.
 * This class serves as a data container for all static game content, such as items, characters, locations, and quests.
 *
 */
public class GameData {

    public ArrayList<Item> items;
    public ArrayList<NPC> npcs;
    public ArrayList<Room> rooms;
    public Player player;

    /**
     * Loads game data from a JSON file.
     * @param resourcePath path to the resource file
     * @return a game.GameData object filled with the loaded data
     */
    public static GameData loadGameDataFromResources(String resourcePath) {
        Gson gson = new Gson();

        try (InputStream is = GameData.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Nenalezen resource: " + resourcePath +
                        " (zkontrolujte, že soubor je v src/main/resources).");
            }


            return gson.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    GameData.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }

    }

    /**
     * Finds a specific location by its identifier.
     * @param id the identifier of the location to be found
     * @return the matching location
     */
    public Room findRoom(String id) {
        for (Room r : rooms) {
            if (r.getId().equals(id)){
                return r;
            }
        }
        throw new IllegalArgumentException("Neexistuje lokace s id: " + id);
    }

    /**
     * Finds a specific item by its identifier.
     * @param id the identifier of the location to be found
     * @return the matching location
     */
    public Item findItem(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)){
                return i;
            }
        }
        throw new IllegalArgumentException("Neexistuje předmět s id: " + id);
    }

    /**
     * Finds a specific npc by its identifier.
     * @param id the identifier of the location to be found
     * @return the matching location
     */
    public NPC findNPC(String id) {
        for (NPC npc : npcs) {
            if (npc.getId().equals(id)){
                return npc;
            }
        }
        throw new IllegalArgumentException("Neexistuje NPC s id: " + id);
    }

}

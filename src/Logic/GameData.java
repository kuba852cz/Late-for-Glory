package Logic;

import Models.Characters.Character;
import Models.Item;
import Models.Room;
import com.google.gson.Gson;
import com.sun.tools.javac.Main;

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
    public ArrayList<CharacterDTO> characters;
    public ArrayList<Room> rooms;

    //napomocna trida, diky ktere muzem inicializovat zdedene postavy
    public static class CharacterDTO {
        public String id;
        public String name;
        public String role;
        public String homeLocationId;

        public String getId() {
            return id;
        }
    }
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

    public Item findItem(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)){
                return i;
            }
        }
        throw new IllegalArgumentException("Neexistuje predmet s id: " + id);
    }

    public CharacterDTO findCharacter(String id) {
        for (CharacterDTO c : characters) {
            if (c.getId().equals(id)){
                return c;
            }
        }
        throw new IllegalArgumentException("Neexistuje postava s id: " + id);
    }


}

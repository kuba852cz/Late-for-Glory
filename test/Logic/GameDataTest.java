package Logic;

import Models.Characters.NPC;
import Models.Characters.Player;
import Models.Item;
import Models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying the loading and retrieval of game data from JSON.
 * @author Jakub Kubíček
 */
class GameDataTest {
    GameData gameData;

    /**
     * Loads the complete game data from the JSON file before each test.
     */
    @BeforeEach
    void setUp() {
        gameData = GameData.loadGameDataFromResources("/gameData.json");
    }

    /**
     * Tests if an existing item is successfully found in the loaded data.
     */
    @Test
    void findItem() {
        Item foundItem = gameData.findItem("item_gloves");

        assertNotNull(foundItem);
        assertEquals("Rukavice", foundItem.getName());
    }

    /**
     * Tests if an existing NPC is successfully found in the loaded data.
     */
    @Test
    void findNPC() {
        NPC foundNPC = gameData.findNPC("NPC_trainer");

        assertNotNull(foundNPC);
        assertEquals("Trener", foundNPC.getName());
    }

    /**
     * Tests if an existing room is successfully found in the loaded data.
     */
    @Test
    void findRoom() {
        Room foundRoom = gameData.findRoom("room_ring");

        assertNotNull(foundRoom);
        assertEquals("Ring", foundRoom.getName());
    }

    /**
     * Tests if searching for a non-existent item throws the correct exception.
     */
    @Test
    void findItemNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            gameData.findItem("item_kebab");
        });
    }
}
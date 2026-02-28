package Models.Characters;

import Logic.GameData;
import Models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying player logic and inventory management.
 * @author Jakub Kubíček
 */

class PlayerTest {
    Player player;
    GameData gameData;

    @BeforeEach
    void setUp() {
        gameData = GameData.loadGameDataFromResources("/gameData.json");
        player = new Player();
    }

    /**
     * Tests whether the stillNeed method correctly identifies
     * missing items based on the player's current inventory and equipment.
     */
    @Test
    void stillNeed() {
        ArrayList<String> neededItems = new ArrayList<>();
        Item gloves = gameData.findItem("item_gloves");
        Item guard = gameData.findItem("item_guard");
        player.getInventory().add(gloves);
        player.getInventory().add(guard);

        ArrayList<String> missingItems = player.stillNeed(gameData);

        assertEquals(1, missingItems.size());
        assertEquals(2, player.getInventory().size());

    }

    /**
     * Tests the successful addition of an item to the player's inventory.
     */
    @Test
    void pickUpItem() {
        Item gloves = gameData.findItem("item_gloves");
        player.getInventory().add(gloves);

        assertEquals(1, player.getInventory().size());
        assertTrue(player.getInventory().contains(gloves));
    }

    /**
     * Tests the successful removal of an item from the player's inventory.
     */
    @Test
    void dropItem() {
        Item gloves = gameData.findItem("item_gloves");
        Item guard = gameData.findItem("item_guard");
        player.getInventory().add(gloves);
        player.getInventory().add(guard);

        player.getInventory().remove(gloves);

        assertEquals(1, player.getInventory().size());
        assertFalse(player.getInventory().contains(gloves));
    }
}
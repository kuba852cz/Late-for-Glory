# 🏆 Late for Glory  

*Note: The game environment, story, and commands are entirely in Czech.*

## ✍️ Author
I'm Jakub Kubíček and I created this game to not only pass the school, but to entertain some people who would give it a chance. 
https://github.com/kuba852cz

## 📖 About the Game
A short text adventure programmed in Java. The player takes on the role of boxer Jon Oliveira on the day of his life-changing match against Ryan Garcia. The goal is to get ready in time, gather the necessary equipment, sign a contract, and enter the ring with the right tactics.

## 🎮 Controls (Commands)
The game is controlled by typing text commands into the console (in Czech). Available commands:
* **jdi <místnost>** (go) - Move to an adjacent room (e.g., `jdi Namesti`).
* **seber <předmět>** (take) - Pick up an item from the room (e.g., `seber Rukavice`).
* **poloz <předmět>** (drop) - Drop an item from the inventory.
* **mluv <postava>** (talk) - Start a conversation with an NPC (e.g., `mluv Trener`).
* **pouzij <předmět>** (use) - Use an item (e.g., `pouzij Protein`).
* **prozkoumat** (inspect) - Display details about the current room.
* **inventar** (inventory) - Show what Jon is currently carrying.
* **pomoc** (help) - Display game help.
* **ukoncit** (quit) - Immediately quit the game.

## ⚙️ Game Mechanics
* **Item Collection and Usage:** Some items must be worn, others consumed or read.
* **Character Status:** The game tracks your fitness and equipment (gloves, mouth guard). Without them, the trainer won't let you into the ring.
* **Dialogue System:** Conversations with NPCs advance the story and unlock key items.
* **Multiple Endings:** Your preparation and decisions in the ring affect the final outcome of the match.

## ⬇️ Installation
1. Make sure you have **Java** installed on your computer (Java 17 or newer is recommended).
2. Download the standalone `Late-for-Glory.jar` file from the repository. (Everything is packed inside, no extra data folders are needed!)

## 🚀 How to Run
1. Open a terminal (command prompt) in the folder where you downloaded the JAR file.
2. Run the game using the command:
   ```bash
   java -jar Late-for-Glory.jar



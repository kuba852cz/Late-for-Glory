package Commands;

import Logic.Game;
import Logic.GameData;
import Models.Characters.NPC;
import Models.Characters.Player;
import Models.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TalkCommand implements  Command {

    private Player player;
    private GameData gameData;
    private boolean gameOver = false;

    public TalkCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    /**
     * Initiates a conversation with an NPC. Every character has its own dialogue, which is handled by the methods below.
     * If the player has spoken to the character earlier, the character will not repeat the conversation.
     *
     * @param targetingName the name of the character the player wants to speak to
     * @return The dialogue text of the selected character, or an error message if the character is not found.
     */
    @Override
    public String execute(String targetingName) {
        NPC targetNpc = null;

        if (targetingName.isEmpty()) {
            return "S kým chceš mluvit?";
        }

        for (String speakingNPCID : player.getCurrentRoom().getNpcs()) {
            NPC npc = gameData.findNPC(speakingNPCID);
            if (npc.getName().equalsIgnoreCase(targetingName)) {
                targetNpc = npc;
                break;
            }
        }

        if (targetNpc == null) {
            return "Postava '" + targetingName + "' se zde nenachází.";
        }

        if (targetNpc.isQuestFinished()) {
            return targetNpc.getName() + ": \"Už jsme všechno pořešili. Soustřeď se na zápas.\"";
        }

        System.out.println("\n" + targetNpc.getName() + ": \"" + targetNpc.getStarterDialogue() + "\"");

        switch (targetNpc.getId()) {
            case "NPC_trainer":
                return dialogueTrainer(targetNpc);
            case "NPC_opponent":
                try(BufferedReader reader = new BufferedReader(new FileReader("res/preEnding.txt"))){
                    System.out.println();
                    String text = "";
                    while((text = reader.readLine()) != null){
                        System.out.println(text);
                    }
                    System.out.println();
                } catch (IOException e) {
                    throw new RuntimeException(e);

               }

                return dialogueRyan(targetNpc);
            case "NPC_unknown":
                return dialogueTyson(targetNpc);
            case "NPC_manager":
                return dialogueManager(targetNpc);
            default:
                return "Tato postava ti nemá co dalšího říct.";
        }
    }

    /**
     * Initiates a dialogue with Mike Tyson. Through this conversation, the player can obtain an important item for winning the match.
     *
     * @param Tyson the NPC object representing Mike Tyson
     * @return The outcome of the dialogue, including whether the player received the item.
     */
    public String dialogueTyson(NPC Tyson){
        Scanner volba = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("MOŽNOSTI:");
        System.out.println("1) 'Nazdar Miku! Mohl bych s tebou fotku?'");
        System.out.println("2) (Odejít)");
        System.out.println(">>>");

        switch (volba.nextLine()) {
            case "1":
                System.out.println("Jasne neni problem. Hodne stesti do zapasu proti Garciovi! Nepodcen ho.");
                Item item = gameData.findItem("item_photo");
                player.pickUpItem(item);
                Tyson.setQuestFinished(true);
                return "Mike ti dal svou podepsanou fotku.";
            case "2":
                return "Hodne stesti do zapasu mladiku!";
            default:
                return "takova volba neexistuje.";
        }
    }
    /**
     * Handles the final boss fight with Ryan Garcia.
     * Based on the player's choices and knowledge (Tyson's advice),
     * it triggers either the good or bad ending by reading from external files.
     *
     * @param Ryan the NPC object representing the opponent Ryan Garcia
     * @return A "Game Over" message after the fight sequence is completed.
     */
    public String dialogueRyan(NPC Ryan){
        Scanner volba = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("MOŽNOSTI:");
        if (player.isKnowsWinningMove()) {
            System.out.println("1) Pouzit radu od Mike Tysona.");
        }else{
            System.out.println("1) ???");
        }
        System.out.println("2) Pouzit klasicky uder");
        System.out.print(">>> ");
        switch (volba.nextLine()) {
            case "1":
                if(player.isKnowsWinningMove()){
                   try(BufferedReader reader = new BufferedReader(new FileReader("res/goodEnding.txt"))){
                       System.out.println();
                        String text = "";
                        while((text = reader.readLine()) != null){
                            System.out.println(text);
                        }
                       System.out.println();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    gameOver = true;
                   break;
                }else{
                    System.out.println("Nevis co mas delat.");
                    return dialogueRyan(Ryan);
                }
            case "2":
                try(BufferedReader reader = new BufferedReader(new FileReader("res/badEnding.txt"))){
                    System.out.println();
                    String text = "";
                    while((text = reader.readLine()) != null){
                        System.out.println(text);
                    }
                    System.out.println();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            default:
                System.out.println("Takova volba neexistuje.");
                return dialogueRyan(Ryan);
        }
        return "Game Over";
    }
    /**
     * Handles the interaction with the Manager.
     * If the player possesses an unsigned contract, the Manager signs it,
     * which is a crucial requirement to enter the ring.
     *
     * @param Manager the NPC object representing the Manager
     * @return A confirmation message of the signing or a reminder of the missing contract.
     */
    public String dialogueManager(NPC Manager){
        Scanner volba = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("MOŽNOSTI:");
        System.out.println("1) 'Mohl byste mi podepsat tu smlouvu?'");
        System.out.println("2) (Odejít)");
        System.out.print(">>> ");

        switch (volba.nextLine()) {
            case "1":
                Item contract = null;
                for (Item item : player.getInventory()){
                    if (item.getId().equals("item_contract")) {
                        contract = gameData.findItem("item_contract");
                    }
                }
                if (contract != null) {
                    player.getInventory().remove(contract);

                    System.out.println("Jasně není problém. Hodně štěstí do zápasu proti Garciovi! Nepodceň ho.");

                    Item signed = gameData.findItem("item_contract_signed");
                    if (signed != null) {
                        player.pickUpItem(signed);
                    }

                    Manager.setQuestFinished(true);
                    return "Manažer ti podal podepsanou smlouvu.";
                }
                return "Chlapce, prvni pro me musis mit tu smlouvu. Jinak ti nemam co podepsat.";
            case "2":
                return "tak proc sem vubec lezes, kdyz rovnou odchazis?";
            default:
                return "takova volba neexistuje.";
        }
    }

    /**
     * Manages the interaction with the Trainer.
     * He checks the player's fitness, equipment (gloves, guard), and signed contract.
     * Only when all conditions are met and equipment is worn, the quest is marked as finished.
     *
     * @param Trainer the NPC object representing the Trainer
     * @return Feedback on what items or conditions the player is still missing.
     */
    public String dialogueTrainer(NPC Trainer){
        boolean hasGloves = false;
        boolean hasGuard = false;
        boolean hasContract = false;

        for (Item item : player.getInventory()) {
            if (item.getId().equals("item_gloves")) {
                hasGloves = true;
            }
            if (item.getId().equals("item_guard")) {
                hasGuard = true;
            }
            if (item.getId().equals("item_contract_signed")) {
                hasContract = true;
            }
        }

        if (hasGloves && hasGuard && player.isFit() && hasContract) {
            return "\nTrenér: \"Výborně Jone! Máš všechno. Vydíš, že to šlo.\n" +
                    "Trenér: \"Jen si to prosimte jeste nasad na sebe\"";
        }
        else if (hasGloves && hasGuard && !player.isFit() && hasContract) {
            return "\nTrenér: \"Věci sice máš, ale podívej se na sebe! Jsi jako párátko.\n" +
                    "Mazej do tělocvičny máknout na strojích a dej si protein!\n" +
                    "V tomhle stavu by tě Ryan zabil.\"";
        }
        else if (player.isHasGlovesOn() && player.isHasGuardOn() && player.isFit()&& hasContract) {
            Trainer.setQuestFinished(true);
            return "\nTrenér: \"Parada! Jsem rad ze si to stihl vcas.\n"+
                    "Trenér: \"Jsi připraven? Běž do Ringu a ukaž mu to!\"";
        }
        else{
            return "\nTrenér: \"Co tu děláš? Ještě nemáš všechno!\n" +
                    "Potřebuješ: Podepsanou smlouvu, Rukavice, Chránič na zuby a svou formu!\n" +
                    "Takhle tě do ringu nepustím!\"";
        }

    }

    @Override
    public boolean exit() {
        return gameOver;
    }
}

package Commands;

import Logic.Game;
import Logic.GameData;
import Models.Characters.NPC;
import Models.Characters.Player;
import Models.Item;

import java.util.Scanner;

public class TalkCommand implements  Command {

    private Player player;
    private GameData gameData;

    public TalkCommand(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    @Override
    public String execute(String targetingName) {
        NPC targetNpc = null;

        if (targetingName.isEmpty()) {
            return "S kým chceš mluvit?";
        }

        for (String speakingNPCID : player.getCurrentRoom().getNpcs()) {
            NPC npc = gameData.findNPC(speakingNPCID);
            if (npc.getName().equals(targetingName)) {
                targetNpc = npc;
                break;
            }
        }

        if (targetNpc.isQuestFinished()) {
            return targetNpc.getName() + ": \"Už jsme všechno pořešili. Soustřeď se na zápas.\"";
        }

        System.out.println("\n" + targetNpc.getName() + ": \"" + targetNpc.getStarterDialogue() + "\"");

        switch (targetNpc.getId()) {
            case "NPC_trainer":
                break;
            case "NPC_opponent":
                break;
            case "NPC_unknown":
                break;
            case "NPC_manager":
                break;
            default:
                break;
        }
        return  "Postava "+ targetingName + " se zde nenachazi.";
    }

    public String dialogueTyson(NPC Tyson){
        Scanner volba = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("MOŽNOSTI:");
        System.out.println("1) 'Nazdar Miku! Mohl bych s tebou fotku?'");
        System.out.println("2) (Odejít)");
        System.out.println(">>>");

        switch (volba.nextInt()) {
            case 1:
                System.out.println("Jasne neni problem. Hodne stesti do zapasu proti Garciovi! Nepodcen ho.");
                Item item = gameData.findItem("item_photo");
                player.pickUpItem(item);
                Tyson.isQuestFinished();
                return "Mike ti dal svou podepsanou fotku.";
            case 2:
                return "Hodne stesti do zapasu mladiku!";
            default:
                return "takova volba neexistuje.";
        }
    }

    public String dialogueRyan(NPC Ryan){
        Scanner volba = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        if (player.getInventory().contains("")) {

        }
        switch (volba.nextInt()) {
            case 1:
                return "";
            case 2:
                return "";
            default:
                return "takova volba neexistuje.";
        }
    }

    public String dialogueManager(NPC Manager){
        Scanner volba = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("MOŽNOSTI:");
        System.out.println("1) 'Mohl byste mi podepsat tu smlouvu?'");
        System.out.println("2) (Odejít)");
        System.out.println(">>>");

        switch (volba.nextInt()) {
            case 1:
                System.out.println("Jasne neni problem. Hodne stesti do zapasu proti Garciovi! Nepodcen ho.");
                Item item = gameData.findItem("item_contract_signed");
                player.pickUpItem(item);
                Manager.isQuestFinished();
                return "Manazer ti podal podepsanou smlouvu.";
            case 2:
                return "tak proc sem vubec lezes, kdyz rovnou odchazis?";
            default:
                return "takova volba neexistuje.";
        }
    }

    public String dialogueTrainer(NPC Trainer){
        Scanner volba = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("MOŽNOSTI:");
        System.out.println("1) 'Mohl byste mi podepsat tu smlouvu?'");
        System.out.println("2) (Odejít)");
        System.out.println(">>>");

        switch (volba.nextInt()) {
            case 1:
                System.out.println("Jasne neni problem. Hodne stesti do zapasu proti Garciovi! Nepodcen ho.");
                Item item = gameData.findItem("item_contract_signed");
                player.pickUpItem(item);
                Trainer.isQuestFinished();
                return "Manazer ti podal podepsanou smlouvu.";
            case 2:
                return "tak proc sem vubec lezes, kdyz rovnou odchazis?";
            default:
                return "takova volba neexistuje.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}

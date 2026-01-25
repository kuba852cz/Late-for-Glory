package Logic;

public class Main {
    public static void main(String[] args) {

        System.out.println("Items: " + data.items.size());
        System.out.println("Characters: " + data.characters.size());
        System.out.println("Locations: " + data.locations.size());

        Game game = new Game();
        game.start();

    }
}
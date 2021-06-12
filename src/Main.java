import gamelogic.Game;

/**
 * Main.
 */
public class Main {
    /**
     * main method of the ass, create a game init it and run it.
     *
     * @param args none
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

import gamelogic.Game;

/**
 * Ass3Game arcenoid game without disappearing blocks.
 *
 * @author itamar shachenc tov
 * @since 2021-04-28
 * id: 207497769
 */
public class Ass6Game {
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


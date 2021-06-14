import LevelInformation.*;
import biuoop.KeyboardSensor;
import gamelogic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<LevelInformation> levels = Arrays.asList(
                new BlackLevel(), new WhiteLevel(), new GreenLevel(), new BlueLevel());
        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        Counter scoreCounter = new Counter();
        ScoreTrackingListener score = new ScoreTrackingListener(scoreCounter);
        GameFlow gameFlow = new GameFlow(ar, ks, scoreCounter, score);
        gameFlow.runLevels(levels);
//        game.initialize();
//        game.run();
    }
}


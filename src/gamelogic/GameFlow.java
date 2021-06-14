package gamelogic;

import LevelInformation.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter scoreCounter;
    private ScoreTrackingListener score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter scoreCounter, ScoreTrackingListener score) {
        this.ar = ar;
        this.ks = ks;
        this.scoreCounter = scoreCounter;
        this.score = score;
    }

    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.ks,
                    this.ar,
                    this.scoreCounter,
                    this.score
               );

            level.initialize();

            do {
                level.run();
            }
            while (!level.shouldStop());

            if (level.getBallsCounter() == 0) {
                break;
            }
        }
    }
}
package gamelogic;

import sprite.Ball;
import sprite.Block;

/**
 * ScoreTrackingListener.
 * keeping track of the score.
 * @author itamar shachenc tov
 * @since 2021-04-28
 * id: 207497769
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter the counter obj.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * updates the counter when a hit of block is accord.
     * @param beingHit the block we hit
     * @param hitter the ball which hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}
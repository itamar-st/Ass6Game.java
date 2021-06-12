package gamelogic;

import sprite.Ball;
import sprite.Block;

/**
 * HitListener.
 * listener for hitting events
 * @author itamar shachenc tov
 * @since 2021-04-28
 * id: 207497769
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block we hit
     * @param hitter   the ball which hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
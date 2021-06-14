package gamelogic;

import sprite.Ball;
import sprite.Block;

/**
 * listener that removes a the ball if he collides with a a block.
 * @author itamar shachenc tov
 * @since 2021-04-28
 * id: 207497769
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game           the game obj we want to del from
     * @param remainingBalls the number of balls remaining
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * remove the ball from the game if the class detects a hit.
     *
     * @param beingHit the block we hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}

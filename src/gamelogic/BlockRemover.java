package gamelogic;

import sprite.Ball;
import sprite.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author itamar shachenc tov
 * @since 2021-04-28
 * id: 207497769
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game          the game we want to remove from.
     * @param removedBlocks the counter of the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit the block we hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (remainingBlocks.getValue() > 0) {
            remainingBlocks.decrease(1);
            //remove the listener from the block
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
        }
    }
}
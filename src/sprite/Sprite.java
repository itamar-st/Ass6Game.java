package sprite;

import gamelogic.GameLevel;
import biuoop.DrawSurface;

/**
 * spirit interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the drawing surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the spirit to the game.
     *
     * @param game the game
     */
    void addToGame(GameLevel game);

    /**
     * remove the spirit to the game.
     *
     * @param game the game
     */
    void removeFromGame(GameLevel game);
}
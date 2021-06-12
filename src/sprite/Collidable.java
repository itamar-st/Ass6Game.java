package sprite;

import geometry.Point;
import geometry.Rectangle;

/**
 * Collidable.
 *
 * @author itamar shachenc tov
 * @since 2021-04-28
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the collsion rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * it returns the new velocity expected after the hit
     *
     * @param collisionPoint  .
     * @param currentVelocity .
     * @param hitter          the object that hitted the collidable
     * @return the a new velocity after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
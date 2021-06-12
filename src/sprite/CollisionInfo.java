package sprite;

import geometry.Line;
import geometry.Point;

/**
 * CollisionInfo when collision occurs.
 *
 * @author itamar shachenc tov
 * @since 2021-04-28
 */
public class CollisionInfo {
    private Point pointOfCollision;
    private Collidable objectOfCollision;
    private Line lineOfCollision;

    /**
     * constructor.
     *
     * @param pointOfCollision  .
     * @param objectOfCollision .
     */
    public CollisionInfo(Point pointOfCollision, Collidable objectOfCollision) {
        this.pointOfCollision = pointOfCollision;
        this.objectOfCollision = objectOfCollision;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return pointOfCollision;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return objectOfCollision;
    }

    /**
     * ret the collision point.
     *
     * @param p point of collision
     */
    public void setCollitionPoint(Point p) {
        pointOfCollision = p;
    }

    /**
     * set the collision object.
     *
     * @param c the collision object
     */
    public void setCollitionObject(Collidable c) {
        objectOfCollision = c;
    }

    /**
     * set the collision line.
     *
     * @param l collision line
     */
    public void setCollisionLine(Line l) {
        lineOfCollision = l;
    }
}
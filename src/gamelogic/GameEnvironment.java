package gamelogic;

import geometry.Line;
import geometry.Point;
import sprite.Collidable;
import sprite.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itamar shachenc tov
 * @since 2021-04-28
 * GameEnvironment holds all of the collidable objects.
 */
public class GameEnvironment {
    private List<Collidable> collidableObjects = new ArrayList<Collidable>();

    /**
     * constructor.
     */
    public GameEnvironment() {
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        collidableObjects.add(c);
    }

    /**
     * remove the given collidable to the environment.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        collidableObjects.remove(c);
    }


    /**
     * get the losest collision with with a line.
     *
     * @param trajectory of the object
     * @return collision object with the info of the collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidableObjectsCopy = new ArrayList<>(collidableObjects);
        CollisionInfo information = null;
        //iterate through the collidable objects and check if they intersect with the line
        for (Collidable colid : collidableObjectsCopy) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(colid.getCollisionRectangle());
            //update the info if not null
            if (collisionPoint != null) {
                information = new CollisionInfo(collisionPoint, colid);
            }
        }
        return information;
    }

    /**
     * getter.
     *
     * @return the game environment
     */
    public List<Collidable> getenv() {
        return collidableObjects;
    }
}
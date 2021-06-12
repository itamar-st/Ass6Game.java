package sprite;

import biuoop.DrawSurface;
import gamelogic.Game;
import gamelogic.HitListener;
import gamelogic.HitNotifier;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block, constructed from a rectangle with color.
 */

public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;
    private Rectangle skelaton;
    private CollisionInfo info;

    /**
     * constructor.
     *
     * @param upperLeft point of the block.
     * @param width     of the block
     * @param height    of the block
     * @param color     of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        hitListeners = new ArrayList<>();
        skelaton = new Rectangle(upperLeft, width, height);
    }

    /**
     * get the rectangle the block is made of. for collision detection
     *
     * @return the rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return skelaton;
    }

    /**
     * notify the listeners a hit accord.
     *
     * @param hitter the ball that caused the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * check if the ball hits the block. if so, change the velocity according to the border we collided with
     *
     * @param collisionPoint  of the object with the block
     * @param currentVelocity of the object before colliding
     * @return the new velocity after collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        // notify the listeners a hit has accrued
        this.notifyHit(hitter);
        // fill the collision information of the block
        info = new CollisionInfo(collisionPoint, this);
        Velocity newVelocity = currentVelocity;
        //turn the point into a Line and search for intersection with the borders
        Line tmpLine = new Line(collisionPoint, collisionPoint);
        // if collding with top or bottom change horizontal velocity, if with sides change vertical velocity
        if (skelaton.getUpperBound().isIntersecting(tmpLine) || skelaton.getLowerBound().isIntersecting(tmpLine)) {
            newVelocity = new Velocity(currentVelocity.getVelocityX(), (-1) * currentVelocity.getVelocityY());
        }
        if (skelaton.getLeftBound().isIntersecting(tmpLine) || skelaton.getRightBound().isIntersecting(tmpLine)) {
            newVelocity = new Velocity((-1) * currentVelocity.getVelocityX(), currentVelocity.getVelocityY());
        }
        return newVelocity;
    }

    /**
     * accessors.
     *
     * @return the color of the point
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * getter fo the upper left point.
     *
     * @return upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * getter for the width of the block.
     *
     * @return the width of the block
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter for the height of the block.
     *
     * @return the height of the block
     */
    public double getHeight() {
        return height;
    }

    /**
     * draw the block with a a rectangle as border.
     *
     * @param surface in which we want to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
        //draw rectangle for the border
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }

    /**
     * notify the block that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * add the block to the game.
     *
     * @param game we added all the info
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    @Override
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}

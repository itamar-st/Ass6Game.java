package sprite;

import biuoop.DrawSurface;
import gamelogic.GameLevel;
import gamelogic.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;

/**
 * Ball.
 *
 * @author itamar shachenc tov
 * @since 2021-04-28
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private Point point;
    private GameEnvironment g;
//Todo: change the g name to g

    /**
     * constructor.
     *
     * @param center of the ball
     * @param r      radius
     * @param color  of the ball
     * @param g      which has all the game info
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.g = g;
    }

    /**
     * another constructor.
     *
     * @param x     coordinate of the center of the ball
     * @param y     coordinate of the center of the ball
     * @param r     radius of the ball
     * @param color of the ball
     * @param g     which has all the game info
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment g) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.g = g;
    }

    /**
     * another constructor.
     *
     * @param x     coordinate of the center of the ball in double
     * @param y     coordinate of the center of the ball in double
     * @param r     radius of the ball
     * @param color of the ball
     * @param g     which has all the game info
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment g) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.g = g;

    }

    /**
     * accessors.
     *
     * @return the x coordinate of the center point
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * accessors.
     *
     * @return the y coordinate of the center point
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * accessors.
     *
     * @return the radius of the point
     */
    public int getSize() {
        return this.r;
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
     * @param surface in which we want to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * set the desired velocity.
     *
     * @param v the velocity desired
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the desired velocity in terms if steps in each coordinate.
     *
     * @param dx move dx pixels in the x coordinate
     * @param dy move dy pixels in the y coordinate
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * accessor for velocity.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * move the ball one step, if intersection occurs,
     * set the location to the location of the collision and change the velocity accordingly.
     */
    public void moveOneStep() {
        //create a trajectory line with the potential location of the ball after the step
        Point endOfPath = this.velocity.applyToPoint(this.center);
        //enlarge the trajectory for avoiding inaccuracies
        Point longerEndOfPath = this.velocity.applyToPoint(endOfPath);
        Line trajectory = new Line(center, longerEndOfPath);
        //check if collision occurs.
        CollisionInfo info = this.g.getClosestCollision(trajectory);
        if (info != null) {
            Point colidPoint = info.collisionPoint();
            Line colidLine = new Line(colidPoint, colidPoint);
            Rectangle colidRectangle = info.collisionObject().getCollisionRectangle();
            //chekc if a collision occurs in each bound of the rectangle
            if (colidRectangle.getUpperBound().isIntersecting(colidLine)) {
                this.center = new Point(colidPoint.getX(), colidPoint.getY() - r);
            } else if (colidRectangle.getLowerBound().isIntersecting(colidLine)) {
                this.center = new Point(colidPoint.getX(), colidPoint.getY() + r);
            } else if (colidRectangle.getLeftBound().isIntersecting(colidLine)) {
                this.center = new Point(colidPoint.getX() - r, colidPoint.getY());
            } else if (colidRectangle.getRightBound().isIntersecting(colidLine)) {
                this.center = new Point(colidPoint.getX() + r, colidPoint.getY());
            }
            //set the location accordingly
            setVelocity(info.collisionObject().hit(info.collisionPoint(), velocity, this));
        } else {
            this.center = endOfPath;
        }
    }

    /**
     * add the block to the game.
     *
     * @param game we added all the info to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}
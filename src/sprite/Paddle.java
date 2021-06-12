package sprite;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import gamelogic.Game;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author itamar shachenc tov
 * @since 2021-04-28
 * Paddle operates as the player.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;
    private Rectangle skelaton;
    private CollisionInfo info;
    private int leftBorder;
    private int rightBorder;

    /**
     * constructor.
     *
     * @param upperLeft   of the paddle
     * @param width       of the paddle
     * @param height      of the paddle
     * @param color       of the paddle
     * @param keyboard    for controlling the paddle
     * @param leftBorder  for bounding the paddle
     * @param rightBorder for bounding the paddle
     */
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color,
                  biuoop.KeyboardSensor keyboard, int leftBorder, int rightBorder) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.keyboard = keyboard;
        this.skelaton = new Rectangle(upperLeft, width, height);
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    /**
     * move the paddle left.
     */
    public void moveLeft() {
        this.upperLeft = new Point(this.upperLeft.getX() - 3, this.upperLeft.getY());
        this.skelaton = new Rectangle(upperLeft, width, height);
    }

    /**
     * move the paddle to the right.
     */
    public void moveRight() {
        this.upperLeft = new Point(this.upperLeft.getX() + 3, this.upperLeft.getY());
        this.skelaton = new Rectangle(upperLeft, width, height);
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
     * notify the paddle time has passed and check a key was pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && upperLeft.getX() > leftBorder) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && upperLeft.getX() + width < rightBorder) {
            moveRight();
        }
    }

    /**
     * draw the paddle.
     *
     * @param d the drawing surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }


    /**
     * get the paddle rectangle.
     *
     * @return the skeleton
     */
    public Rectangle getCollisionRectangle() {
        this.skelaton = new Rectangle(upperLeft, width, height);
        return skelaton;
    }

    /**
     * check if the ball hits the paddle. if so, change the velocity according to the border we collided with
     *
     * @param collisionPoint  with the ball
     * @param currentVelocity of the ball
     * @param hitter          the object that hit the ball
     * @return new velocity based on the collision location
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        //divide the paddle to 5 sections
        double section = width / 5;
        info = new CollisionInfo(collisionPoint, this);
        //create a line from the collision point
        Line tmpLine = new Line(collisionPoint, collisionPoint);
        Velocity newVelocity = currentVelocity;
        double startOfPaddle = upperLeft.getX();
        double colidX = collisionPoint.getX();
        //check if the ball hit the top of the paddle. if so, in which section
        if (skelaton.getUpperBound().isIntersecting(tmpLine)) {
            if (colidX > startOfPaddle && colidX < startOfPaddle + section) {
                newVelocity = Velocity.fromAngleAndSpeed(210, currentVelocity.getSpeed());
            } else if (colidX > startOfPaddle + section && colidX < startOfPaddle + (2 * section)) {
                newVelocity = Velocity.fromAngleAndSpeed(240, currentVelocity.getSpeed());
            } else if (colidX > startOfPaddle + 2 * section && colidX < startOfPaddle + 3 * section) {
                newVelocity = Velocity.fromAngleAndSpeed(270, currentVelocity.getSpeed());
            } else if (colidX > startOfPaddle + 3 * section && colidX < startOfPaddle + 4 * section) {
                newVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            } else {
                newVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            }
            //check if it hits the sides
        }
        if (skelaton.getLeftBound().isIntersecting(tmpLine)
                || skelaton.getRightBound().isIntersecting(tmpLine)) {
            newVelocity = new Velocity((-1) * currentVelocity.getVelocityX(), currentVelocity.getVelocityY());
        }
        return newVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game we are paying
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void removeFromGame(Game game) {
    }
}
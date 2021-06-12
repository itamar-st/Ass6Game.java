package sprite;

import geometry.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author itamar shachenc tov
 * @since 2021-04-28
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx move x pixels in the x coordinate
     * @param dy move y pixels in the y coordinate
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * accessor.
     *
     * @return dx
     */
    public double getVelocityX() {
        return this.dx;
    }

    /**
     * accessor.
     *
     * @return dy
     */
    public double getVelocityY() {
        return this.dy;
    }

    /**
     * change the velocity from angel and speed to dx and dy.
     *
     * @param angle of the motion
     * @param speed of the motion
     * @return the velocity in terms of dx,dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleInRad = Math.toRadians(angle);
        double dx = speed * Math.cos(angleInRad);
        double dy = speed * Math.sin(angleInRad);
        return new Velocity(dx, dy);
    }

    /**
     * @return the velocity value in terms of speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p the point we want to apply the movement
     * @return new point with the coordinates shifted dx,dy pixels
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
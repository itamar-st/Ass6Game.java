package geometry;

/**
 * point.
 *
 * @author itamar shachenc tov
 * @since 2021-04-28
 */

public class Point {
    private double x;
    private double y;

    /**
     * constructors.
     *
     * @param x coordinate of the point
     * @param y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * check the disctance between two points.
     *
     * @param other point to compare
     * @return the distance
     */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * @param other point to compare
     * @return true if the same point, false otherwise
     */
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return (x == other.x) && (y == other.y);
    }

    /**
     * @return the x coordinate
     */
    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * transform the return statement of the point to be more convenient.
     *
     * @return formatted string
     */
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }

}
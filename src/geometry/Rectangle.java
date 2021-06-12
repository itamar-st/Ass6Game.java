package geometry;

import java.util.ArrayList;
import java.util.List;


/**
 * @author itamar shachenc tov
 * @since 2021-04-28
 * Rectangle shape
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;
    private Line upperBound;
    private Line lowerBound;
    private Line leftBound;
    private Line rightBound;

    /**
     * constructor.
     *
     * @param upperLeft corner of the rectangle
     * @param width     of the rectangle
     * @param height    of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        //set the bounds with the width and height
        upperBound = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        lowerBound = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        leftBound = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        rightBound = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * get all the intersection points of the rectangle with a specified line.
     *
     * @param line we intersect with
     * @return a (possibly empty) List of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //get all the borders of the rectangle
        Line[] borders = getBorders();
        List<Point> intersections = new ArrayList<>();
        //iterate through all the borders and check if the line intersect with them
        for (Line border : borders) {
            if (border.isIntersecting(line)) {
                //returns the intersection if exists, else null
                Point addIntersection = border.intersectionWith(line);
                if (addIntersection != null) {
                    intersections.add(addIntersection);
                }
            }
        }
        return intersections;
    }

    /**
     * getter.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getter.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getter.
     *
     * @return the width and height of the rectangle.
     */
    public Line getUpperBound() {
        return upperBound;
    }

    /**
     * getter.
     *
     * @return the lower bound of the rectangle.
     */
    public Line getLowerBound() {
        return lowerBound;
    }

    /**
     * getter.
     *
     * @return the left bound of the rectangle.
     */
    public Line getLeftBound() {
        return leftBound;
    }

    /**
     * getter.
     *
     * @return the right bound of the rectangle.
     */
    public Line getRightBound() {
        return rightBound;
    }

    /**
     * getter.
     *
     * @return all the borders of the rectangle.
     */
    public Line[] getBorders() {
        return new Line[]{upperBound, lowerBound, leftBound, rightBound};
    }

    /**
     * getter.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * transform the return statement of the point to be more convenient.
     *
     * @return formatted string
     */
    public String toString() {
        return "[" + this.upperLeft + ", width: " + this.width + ", height: " + this.height + "]";
    }
}


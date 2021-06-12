package geometry;

import java.util.List;

/**
 * Line.
 *
 * @author itamar shachen tov
 * id 207497769
 */

public class Line {
    private Point start;
    private Point end;

    /**
     * constructors.
     *
     * @param start starting point of the line
     * @param end   ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * another constructor.
     *
     * @param x1 coordinates of the two points
     * @param y1 coordinates of the two points
     * @param x2 coordinates of the two points
     * @param y2 coordinates of the two points
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    //
    public double length() {
        return start.distance(end);
    }

    /**
     * @return the middle point of the line
     */
    //
    public Point middle() {
        double startX = start.getX();
        double startY = start.getY();
        double endX = end.getX();
        double endY = end.getY();
        Point mid = new Point((startX + endX) / 2, (startY + endY) / 2);
        return mid;
    }

    /**
     * @return the start point of the line
     */
    //
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * side function that checks if the points are between each other.
     *
     * @param l1Start starting point of the first line
     * @param l2Start starting point of the second line
     * @param l1End   ending point of the first line
     * @param l2End   ending point of the second
     * @return the point between the lines or null
     */
    Point betweenPoints(Point l1Start, Point l1End, Point l2Start, Point l2End) {
        if (l1Start.getX() >= l2Start.getX() && l1Start.getX() <= l2End.getX()
                && l1Start.getY() >= l2Start.getY() && l1Start.getY() <= l2End.getY()) {
            return l1Start;
        }
        if (l2Start.getX() >= l1Start.getX() && l2Start.getX() <= l1End.getX()
                && l2Start.getY() >= l1Start.getY() && l2Start.getY() <= l1End.getY()) {
            return l2Start;
        }
        return null;
    }

    /**
     * checks if a line traces another line.
     *
     * @param l1Start starting point of the first line
     * @param l2Start starting point of the second line
     * @param l1End   ending point of the first line
     * @param l2End   ending point of the second
     * @return the intersection point if exists, else null
     */
    public Point checkRepeatedPoint(Point l1Start, Point l1End, Point l2Start, Point l2End) {
        if (l1Start.equals(l2End) || l1Start.equals(l2Start)) {
            return l1Start;
        }
        if (l1End.equals(l2Start) || l1End.equals(l2End)) {
            return l1End;
        }
        return null;
    }

    /**
     * algorithm for calculating intersection point between two lines
     * the lines are treated as vectors.<p>
     * we define  2-dimensional vector cross product v × w to be vx wy − vy wx <p>
     * the vector is defined by his starting point and the added distance to to the other point.
     * every point can be represented as starting point + t* added distance.
     * we want to find a factor for both vectors to represent the intersection point <p>
     * the calculation: p , q the starting points, s,r are the added distance, u,t are the factors
     * first line=  p + tr     second line = q + us <p>
     * p + tr = q + us --> (p + tr) × s = (q + us) × s -->
     * t (r × s) = (q − p) × s --> t = (q − p) × s / (r × s) <p>
     * similar development with the other factor --> u = (q − p) × r / (r × s)
     *
     * @param l1Start starting point of the first line
     * @param l2Start starting point of the second line
     * @param l1End   ending point of the first line
     * @param l2End   ending point of the second
     * @param flag    to know if are called by intersectsWith or is intersecting
     * @return the intersection point if exists, else null
     */
    public Point intersectionAlgo(Point l1Start, Point l1End, Point l2Start, Point l2End, boolean flag) {

        // delta X of the two line segments
        double deltaX = l2Start.getX() - l1Start.getX();
        double deltaY = l2Start.getY() - l1Start.getY();
        //the length added from the starting point of l1
        double sX = l1End.getX() - l1Start.getX();
        double sY = l1End.getY() - l1Start.getY();
        //the length added from the starting point of l2
        double rX = l2End.getX() - l2Start.getX();
        double rY = l2End.getY() - l2Start.getY();
        //vector multiplication
        double sXr = rX * sY - rY * sX;
        double deltaXs = -deltaX * sY + deltaY * sX;
        double deltaXr = -deltaX * rY + deltaY * rX;
        // meaning the lines have the same slope
        if (sXr == 0) {
            //corner case for point and a line overlapping
            if (l1Start.equals(l1End) || l2Start.equals(l2End)) {
                flag = true;
            }
            //check a corner case for overlapping lines
            if (flag) {
                // meaning the lines collinear
                if (deltaXs == 0 || deltaXr == 0) {
                    //check if the lines collinear and overlapping(returns the point)
                    // or collinear and disjoint (returns null)
                    return betweenPoints(l1Start, l1End, l2Start, l2End);
                }
            }
        } else {
            //get the factors
            double t = deltaXs / sXr;
            double u = deltaXr / sXr;

            //check if the are really a part of the line
            if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
                Point intersection = new Point(l1Start.getX() + (u * sX), l1Start.getY() + (u * sY));
                return intersection;
            }
        }
        return null;
    }

    /**
     * checks if the 2 lines intersect,
     * uses the same algo as intersectionWith but uses a flag for checking if lines overlap.
     *
     * @param other line to compare
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point intersectionPoint = intersectionAlgo(this.start, this.end, other.start(), other.end(), true);
        return intersectionPoint != null;
    }

    /**
     * checks where does the lines intersect.
     *
     * @param other line to check the intersection
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        return intersectionAlgo(this.start, this.end, other.start(), other.end(), false);
    }

    /**
     * checks where is the closest intersection point to a rectangle.
     *
     * @param rect line to check the intersection
     * @return the intersection point if the line intersects with the rectangle, null otherwise.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointArray = rect.intersectionPoints(this);
        //initial distance
        double distance = 100000;
        Point closestIntersectionPoint = null;
        //update the distance if its smaller and return the closest intersection point
        for (Point p : pointArray) {
            double distP1P2 = p.distance(start);
            if (distP1P2 < distance) {
                closestIntersectionPoint = p;
                distance = distP1P2;
            }
        }
        return closestIntersectionPoint;
    }

    /**
     * checks if the lines are equal.
     *
     * @param other line to compare
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (start.equals(other.start)) && (end.equals(other.end));
    }

}
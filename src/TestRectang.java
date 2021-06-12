import geometry.*;
import java.util.List;

public class TestRectang {
    public void TestRectange() {
        Point recUpperLeft1 = new Point(1, 4);
        Rectangle rec1 = new Rectangle(recUpperLeft1, 5, 1);
        Line line1 = new Line(1,1,6,3);
        java.util.List<Point> l1 = rec1.intersectionPoints(line1);
        Point closestinter1 = line1.closestIntersectionToStartOfLine(rec1);

        Point recUpperLeft2 = new Point(1, 4);
        Rectangle rec2 = new Rectangle(recUpperLeft2, 5, 1);
        Line line2 = new Line(1,1,5,3);
        java.util.List<Point> l2 = rec2.intersectionPoints(line2);
        Point closestinter2 = line2.closestIntersectionToStartOfLine(rec2);

        Point recUpperLeft3 = new Point(1, 4);
        Rectangle rec3 = new Rectangle(recUpperLeft3, 5, 1);
        Line line3 = new Line(1,1,3,8);
        java.util.List<Point> l3 = rec3.intersectionPoints(line3);
        Point closestinter3 = line3.closestIntersectionToStartOfLine(rec3);

        Point recUpperLeft4 = new Point(1, 4);
        Rectangle rec4 = new Rectangle(recUpperLeft4, 5, 1);
        Line line4 = new Line(1,1,2,2);
        java.util.List<Point> l4 = rec4.intersectionPoints(line4);
        Point closestinter4 = line4.closestIntersectionToStartOfLine(rec4);

        Point recUpperLeft5 = new Point(1, 4);
        Rectangle rec5 = new Rectangle(recUpperLeft5, 5, 1);
        Line line5 = new Line(2,3,2,3);
        java.util.List<Point> l5 = rec5.intersectionPoints(line4);
        Point closestinter5 = line5.closestIntersectionToStartOfLine(rec5);

        System.out.println("l1: " + l1 + "closest intersection: " + closestinter1);
        System.out.println("l2: " + l2 + "closest intersection: " + closestinter2);
        System.out.println("l3: " + l3 + "closest intersection: " + closestinter3);
        System.out.println("l4: " + l4 + "closest intersection: " + closestinter4);
        System.out.println("l5: " + l5 + "closest intersection: " + closestinter5);
    }
}

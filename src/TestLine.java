import geometry.*;
public class TestLine {
    public void TestForLine() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(5, 2);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(3, 2);
        Line l1 = new Line(p1, p2);
        Line l2 = new Line(p3, p4);
//        System.out.println("l1: " + l1);
//        System.out.println("l2: " + l2);
//        System.out.println("l1 start: " + l2.start());
//        System.out.println("l1 end: " + l2.end());
//        System.out.println("l2 start x: " + l2.start().getX());
//        System.out.println("l2 start y: " + l2.start().getY());
//        Point l1mid = l1.middle();
//        System.out.println("l1mid: " + l1mid);
//        System.out.println("l1 equals l2? " + l1.equals(l2));
        System.out.println("is l1 intersect with l2? " + l1.isIntersecting(l2));
        System.out.println("l1 intersect with l2 in: " + l1.intersectionWith(l2));

    }
}

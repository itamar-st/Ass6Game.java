import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import gamelogic.GameEnvironment;
import geometry.Line;
import geometry.Point;
import sprite.Ball;
import sprite.Block;
import sprite.Collidable;
import sprite.Velocity;

import java.awt.Color;

public class TestGameEnvironment {

    static public void drawAnimation(Point start, double dx, double dy, GameEnvironment game) {
        //create the GUI
        GUI gui = new GUI("title", 600, 600);
        Sleeper sleeper = new Sleeper();
        //create a ball with velocity
        Ball ball = new Ball(start.getX(), start.getY(), 4, java.awt.Color.BLACK, game);
        Velocity v = Velocity.fromAngleAndSpeed(dx, dy);
        ball.setVelocity(v);
        //keep running the animation
        while (true) {
            //move the ball one step within the frame's bounds
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            // draw the all, show it and wait 50 millisec
            ball.drawOn(d);
            for (Collidable c : game.getenv()) {
                ((Block) c).drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(30);  // wait for 50 milliseconds.
        }
    }
    public void Testenviomant() {
        Point recUpperLeft1 = new Point(1, 4);
        Block b1 = new Block(new Point(50, 200), 200, 35, Color.green);
        Block b2 = new Block(new Point(250, 400), 100, 35, Color.yellow);
        Block b3 = new Block(new Point(380, 350), 100, 35, Color.blue);
        Block ceiling = new Block(new Point(0, 0), 580, 20, Color.black);
        Block floor = new Block(new Point(20, 550), 580, 20, Color.black);
        Block leftWall = new Block(new Point(50, 20), 20, 580, Color.black);
        Block rightWall = new Block(new Point(580, 0), 20, 580, Color.black);
//        Block[] list = {floor,leftWall};
        Block[] list = {b1, ceiling, floor, leftWall, rightWall};
//        Block[] list = {b1, b2, b3, ceiling, floor, leftWall, rightWall};
        Line line1 = new Line(1, 1, 6, 3);
        Line line2 = new Line(5, 2, 8, 11);
        Line line3 = new Line(1, 1, 5, 5);
//        Ball ball = new Ball(new Point(10,10), 10, Color.black, );
        GameEnvironment game = new GameEnvironment();
        for (Block b :list) {
            game.addCollidable(b);
        }
        drawAnimation(new Point(120, 500), 45, 40, game);
//        CollisionInfo i = game.getClosestCollision(line2);
//        System.out.println("collition point: " + i.collisionPoint());
//        System.out.println("colliton object: " + i.collisionObject().getCollisionRectangle() + "b1 = " + b1.getCollisionRectangle());
    }

}

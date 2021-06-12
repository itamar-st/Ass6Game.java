package gamelogic;

import geometry.Point;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprite.Ball;
import sprite.Block;
import sprite.Collidable;
import sprite.DeathZone;
import sprite.Paddle;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.util.Random;


/**
 * @author itamar shachenc tov
 * @since 2021-04-28
 * Game contains all of the game environment and spirits.
 */
public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Color frameColor = Color.green;
    private GUI gui;
    private Sleeper sleeper;
    private Counter blockCounter = new Counter();
    private BlockRemover blockRemover = new BlockRemover(this, blockCounter);
    private Counter ballsCounter = new Counter();
    private BallRemover ballRemover = new BallRemover(this, ballsCounter);
    private Counter scoreCounter = new Counter();
    private ScoreTrackingListener score = new ScoreTrackingListener(scoreCounter);

    /**
     * add collidable object to the game.
     *
     * @param c collidable to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add spirit obj to the game.
     *
     * @param s spirit to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove Collidable obj to the game.
     *
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove Collidable obj to the game.
     *
     * @param s spirit to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * get a random, num in a given range.
     *
     * @param min lower bound
     * @param max upper bound
     * @return a random number between the two
     */
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(Math.abs(max - min)) + min;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */

    public void initialize() {
        //create the GUI
        int guiLength = 600;
        int guiWidth = 800;
        gui = new GUI("Arkanoid", guiWidth, guiLength);
        sleeper = new Sleeper();
        //create keyboard sensor for the paddle
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        int startOfGUI = 0;
        int blockWidth = 50;
        int blockLength = 25;
        int paddleWidth = 150;
        int borderThickness = 20;
        int cleanWidth = guiWidth - borderThickness;
        int cleanLength = guiLength;
        //init the borders and the blocks
        blockInit(startOfGUI, blockWidth, blockLength, borderThickness, cleanWidth, cleanLength);
        //init the balls
        ballInit();
        //random location for the paddle
        Paddle paddle = new Paddle(new Point(500, 560),
                paddleWidth, borderThickness, Color.blue, keyboard, borderThickness, cleanWidth);
        paddle.addToGame(this);

    }

    /**
     * ballInit- create the balls if the game and add them to the game.
     */
    private void ballInit() {
        //create 3 balls with velocity
        Point ballCenter1 = new Point(150, 500);
        int ballRadius = 5;
        Ball ball1 = new Ball(ballCenter1, ballRadius, Color.BLACK, environment);
        Velocity v1 = Velocity.fromAngleAndSpeed(250, 5);
        Point ballCenter2 = new Point(353, 500);
        Ball ball2 = new Ball(ballCenter2, ballRadius, Color.BLACK, environment);
        Velocity v2 = Velocity.fromAngleAndSpeed(230, 5);
        Point ballCenter3 = new Point(353, 500);
        Ball ball3 = new Ball(ballCenter3, ballRadius, Color.BLACK, environment);
        Velocity v3 = Velocity.fromAngleAndSpeed(270, 5);
        ball1.setVelocity(v1);
        ball2.setVelocity(v2);
        ball3.setVelocity(v3);
        //set the number of balls in the ball counter
        ballsCounter.increase(3);


        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
    }

    /**
     * blockInit- create all kinds of blocks and add them to the game.
     *
     * @param startOfGUI      the frame starting point
     * @param blockWidth      .
     * @param blockLength     .
     * @param borderThickness .
     * @param cleanWidth      of the borders
     * @param cleanLength     of the borders
     */
    private void blockInit(int startOfGUI, int blockWidth, int blockLength,
                           int borderThickness, int cleanWidth, int cleanLength) {
        //init the borders of the game
        Block ceiling = new Block(new Point(borderThickness, borderThickness), cleanWidth, borderThickness, Color.pink);
        Block leftWall = new Block(new Point(startOfGUI, borderThickness), borderThickness, cleanLength, Color.pink);
        Block rightWall = new Block(new Point(cleanWidth, borderThickness), borderThickness, cleanLength, Color.pink);
        DeathZone deathZone = new DeathZone(new Point(borderThickness, cleanLength),
                cleanWidth, borderThickness, Color.black);
        Sprite[] list = {deathZone, ceiling, leftWall, rightWall};
        // notify when the ball hits the deathzone
        deathZone.addHitListener(ballRemover);
        //add the frames to the game
        for (Sprite w : list) {
            w.addToGame(this);
        }

//        HitListener printListener = new PrintingHitListener();
        //create the blocks in a reversed pyramid shape.
        for (int j = 1; j <= 6; j++) {
            // get random colors
            float hRand = getRandomNumber(1, 359);
            float sRand = getRandomNumber(80, 99);
            float bRand = getRandomNumber(30, 60);
            Color blockColor = Color.getHSBColor(hRand, sRand, bRand);
            for (int i = 1 + j; i <= 12; i++) {
                int blocksStartX = 130;
                int blocksStartY = 150;
                //create the block and add it to the game
                Point blockCorner = new Point(blocksStartX + i * blockWidth, blocksStartY + j * blockLength);
                int evilBlockRow = 5;
                int evilBlockColumn = 3;
                if (i == evilBlockRow && j == evilBlockColumn) {
                    //add a block that deleting the ball out of the game
                    DeathZone evilBlock = new DeathZone(blockCorner, blockWidth, blockLength, Color.black);
                    evilBlock.addHitListener(ballRemover);
                    evilBlock.addToGame(this);
                } else {
                    Block b = new Block(blockCorner, blockWidth, blockLength, blockColor);
                    b.addToGame(this);
                    b.addHitListener(blockRemover);
                    b.addHitListener(score);
                }
                blockCounter.increase(1);
            }
        }
    }

    /**
     * draw the surface of the game.
     */
    private void draw() {
        DrawSurface d = gui.getDrawSurface();
        d.setColor(frameColor);
        d.fillRectangle(20, 20, 800, 600);
        d.setColor(Color.black);
        d.drawText(380, 14, "Score: " + scoreCounter.getValue(), 15);
        this.sprites.drawAllOn(d);
        gui.show(d);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        //set the fps
        int framesPerSecond = 100;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        //run the animation
        while (true) {
            // timing
            long startTime = System.currentTimeMillis();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            //draw all of the spirits
            draw();
            this.sprites.notifyAllTimePassed();
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (blockCounter.getValue() <= 0) {
                int winingPoints = 100;
                scoreCounter.increase(winingPoints);
                draw();
                sleeper.sleepFor(1000);
                gui.close();
                return;
            }
            if (ballsCounter.getValue() <= 0) {
                gui.close();
                return;
            }
        }
    }
}
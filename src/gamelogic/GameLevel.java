package gamelogic;

import LevelInformation.LevelInformation;
import geometry.Point;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprite.*;

import java.awt.Color;
import java.util.List;
import java.util.Random;


/**
 * @author itamar shachenc tov
 * @since 2021-04-28
 * GameLevel contains all of the game environment and spirits.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter blockCounter = new Counter();
    private BlockRemover blockRemover = new BlockRemover(this, blockCounter);
    private Counter ballsCounter = new Counter();
    private BallRemover ballRemover = new BallRemover(this, ballsCounter);
    private Counter scoreCounter;
    private ScoreTrackingListener score;
    private AnimationRunner runner;
    private boolean running;
    //create keyboard sensor for the paddle
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * constructor
     * @param levelInformation the specific level we are playing
     */
    public GameLevel(LevelInformation levelInformation, biuoop.KeyboardSensor keyboard,
                     AnimationRunner runner, Counter scoreCounter, ScoreTrackingListener score) {
        this.levelInformation = levelInformation;
        this.keyboard = keyboard;
        this.runner = runner;
        this.scoreCounter = scoreCounter;
        this.score = score;
    }

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
        int startOfGUI = 0;
        int blockWidth = 50;
        int blockLength = 25;
        int borderThickness = 20;
        int cleanWidth = guiWidth - borderThickness;
        int cleanLength = guiLength;
        //init the borders and the blocks
        blockInit(startOfGUI, blockWidth, blockLength, borderThickness,
                cleanWidth, cleanLength, levelInformation.blocks());
        //random location for the paddle
        Paddle paddle = new Paddle(new Point(500, 560),
                levelInformation.paddleWidth(), borderThickness, Color.yellow, keyboard, borderThickness, cleanWidth);
        paddle.addToGame(this); // Todo: maybe i need to add this to run too
    }

    /**
     * ballInit- create the balls if the game and add them to the game.
     */
    private void ballInit() {
        //create 3 balls with velocity
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            //create the center of the ball
            Point ballCenter1 = new Point(380, 500);
            int ballRadius = 5;
            Ball ball = new Ball(ballCenter1, ballRadius, Color.WHITE, environment);
            //take the velocity from the current level
            Velocity v = levelInformation.initialBallVelocities().get(i);
            ball.setVelocity(v);
            ballsCounter.increase(1);
            ball.addToGame(this);
        }
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
     * @param blocksOfLevel the blocks from the current level
     */
    private void blockInit(int startOfGUI, int blockWidth, int blockLength,
                           int borderThickness, int cleanWidth, int cleanLength, List<Block> blocksOfLevel) {
        //init the borders of the game
        Block ceiling = new Block(new Point(borderThickness, borderThickness), cleanWidth, borderThickness, Color.pink);
        Block leftWall = new Block(new Point(startOfGUI, borderThickness), borderThickness, cleanLength, Color.pink);
        Block rightWall = new Block(new Point(cleanWidth, borderThickness), borderThickness, cleanLength, Color.pink);
        DeathZone deathZone = new DeathZone(new Point(borderThickness, cleanLength),
                cleanWidth, borderThickness, Color.black);
        Sprite background = levelInformation.getBackground();
        Sprite[] list = {deathZone, ceiling, leftWall, rightWall, background};
        // notify when the ball hits the deathzone
        deathZone.addHitListener(ballRemover);
        //add the frames to the game
        for (Sprite w : list) {
            w.addToGame(this);
        }
        for (Block b : blocksOfLevel) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(score);
            blockCounter.increase(1);
        }
    }



    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        //init the balls
        ballInit();
        //countdown screen
//        this.runner.run(new CountdownAnimation(3, 3, sprites)); // countdown before turn starts.Todo: finish the countdown
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * draw the surface of the game.
     */
    public void draw(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(100, 14, "Lives: ", 15);
        d.drawText(380, 14, "Score: " + scoreCounter.getValue(), 15);
        d.drawText(550, 14, "Level Name: " + levelInformation.levelName(), 15);
        this.sprites.drawAllOn(d);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw all of the spirits
        draw(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        if (blockCounter.getValue() == levelInformation.blocksAmount() - levelInformation.numberOfBlocksToRemove()) {
            int winingPoints = 100;
            scoreCounter.increase(winingPoints);
//            draw();Todo: return the last draw after ending the game
//            sleeper.sleepFor(1000);
//            runner.getGui().close();
            this.running = false;
        }
        if (ballsCounter.getValue() <= 0) {
            runner.getGui().close();
            this.running = false;
        }
        return !this.running;
    }

    public int getBallsCounter() {
        return ballsCounter.getValue();
    }
}
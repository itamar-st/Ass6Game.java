package gamelogic;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class AnimationRunner {
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();
    private SpriteCollection sprites;
    private int guiLength = 600;
    private int guiWidth = 800;
    private GUI gui = new GUI("Arkanoid", guiWidth, guiLength);

    public void run(Animation animation) {
        //set the fps
        int framesPerSecond = 100;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    public GUI getGui() {
        return gui;
    }
}
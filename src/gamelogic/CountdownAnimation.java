package gamelogic;

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private double counter;
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        sleeper = new Sleeper();
        counter = numOfSeconds;
    }
    public void doOneFrame(DrawSurface d) {
//        GameLevel primaryGame = new GameLevel();
//        primaryGame.draw(d);
        d.setColor(Color.black);
        d.fillRectangle(20, 20, 800, 600);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(380, 300, "Score: ", 50);
        int ascciAdd = 48;
        sleeper.sleepFor((long) numOfSeconds / countFrom);
    }
    public boolean shouldStop() {
        return counter == 0;
    }
}
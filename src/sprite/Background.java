package sprite;

import biuoop.DrawSurface;
import gamelogic.GameLevel;
import geometry.Point;

import java.awt.*;

public class Background implements Sprite {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;

    public Background(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}

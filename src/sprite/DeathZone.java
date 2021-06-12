package sprite;

import biuoop.DrawSurface;
import geometry.Point;

import java.awt.Color;

/**
 * a special block which removes balls from the game.
 */
public class DeathZone extends Block {
    /**
     * constructor.
     *
     * @param upperLeft point of the block.
     * @param width     of the block
     * @param height    of the block
     * @param color     of the block
     */
    public DeathZone(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
    }

    /**
     * draw the block without a rectangle as border.
     *
     * @param surface in which we want to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(super.getColor());
        surface.fillRectangle((int) super.getUpperLeft().getX(), (int) super.getUpperLeft().getY(),
                (int) super.getWidth(), (int) super.getHeight());
    }
}

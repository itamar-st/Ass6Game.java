package gamelogic;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import sprite.Sprite;

/**
 * SpiritCollection contains all of the spirits in the game.
 * @author itamar shachenc tov
 * @since 2021-04-28
 */
public class SpriteCollection {
    private List<Sprite> spiritObjects = new ArrayList<Sprite>();

    /**
     * add a spirit to the list.
     *
     * @param s the spirit we want to add
     */
    public void addSprite(Sprite s) {
        spiritObjects.add(s);
    }

    /**
     * remove a spirit to the list.
     *
     * @param s the spirit we want to remove
     */
    public void removeSprite(Sprite s) {
        spiritObjects.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spiritObjectscopy = new ArrayList<>(spiritObjects);
        for (Sprite s : spiritObjectscopy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d drawing surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spiritObjects) {
            s.drawOn(d);
        }
    }

    /**
     * getter.
     *
     * @return the spirits
     */
    public List<Sprite> getspirits() {
        return spiritObjects;
    }
}
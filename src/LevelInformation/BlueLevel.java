package LevelInformation;

import geometry.Point;
import sprite.Background;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlueLevel implements LevelInformation {
    private int numOfBlocks;
    public BlueLevel() {
        numOfBlocks = 0;
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(250, 5));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "BlackLevel";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Point(20, 40), 760, 560, Color.blue);
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        list.add(new Block(new Point(380, 100), 30, 30, Color.red));
        numOfBlocks += 1;
        return list;
    }
    @Override
    public int blocksAmount() {
        return numOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}

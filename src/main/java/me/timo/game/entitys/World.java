package me.timo.game.entitys;

import me.timo.game.utils.Sprite;
import me.timo.game.utils.Vector;

import java.util.ArrayList;

public class World {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public String name;
    public ArrayList<Block> blocks = new ArrayList<>();

    public World(String name) {
        this.name = name;
    }

    public void setBlock(Vector vector, Sprite sprite) {
        vector.multiply(64);
        sprite.setLocation(vector);
        blocks.add(new Block(sprite));
    }

}

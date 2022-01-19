package me.timo.game.entitys;

import me.timo.game.utils.Sprite;
import me.timo.game.utils.Vector;

public class Block {

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite sprite;

    public Block(Sprite sprite)  {
        try {
            this.sprite = sprite.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}

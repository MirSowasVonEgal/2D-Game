package me.timo.game.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sprite {

    public Vector location;
    public Vector velocity;
    public Image image;

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setBoundary(Rectangle boundary) {
        this.boundary = boundary;
    }

    public Rectangle boundary;

    public Sprite() {
        location = new Vector();
        velocity = new Vector();
        boundary = new Rectangle();
    }

    public Rectangle getBoundary() {
        return new Rectangle(location.x, location.y, image.getWidth(), image.getHeight());
    }

    public boolean isTouching(Sprite sprite) {
        return getBoundary().isTouching(sprite.getBoundary());
    }

    public void setImage(String file) {
        image = new Image(file);
        boundary.height = image.getHeight();
        boundary.width = image.getWidth();
    }

    public void render(GraphicsContext context) {
        context.drawImage(image, location.x, location.y);
    }

}

package me.timo.game.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sprite implements Cloneable {

    public Vector location;
    public Vector velocity;
    public Image image;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color color;

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
        type = "";
    }

    public Rectangle getBoundary() {
        boundary.x = location.x;
        boundary.y = location.y;
        return boundary;
    }

    public boolean isTouching(Sprite sprite) {
        return getBoundary().isTouching(sprite.getBoundary());
    }

    public void setSize(double width, double height) {
        boundary.width = width;
        boundary.height = height;
        boundary.x = location.x;
        boundary.y = location.y;
    }

    public void setImage(String file) {
        image = new Image(file);
        boundary.width = image.getWidth();
        boundary.height = image.getHeight();
        boundary.x = location.x;
        boundary.y = location.y;
    }

    public void render(GraphicsContext context) {
        if(image != null) {
            context.drawImage(image, location.x, location.y);
        } else {
            context.setFill(color);
            context.fillRect(location.x, location.y, boundary.width, boundary.height);
        }
    }

    @Override
    public Sprite clone() throws CloneNotSupportedException {
        return (Sprite) super.clone();
    }

}

package me.timo.game.utils;

public class Rectangle {

    public double x;
    public double y;
    public double width;
    public double height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    public boolean isTouching(Rectangle rectangle) {
        return !(this.x + this.width < rectangle.x ||
                rectangle.x + rectangle.width < this.x ||
                this.y + this.height < rectangle.y ||
                rectangle.y + rectangle.height < this.y);
    }

}

package me.timo.game.utils;

public class Vector {

    public double x;
    public double y;

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void remove(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void add(Vector vector) {
        add(vector.x, vector.y);
    }

    public void remove(Vector vector) {
        remove(vector.x, vector.y);
    }

    public void multiply(double m) {
        this.x *= m;
        this.y *= m;
    }

}

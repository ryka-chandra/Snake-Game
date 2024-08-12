package org.cis1200.snake;

import java.awt.Graphics;

/**
 * An object in the game.
 *
 * Game objects exist in the game court. They have a position, velocity, size
 * and bounds. Their velocity controls how they move; their position should
 * always be within their bounds.
 */
public abstract class GameObj {
    /*
     * Current position of the object
     */
    private int px;
    private int py;

    /* Size of object, in pixels. */
    private final int width;
    private final int height;

    /* Velocity: number of pixels to move every time move() is called. */
    private int vx;
    private int vy;

    /**
     * Constructor
     */
    public GameObj(
            int vx, int vy, int px, int py, int width, int height, int courtwidth,
            int courtheight
    ) {
        this.vx = vx;
        this.vy = vy;
        this.px = px;
        this.py = py;
        this.width = width;
        this.height = height;
    }

    /*
     * GETTERS
     */
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }

    public int getVx() {
        return this.vx;
    }

    public int getVy() {
        return this.vy;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /*
     * SETTERS
     */
    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    /**
     * Determine whether this game object is currently intersecting another
     * object. Used in GameCourt.java.
     */
    public boolean intersects(GameObj that) {
        return (this.px == that.px && this.py == that.py);
    }

    /**
     * Default draw method that provides how the object should be drawn in the
     * GUI. This method does not draw anything. Subclass overrides this
     * method based on how object should appears.
     */
    public abstract void draw(Graphics g);
}
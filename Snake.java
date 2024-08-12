package org.cis1200.snake;

import java.awt.*;
import java.util.*;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a rectangle of a green color.
 */
public class Snake extends GameObj {
    public static final int SIZE = 20;
    private java.util.List<Integer> body = new LinkedList<Integer>();
    public static final int INIT_POS_X = 3;
    public static final int INIT_POS_Y = 1;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private final Color color;

    /**
     * Constructor
     */
    public Snake(int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
        body.add(2);
        body.add(1);
        body.add(1);
        body.add(1);
        this.color = color;
    }

    /*
     * GETTER
     */
    public java.util.List<Integer> getBody() {
        return body;
    }

    /*
     * Checks to see if something has intersected the body based on the coordinates of
     * the body and the coordinates of the thing in intersection.
     */
    public boolean intersectBody(int x, int y) {
        for (int i = 0; i < getBody().size(); i = i + 2) {
            if (x == getBody().get(i) && y == getBody().get(i + 1)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Once the snake has eaten an apple, the snake grows by one grid space
     * from the head
     */
    public void grow() {
        getBody().add(0, this.getPy());
        getBody().add(0, this.getPx());
        this.setPx(this.getPx() + this.getVx());
        this.setPy(this.getPy() + this.getVy());
    }

    /*
     * If the velocity of the snake is not 0, the position will get added to by 1
     * and the new coordinates will be updated at the head of the body list.
     */
    public void move() {
        if (!(this.getVx() == 0 && this.getVy() == 0)) {
            int oldpx = this.getPx();
            int oldpy = this.getPy();
            this.setPx(this.getPx() + this.getVx());
            this.setPy(this.getPy() + this.getVy());
            getBody().add(0, oldpy);
            getBody().add(0, oldpx);
            getBody().remove(getBody().size() - 1);
            getBody().remove(getBody().size() - 1);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getPx() * SIZE, this.getPy() * SIZE, this.getWidth(), this.getHeight());
        for (int i = 0; i < getBody().size(); i = i + 2) {
            g.fillRect(
                getBody().get(i) * SIZE, getBody().get(
                    i + 1) * SIZE, this.getWidth(), this.getHeight());
        }
    }

    @Override
    public void setVx(int v) {
        if ((this.getPx() + v) == getBody().get(0)) {
            return;
        } else {
            super.setVx(v);
        }
    }

    @Override
    public void setVy(int v) {
        if ((this.getPy() + v) == getBody().get(1)) {
            return;
        } else {
            super.setVy(v);
        }
    }
}
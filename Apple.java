package org.cis1200.snake;

import java.awt.*;

/**
 * A basic game object starting at a random position on the game court. It
 * is displayed as a circle of a red color.
 */
public class Apple extends GameObj {
    public static final int SIZE = 20;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    private static int courtWidth;
    private static int courtHeight;

    final private Color color;

    /**
     * Constructor
     */
    public Apple(int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
        this.courtWidth = courtWidth;
        this.courtHeight = courtHeight;
        this.color = color;
    }

    /*
     * GETTERS
    */
    public int getCourtWidth() {
        return courtWidth;
    }

    public int getCourtHeight() {
        return courtHeight;
    }

    /*
    * This changes the location of the apple to a new random spot on the grid 
    */
    public void reloc() {
        this.setPx((int) (Math.random() * ((getCourtWidth() / SIZE) - 2)) + 1);
        this.setPy((int) (Math.random() * ((getCourtHeight() / SIZE) - 2)) + 1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPx() * SIZE, this.getPy() * SIZE, this.getWidth(), this.getHeight());
    }
}
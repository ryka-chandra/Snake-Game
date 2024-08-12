package org.cis1200.snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* Class that deals with all of the "action" this is happening 
 * on the grid itself.
 */
public class GameCourt extends JPanel {

    // the state of the game logic
    private Snake snake;
    private Apple apple;

    private boolean playing = false; // whether the game is running
    private final JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    private static int courtWidth = 300;
    private static int courtHeight = 300;
    public static final int SNAKE_VELOCITY = 1;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 60;

    /**
     * Constructor
     */
    public GameCourt(JLabel status) {
        Timer timer = new Timer(INTERVAL, e -> tick());
        timer.start();

        setFocusable(true);

        // This key listener allows the snake to move as long as an arrow key
        // is pressed, by changing the snake's velocity accordingly. (The tick
        // method below actually moves the snake.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.setVx(-SNAKE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.setVx(SNAKE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.setVy(SNAKE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snake.setVy(-SNAKE_VELOCITY);
                }
            }

            public void keyReleased(KeyEvent e) {
                snake.setVx(0);
                snake.setVy(0);
            }
        });

        this.status = status;
    }

    /*
     * GETTERS
     */
    public int getCourtWidth() {
        return this.courtWidth;
    }

    public int getCourtHeight() {
        return this.courtHeight;
    }

    /*
     * SETTERS
     */
    public void setCourtWidth(int courtWidth) {
        this.courtWidth = courtWidth;
    }

    public void setCourtHeight(int courtHeight) {
        this.courtHeight = courtHeight;
    }

    /**
     * While the snake is intersecting the apple, the apple is being relocated
     * to a new random position.
     */
    public int[] snakeIntersectApple() {
        while (snake.intersects(apple) || snake.intersectBody(apple.getPx(), apple.getPy())) {
            apple.reloc();
        }
        int[] arr = {apple.getPx(), apple.getPy()};
        return arr;
    }
    
    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLUE));

        snake = new Snake(courtWidth, courtHeight, Color.GREEN);
        apple = new Apple(courtWidth, courtHeight, Color.RED);
        apple.reloc();
        snakeIntersectApple();

        playing = true;
        status.setText("Running...");

        requestFocusInWindow();
    }

    /**
     * For the File I/O implementation, this sets the size of the grid based on user input 
     */
    public void setSize(int inputSize) {
        setCourtWidth(inputSize);
        setCourtHeight(inputSize);
        reset();
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            // advance the snake
            snake.move();

            // check for the game end conditions
            if (snake.intersects(apple)) {
                while (snake.intersectBody(
                    apple.getPx(), apple.getPy()) || (
                        apple.getPx() == snake.getPx() && apple.getPy() == snake.getPy())) {
                    apple.reloc();
                }
                snake.grow();
            }
            if (snake.intersectBody(snake.getPx(), snake.getPy())) {
                playing = false;
                status.setText("You lose!");
            }
            if (snake.getPx() >= (
                courtWidth / snake.getWidth()) || snake.getPy() >= (
                    courtHeight / snake.getHeight()) || snake.getPx() < 0 || snake.getPy() < 0) {
                playing = false;
                status.setText("You lose!");
            }

            // update the display
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        apple.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(courtWidth, courtHeight);
    }
}
package org.cis1200.snake;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import javax.swing.*;

/**
 * Tests my code for the Snake Game (JUnit tests).
 */

public class GameTest {

    @Test
    void testAppleConstructor() {
        int courtWidth = 100;
        int courtHeight = 100;
        Color color = Color.RED;

        Apple apple = new Apple(courtWidth, courtHeight, color);

        assertEquals(Apple.INIT_POS_X, apple.getPx());
        assertEquals(Apple.INIT_POS_Y, apple.getPy());
        assertEquals(Apple.SIZE, apple.getWidth());
        assertEquals(Apple.SIZE, apple.getHeight());
        assertEquals(Apple.INIT_VEL_X, apple.getVx());
        assertEquals(Apple.INIT_VEL_Y, apple.getVy());
    }

    @Test
    void testAppleReloc() {
        int courtWidth = 100;
        int courtHeight = 100;
        Color color = Color.RED;

        Apple apple = new Apple(courtWidth, courtHeight, color);
        int initialPx = apple.getPx();
        int initialPy = apple.getPy();

        apple.reloc();

        assertNotEquals(initialPx, apple.getPx());
        assertNotEquals(initialPy, apple.getPy());
        assertTrue(apple.getPx() > 0 && apple.getPx() < courtWidth / Apple.SIZE - 1);
        assertTrue(apple.getPy() > 0 && apple.getPy() < courtHeight / Apple.SIZE - 1);
    }
    
    @Test
    void testGameCourtSnakeIntersectApple() {
        JLabel status = new JLabel();
        GameCourt gameCourt = new GameCourt(status);
        gameCourt.reset();

        for (int i = 0; i < 20; i++) {
            int[] coord = gameCourt.snakeIntersectApple();
            assertTrue(3 != coord[0]);
            assertTrue(1 != coord[1]);
        }
    }
    
    @Test
    void testGameCourtSetSize() {
        JLabel status = new JLabel();
        GameCourt gameCourt = new GameCourt(status);

        int newSize = 400;
        gameCourt.setSize(newSize);

        assertEquals(newSize, gameCourt.getCourtWidth());
        assertEquals(newSize, gameCourt.getCourtHeight());
        assertEquals("Running...", status.getText());
    }

    @Test
    void testGameCourtPreferredSize() {
        JLabel status = new JLabel();
        GameCourt gameCourt = new GameCourt(status);

        Dimension preferredSize = gameCourt.getPreferredSize();

        assertEquals(gameCourt.getCourtWidth(), preferredSize.width);
        assertEquals(gameCourt.getCourtHeight(), preferredSize.height);
    }

    @Test
    void testGameObjConstructorAndGetters() {
        int courtWidth = 300;
        int courtHeight = 300;
        Color color = Color.BLUE;

        GameObj gameObj = new Snake(courtWidth, courtHeight, color);

        assertEquals(0, gameObj.getVx());
        assertEquals(0, gameObj.getVy());
        assertEquals(3, gameObj.getPx());
        assertEquals(1, gameObj.getPy());
        assertEquals(20, gameObj.getWidth());
        assertEquals(20, gameObj.getHeight());
    }

    @Test
    void testGameObjSetters() {
        int courtWidth = 300;
        int courtHeight = 300;
        Color color = Color.BLUE;
        GameObj gameObj = new Snake(courtWidth, courtHeight, color);

        int newVx = 2;
        int newVy = -2;
        int newPx = 50;
        int newPy = 60;

        gameObj.setVx(newVx);
        gameObj.setVy(newVy);
        gameObj.setPx(newPx);
        gameObj.setPy(newPy);

        assertEquals(newVx, gameObj.getVx());
        assertEquals(newVy, gameObj.getVy());
        assertEquals(newPx, gameObj.getPx());
        assertEquals(newPy, gameObj.getPy());
    }

    @Test
    void testGameObjIntersects() {
        int courtWidth = 300;
        int courtHeight = 300;
        Color color = Color.BLUE;
        GameObj obj1 = new Snake(courtWidth, courtHeight, color);
        GameObj obj2 = new Apple(courtWidth, courtHeight, color);
        GameObj obj3 = new Snake(courtWidth, courtHeight, color);

        assertFalse(obj1.intersects(obj2));
        assertTrue(obj1.intersects(obj3));
    }

    @Test
    void testSnakeConstructorAndGetters() {
        int courtWidth = 300;
        int courtHeight = 300;
        Color color = Color.GREEN;

        Snake snake = new Snake(courtWidth, courtHeight, color);

        assertEquals(Snake.INIT_POS_X, snake.getPx());
        assertEquals(Snake.INIT_POS_Y, snake.getPy());
        assertEquals(Snake.SIZE, snake.getWidth());
        assertEquals(Snake.SIZE, snake.getHeight());
        assertEquals(Snake.INIT_VEL_X, snake.getVx());
        assertEquals(Snake.INIT_VEL_Y, snake.getVy());
        assertEquals(20, snake.getWidth());
        assertEquals(20, snake.getHeight());
        assertEquals(4, snake.getBody().size());
    }

    @Test
    void testSnakeIntersectBody() {
        Snake snake = new Snake(300, 300, Color.GREEN);

        assertFalse(snake.intersectBody(2, 2));

        snake.setVx(1);
        snake.grow();
        snake.grow();
        snake.grow();

        assertTrue(snake.intersectBody(3, 1));
        assertTrue(snake.intersectBody(4, 1));
        assertTrue(snake.intersectBody(5, 1));
        assertFalse(snake.intersectBody(6, 1));
        assertFalse(snake.intersectBody(2, 2));
        assertFalse(snake.intersectBody(10, 10));
    }

    @Test
    void testSnakeGrow() {
        Snake snake = new Snake(300, 300, Color.GREEN);

        assertEquals(4, snake.getBody().size());

        snake.grow();

        assertEquals(6, snake.getBody().size());
        assertEquals(snake.getPx(), (int) snake.getBody().get(0));
        assertEquals(snake.getPy(), (int) snake.getBody().get(1));
    }

    @Test
    void testSnakeMove() {
        Snake snake = new Snake(300, 300, Color.GREEN);

        assertEquals(4, snake.getBody().size());

        snake.move();

        assertEquals(4, snake.getBody().size());

        snake.setVx(1);
        snake.move();

        assertEquals(4, snake.getBody().size());
        assertEquals(snake.getPx(), 4);
        assertEquals(snake.getPy(), 1);
    }

    @Test
    void testSnakeSetVx() {
        Snake snake = new Snake(300, 300, Color.GREEN);

        snake.setVx(1);
        assertEquals(1, snake.getVx());

        snake.setVx(-1);
        assertEquals(1, snake.getVx()); //can't go directly from right to left

        snake.grow();

        assertEquals(1, snake.getVx()); 
    }

    @Test
    void testSnakeSetVy() {
        Snake snake = new Snake(300, 300, Color.GREEN);

        snake.setVy(1);
        assertEquals(1, snake.getVy());

        snake.setVy(-1);
        assertEquals(-1, snake.getVy()); //you're allowed to go from right to down

        snake.grow();

        assertEquals(-1, snake.getVy());
    }

}

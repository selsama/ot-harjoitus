/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.scene.paint.Color;
import snakegame.domain.*;
import javafx.scene.shape.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salmison
 */
public class GameHandlerSnakeTest {
    
    private GameHandler game;
    private int size;
    
    public GameHandlerSnakeTest() {
        size = 100;
        game = new GameHandler(size, size);
    }
    
    @Test
    public void getSnakeReturnsSnakeHead() {
        assertTrue(game.getSnake() instanceof SnakeHead);
    }
    
    @Test
    public void snakeStartsInMiddleTest() {
        Rectangle snake = game.getSnake().getShape();
        assertEquals("X-coordinate is not in middle", 1.0 * size / 2, snake.getX(), 1.0);
        assertEquals("Y-coordinate is not in middle", 1.0 * size / 2, snake.getY(), 1.0);
    }
    
    @Test
    public void moveSnakeMovesSnake() {
        Rectangle snake = game.getSnake().getShape();
        double x = snake.getX();
        double y = snake.getY();
        double epsilon = 0.0001;
        game.moveSnake();
        assertFalse(Math.abs(x - snake.getX()) < epsilon && Math.abs(y - snake.getY()) < epsilon);
    }
    
    @Test
    public void turnSnakeTurnsSnake() {
        SnakeHead snake = game.getSnake();
        game.turnSnake(Direction.RIGHT);
        assertEquals("Does not turn right", Direction.RIGHT, snake.getDirection());
        game.turnSnake(Direction.DOWN);
        assertEquals("Does not turn down", Direction.DOWN, snake.getDirection());
        game.turnSnake(Direction.LEFT);
        assertEquals("Does not turn left", Direction.LEFT, snake.getDirection());
        game.turnSnake(Direction.UP);
        assertEquals("Does not turn up", Direction.UP, snake.getDirection());
    }
    
    @Test
    public void setSnakeColorTest() {
        SnakeHead snake = game.getSnake();
        game.setSnakeColor(Color.AQUA);
        assertTrue(snake.getColor().equals(Color.AQUA));
    }

}

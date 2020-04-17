/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import snakegame.domain.*;
import javafx.scene.shape.*;
import org.junit.After;
import org.junit.Before;
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import snakegame.domain.*;

/**
 *
 * @author salmison
 */
public class SnakeHeadCrashingTest {
    
    SnakeHead snake;
    Obstacle obs;
    
    @Before
    public void setUp() {
        snake = new SnakeHead(10, 10);
        obs = new Obstacle(10, 10);
    }
    
    @Test
    public void snakeCrashesWithObstacleWhenOverlapping() {
        assertTrue(snake.crash(obs));
    }
    
    @Test
    public void snakeCrashesWithObstacleWhenOverlappingJustLittle() {
        obs.setPosition(0, 0);
        assertTrue(snake.crash(obs));
    }
    
    @Test
    public void snakeDoesNotCrashWhenNextToObstacle() {
        obs.setPosition(20, 10);
        assertFalse(snake.crash(obs));
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

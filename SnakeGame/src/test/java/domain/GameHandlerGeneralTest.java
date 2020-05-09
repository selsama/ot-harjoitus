/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.*;
import javafx.scene.input.KeyCode;
import snakegame.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salmison
 */
public class GameHandlerGeneralTest {
    
    private GameHandler game;
    
    public GameHandlerGeneralTest() {
        game = new GameHandler(100,100);
    }
    
    @Test
    public void speedTest() {
        assertEquals(40000000, game.getSpeed());
        game.setSpeed(10);
        assertEquals(10, game.getSpeed());
        game.setSpeed(0);
        assertEquals(0, game.getSpeed());
    }
    
    @Test
    public void getObstaclesReturnsList() {
        assertTrue(game.getObstacles() instanceof List);
    }
    
//    button tests
    @Test
    public void testPauseButton() {
        assertTrue("game should start on pause", game.onPause());
        game.start();
        assertFalse("starting the game should unpause it", game.onPause());
        game.handleKeyPressed(KeyCode.P);
        assertTrue("pressing P should pause the game when unpaused", game.onPause());
        game.handleKeyPressed(KeyCode.P);
        assertFalse("pressing P should unpause the game when paused", game.onPause());
    }
    
    @Test
    public void testTurnButtons() {
        game.start();
        game.handleKeyPressed(KeyCode.RIGHT);
        assertEquals("Not turning right", Direction.RIGHT, 
                game.getSnake().getDirection());
        game.handleKeyPressed(KeyCode.LEFT);
        assertEquals("Turning to opposite direction should not be possible", 
                Direction.RIGHT, game.getSnake().getDirection());
        game.handleKeyPressed(KeyCode.DOWN);
        assertEquals("Not turning down", Direction.DOWN,
                game.getSnake().getDirection());
        game.handleKeyPressed(KeyCode.UP);
        assertEquals("Turning to opposite direction should not be possible", 
                Direction.DOWN, game.getSnake().getDirection());
        game.handleKeyPressed(KeyCode.LEFT);
        assertEquals("Not turning left", Direction.LEFT,
                game.getSnake().getDirection());
        game.handleKeyPressed(KeyCode.UP);
        assertEquals("Not turning up", Direction.UP, 
                game.getSnake().getDirection());
    }
    
    
//    gameover tests
    
    @Test
    public void gameOverIfSnakeHitsWall() {
        game.getSnake().setDirection(Direction.UP);
        for(int i = 0; i < 5; i++) {
            game.getSnake().move();
        }
        assertTrue("problem: upper wall", game.gameOver());
        game.newGame();
        game.getSnake().setDirection(Direction.RIGHT);
        for(int i = 0; i < 5; i++) {
            game.getSnake().move();
        }
        assertTrue("problem: right wall", game.gameOver());
        game.newGame();
        game.getSnake().setDirection(Direction.DOWN);
        for(int i = 0; i < 5; i++) {
            game.getSnake().move();
        }
        assertTrue("problem: down wall", game.gameOver());
        game.newGame();
        game.getSnake().setDirection(Direction.LEFT);
        for(int i = 0; i < 5; i++) {
            game.getSnake().move();
        }
        assertTrue("problem: left wall", game.gameOver());
    }

    @Test
    public void snakeEatsOwnTailTest() {
        game.handleKeyPressed(KeyCode.UP);
        game.moveSnake();
        game.moveSnake();
        game.handleKeyPressed(KeyCode.LEFT);
        game.moveSnake();
        game.moveSnake();
        game.handleKeyPressed(KeyCode.DOWN);
        game.moveSnake();
        game.moveSnake();
        game.handleKeyPressed(KeyCode.RIGHT);
        game.moveSnake();
        game.moveSnake();
        assertTrue("Snake hit own tail but did not die", game.gameOver());
        game.handleKeyPressed(KeyCode.UP);
        assertEquals("Snake turned after gameover", Direction.RIGHT, 
                        game.getSnake().getDirection());
    }
    
    @Test
    public void SnakeHitsNothingTest() {
        game.moveSnake();
        game.moveSnake();
        assertFalse(game.gameOver());
        game.handleKeyPressed(KeyCode.RIGHT);
        game.moveSnake();
        game.moveSnake();
        game.moveSnake();
        assertFalse("Snake hit right wall too early",game.gameOver());
        game.handleKeyPressed(KeyCode.DOWN);
        game.moveSnake();
        game.handleKeyPressed(KeyCode.LEFT);
        game.moveSnake();
        assertFalse("Snake should be able to make a u-turn", game.gameOver());
        game.handleKeyPressed(KeyCode.RIGHT);
        game.moveSnake();
        assertFalse("Snake should not die from player trying to turn to opposite direction",
                game.gameOver());
        assertEquals("Snake should not turn to opposite direction", 
                Direction.LEFT, game.getSnake().getDirection());
    }
    
    
}

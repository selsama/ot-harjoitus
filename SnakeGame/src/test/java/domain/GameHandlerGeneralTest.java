/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.*;
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
    
//    @Test
//    public void wallsExist() {
//        
//    }
    
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
    
    
    
}

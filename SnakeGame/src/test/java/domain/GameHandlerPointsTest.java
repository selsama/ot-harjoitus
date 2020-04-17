/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import snakegame.domain.GameHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salmison
 */
public class GameHandlerPointsTest {
    
    private GameHandler game;
    
    public GameHandlerPointsTest() {
        game = new GameHandler(10,10);
    }
    

    @Test
    public void pointsStartOnZero() {
        assertEquals(0,game.getPoints());
    }
    
    @Test
    public void addPointsAddsPoints() {
        int start = game.getPoints();
        game.addPoints(10);
        assertTrue(start<game.getPoints());
    }
    
    @Test
    public void addPointsAddsCorrectAmount() {
        int start = game.getPoints();
        game.addPoints(100);
        assertEquals(100,game.getPoints());
    }
}

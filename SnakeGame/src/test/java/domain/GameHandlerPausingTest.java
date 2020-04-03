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
public class GameHandlerPausingTest {
    
    private GameHandler game;
    
    @Before
    public void setUp() {
        game = new GameHandler(10,10);
    }
    
    @Test
    public void gameStartsOnPause() {
        assertTrue(game.PAUSED);
    }
    
    @Test
    public void setOnPauseWorks() {
        game.PAUSED=false;
        game.setOnPause();
        assertTrue(game.PAUSED);
    }
    
    @Test
    public void setOffPauseWorks() {
        game.PAUSED = true;
        game.setOffPause();
        assertFalse(game.PAUSED);
    }
    
    @Test
    public void triggerPauseWorksWhenPaused() {
        game.PAUSED = true;
        game.triggerPause();
        assertFalse(game.PAUSED);
    }
    
    @Test
    public void triggerPauseWorksWhenUnpaused() {
        game.PAUSED = false;
        game.triggerPause();
        assertTrue(game.PAUSED);
    }
}

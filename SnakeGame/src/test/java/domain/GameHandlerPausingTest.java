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
        assertTrue(game.paused);
    }
    
    @Test
    public void setOnPauseWorks() {
        game.paused=false;
        game.setOnPause();
        assertTrue(game.paused);
    }
    
    @Test
    public void setOffPauseWorks() {
        game.paused = true;
        game.setOffPause();
        assertFalse(game.paused);
    }
    
    @Test
    public void triggerPauseWorksWhenPaused() {
        game.paused = true;
        game.triggerPause();
        assertFalse(game.paused);
    }
    
    @Test
    public void triggerPauseWorksWhenUnpaused() {
        game.paused = false;
        game.triggerPause();
        assertTrue(game.paused);
    }
}

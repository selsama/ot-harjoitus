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
        assertTrue(game.onPause());
    }
    
    @Test
    public void setOnPauseWorks() {
        game.setOffPause();
        assertFalse(game.onPause());
        game.setOnPause();
        assertTrue(game.onPause());
    }
    
    @Test
    public void setOffPauseWorks() {
        game.setOffPause();
        assertFalse(game.onPause());
    }
    
    @Test
    public void triggerPauseWorksWhenPaused() {
        game.triggerPause();
        assertFalse(game.onPause());
    }
    
    @Test
    public void triggerPauseWorksWhenUnpaused() {
        game.setOffPause();
        game.triggerPause();
        assertTrue(game.onPause());
    }
}

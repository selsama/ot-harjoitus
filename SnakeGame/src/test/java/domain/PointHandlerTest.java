/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.*;
import snakegame.domain.PointHandler;
import snakegame.dao.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salmison
 */
public class PointHandlerTest {
    
    private PointHandler points;
    private TestHighScoreDao testDao;
    
    public PointHandlerTest() {
        testDao = new TestHighScoreDao();
        points = new PointHandler(testDao);
    }
    
    @Test
    public void pointsStartOnZero() {
        assertEquals(0,points.getPoints());
    }
    
    @Test
    public void addPointsAddsPoints() {
        int start = points.getPoints();
        points.addPoints(10);
        assertTrue(start<points.getPoints());
    }
    
    @Test
    public void addPointsAddsCorrectAmount() {
        int start = points.getPoints();
        points.addPoints(100);
        assertEquals(100, points.getPoints());
    }
    
    @Test
    public void resetResetsPoints() {
        points.addPoints(300);
        points.reset();
        assertEquals(0, points.getPoints());
    }
    
    @Test
    public void checkIfHighScoreReturnsBoolean() {
        assertTrue(points.checkIfHighScore());
    }
    
    @Test
    public void getHighScoreReturnsList() {
        assertTrue(points.getHighscores() instanceof List);
    }
    
    @Test
    public void addNewHighScoreReturnsBoolean() {
        assertTrue(points.addNewHighscore("name"));
    }
    
    @Test
    public void biggerHighScoreTest() {
        assertEquals("List should be empty first", 0, points.getHighscores().size());
        points.addPoints(1);
        for (int i = 0; i < 10; i++) {
            assertTrue("Everything is highscore until 10 scores", points.checkIfHighScore());
            points.addNewHighscore("1 point");
        }
        assertEquals("Adding highscores is not working", 10, points.getHighscores().size());
        points.addPoints(1);
        points.addNewHighscore("2 points");
        assertEquals("After 10, list shouldn't grow", 10, points.getHighscores().size());
        points.reset();
        assertEquals("Resetting shouldn't affect saved highscores", 10, points.getHighscores().size());
        points.addPoints(3);
        points.addNewHighscore("3 points");
        assertEquals("After 10, list shouldn't grow", 10, points.getHighscores().size());
    }
}

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
        assertEquals(100,points.getPoints());
    }
    
    @Test
    public void resetResetsPoints() {
        points.addPoints(300);
        points.reset();
        assertEquals(0,points.getPoints());
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
}

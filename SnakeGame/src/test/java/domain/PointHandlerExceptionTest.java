/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import snakegame.domain.PointHandler;
import snakegame.dao.*;

/**
 *
 * @author salmison
 */
public class PointHandlerExceptionTest {
    
    private PointHandler points;
    private HighScoreDao testDao;
    
    public PointHandlerExceptionTest() {
        testDao = new TestExceptionHighScoreDao();
        points = new PointHandler(testDao);
    }
    
    @Test
    public void checkIfHighscoreReturnsFalseWhenException() {
        assertFalse(points.checkIfHighScore());
    }
    
    @Test
    public void getHighscoersDoesNotFlipWithException() {
        List<String> list = points.getHighscores();
        assertTrue(list.size() == 0);
    }
    
    @Test
    public void addnewHighscoreReturnsFalseWhenException() {
        assertFalse(points.addNewHighscore("name"));
    }
}

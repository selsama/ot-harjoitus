/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.*;
import snakegame.dao.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class sqlHighscoreTest {
    
    HighScoreDao dao;
    
    public sqlHighscoreTest() {
        dao = new SQLHighScoreDao("./test");
        try {
            int size = dao.getScores().size();
            for(int i = 0; i < size; i++) {
                dao.deleteLast();
            }
        } catch(Exception e) {
        }
    }
    
    @Test
    public void Test() {
        try {
            for(int i = 1; i <= 10; i++) {
                assertTrue("Everything until 10 is highscore", dao.checkIfHighscore(i));
                dao.add((i+ " points"), i);
            }
            assertTrue("New highest is highscore", dao.checkIfHighscore(20));
            dao.add("20 points", 20);
            dao.deleteLast();
            List<String> scores = dao.getScores();
            assertEquals("Deleting should, uknow, delete stuff", 10, scores.size());
            assertEquals("Highest score should be first even if it was added last",
                    "20 points\t\t20", scores.get(0));
            assertEquals("Smallest score should be last", "2 points\t\t2", scores.get(9));
            
        } catch (Exception e) {
            fail("SQL exception");
        }
    }
    
}

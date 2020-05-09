/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;

import snakegame.dao.*;
import java.util.*;
/**
 * game point managing
 */
public class PointHandler {
    
    private Integer points;
    private HighScoreDao highscores;
    
    /**
     * creates a new instance of PointHandler, that uses the given HighScoreDao
     * @param dao 
     */
    public PointHandler(HighScoreDao dao) {
        try {
            highscores = dao;
        } catch (Exception e) {
            System.out.println("Check your database connection");
        }
        points = 0;
    }
    
    /**
     * sets the point counter back to zero.
     */
    public void reset() {
        points = 0;
    }
    
    /**
     * returns the amount of points collected
     * @return points
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * increases the amount of points collected
     * @param points the amount of increase in points
     */
    public void addPoints(int points) {
        this.points += points;
    }    
    
    /**
     * checks if the current score is a new record
     * @return true if the score makes it to top ten, else false
     */
    public boolean checkIfHighScore() {
        boolean answer;
        try {
            answer = highscores.checkIfHighscore(points);
        } catch (Exception e) {
            System.out.println("error with highscore system: " + e.getMessage());
            answer = false;
        }
        return answer;
    }
    
    /**
     * returns a list of highscores
     * @return highscores
     */
    public List<String> getHighscores() {
        List<String> highscores;
        try {
            highscores = this.highscores.getScores();
        } catch (Exception e) {
            System.out.println("error with highscore system: " + e.getMessage());
            highscores = new ArrayList<>();
        }
        return highscores;
    }  
    /**
     * adds a new highscore to the list
     * @param name the name of the player
     * @return true, if the score was added successfully, else false
     */
    public boolean addNewHighscore(String name) {
        try {
            if (highscores.getScores().size() >= 10) {
                highscores.deleteLast();
            }
            highscores.add(name, points);
        } catch (Exception e) {
            System.out.println("error with highscore system: " + e.getMessage());
            return false;
        }
        return true;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.dao;

import java.util.*;

/**
 *
 * @author salmison
 */
public class TestHighScoreDao implements HighScoreDao<String, Integer> {
    
    private ArrayList<String> highscores;
    
    public TestHighScoreDao() {
        highscores = new ArrayList<>();
    }
    
    public void setHighScoreList(ArrayList<String> newlist) {
        this.highscores = newlist;
    }
    
    @Override
    public boolean checkIfHighscore(Integer score) {
        if (highscores.size() < 10) {
            return true;
        }
        String[] parts = highscores.get(9).split(";");
        if (score >= Integer.parseInt(parts[1])) {
            return true;
        }
        return false;
    }
    
    @Override
    public void add(String name, Integer score) {
        String s = name + ";" + score;
        if (highscores.size() < 10) {
            highscores.add(s);
        } else {
            this.deleteLast();
            highscores.add(s);
        }
    }
    
    @Override
    public void deleteLast() {
        highscores.remove(9);
    }
    
    @Override
    public List<String> getScores() {
        return highscores;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.dao;

import java.util.List;

/**
 *
 * @author salmison
 */
public class FileHighscoreDao implements HighScoreDao<String, Integer> {

    @Override
    public boolean checkIfHighscore(Integer score) {
        return true;
    }
    
    @Override
    public void add(String name, Integer score) {
        
    }
    
    @Override
    public void deleteLast() {
        
    }
    
    @Override
    public List<String> getScores() {
        return null;
    }    
    
}

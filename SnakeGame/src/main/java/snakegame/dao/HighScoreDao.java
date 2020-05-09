/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.dao;

import java.sql.*;
import java.util.*;

/**
 * Interface for high score saving system
 */
public interface HighScoreDao<T, K> {
    /**
     * checks if given score qualifies as high score
     * @param score to be checked
     * @return true, if it does, false if not
     * @throws SQLException 
     */
    boolean checkIfHighscore(K score) throws SQLException;
    /**
     * adds a new high score
     * @param name name of the player
     * @param score score
     * @throws SQLException 
     */
    void add(T name, K score) throws SQLException;
    /**
     * removes last high score
     * @throws SQLException 
     */
    void deleteLast() throws SQLException;
    /**
     * returns a list of high scores and adjacent names
     * @return List<T> of names and scores
     * @throws SQLException 
     */
    List<T> getScores() throws SQLException;
}

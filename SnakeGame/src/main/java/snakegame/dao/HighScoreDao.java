/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.dao;

import java.sql.*;
import java.util.*;

/**
 *
 * @author salmison
 */
public interface HighScoreDao<T, K> {
    boolean checkIfHighscore(K score) throws SQLException;
    void add(T name, K score) throws SQLException;
    void deleteLast() throws SQLException;
    List<T> getScores() throws SQLException;
}

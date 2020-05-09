/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.dao;

import java.sql.*;
import java.util.*;
/**
 * HighScoreDao for testing, throws SQLExceptions with everything
 */
public class TestExceptionHighScoreDao implements HighScoreDao<String, Integer> {
    
    private Connection connect;
    private Statement s;
    
    public TestExceptionHighScoreDao() {
        try {
            connect = DriverManager.getConnection("jdbc:h2:./test");
            s = connect.createStatement();
        } catch (SQLException e) {
        }
    }
    
    @Override
    public boolean checkIfHighscore(Integer score) throws SQLException {
        s.execute("SELECT true FROM Table");
        return true;
    }
    
    @Override
    public void add(String name, Integer score) throws SQLException {
        s.execute("INSERT INTO Table name VALUES(name)");
    }
    
    @Override
    public void deleteLast() throws SQLException {
        s.execute("DELETE FROM Table");
    }
    
    @Override
    public List<String> getScores() throws SQLException {
        s.execute("SELECT * FROM Table");
        return null;
    }
}

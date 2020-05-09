/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.dao;

import java.sql.*;
import java.util.*;

/**
 * SQL-using HighScoreDao for saving high scores
 */
public class SQLHighScoreDao implements HighScoreDao<String, Integer> {
    
    private Connection db;
    private Statement s;
    private ResultSet r;
    
    /**
     * creates a new SQLHighScoreDao to given location
     * @param data location of SQL database
     */
    public SQLHighScoreDao(String data) {
        try {
            db = DriverManager.getConnection("jdbc:h2:" + data);
            s = db.createStatement();
        } catch (SQLException e) {
        }
        try {
            s.execute("CREATE TABLE Scores (id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT, score INTEGER)");
        } catch (SQLException e) {
        }
    }
    
    @Override
    public boolean checkIfHighscore(Integer score) throws SQLException {
        PreparedStatement p = db.prepareStatement("SELECT COUNT(id) FROM Scores");
        r = p.executeQuery();
        r.next();
        int size = r.getInt(1);
        if (size < 10) {
            return true;
        }
        int previous = this.getLastScore();
        if (score >= previous) {
            return true;
        }
        return false;
    }
    
    @Override
    public void add(String name, Integer score) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Scores (name, score) VALUES (?,?)");
        p.setString(1, name);
        p.setInt(2, score);
        p.executeUpdate();
        s.execute("COMMIT");
    }
    
    @Override
    public void deleteLast() throws SQLException {
        int id = this.getLastId();
        s.execute("DELETE FROM Scores WHERE id=" + id);
    }
    
    @Override
    public List<String> getScores() throws SQLException {
        PreparedStatement p = db.prepareStatement("SELECT name, score FROM Scores ORDER BY score DESC");
        r = p.executeQuery();
        ArrayList<String> scores = new ArrayList<>();
        while (r.next()) {
            scores.add(r.getString("name") + "\t\t" + r.getInt("score"));
        }
        return scores;
    }
    
    private int getLastScore() throws SQLException {
        PreparedStatement p = db.prepareStatement("SELECT MIN(score) FROM Scores");
        r = p.executeQuery();
        r.next();
        int previous = r.getInt(1);
        return previous;
    }
    
    private int getLastId() throws SQLException {
        int score = this.getLastScore();
        PreparedStatement p = db.prepareStatement("SELECT id FROM Scores WHERE score=" + score);
        r = p.executeQuery();
        r.next();
        int id = r.getInt(1);
        return id;
    }
}

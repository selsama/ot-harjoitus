/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;

import java.util.*;
import javafx.scene.shape.*;
/**
 *
 * @author salmison
 */
public class GameHandler {
    public boolean paused;
    private List<Obstacle> obstacles;
    
    public GameHandler(int width, int height) {
        paused = true;
        obstacles = new ArrayList<>();
        for (int i = 0; i < width; i += 10) {
            obstacles.add(new Obstacle(i, 0));
            obstacles.add(new Obstacle(i, height - 10));
        }
        for (int i = 0; i < height; i += 10) {
            obstacles.add(new Obstacle(0, i));
            obstacles.add(new Obstacle(width - 10, i));
        }
    }
    
    public List getObstacles() {
        return obstacles;
    }
    
    public boolean gameOver(SnakeHead snake) {
        for (Obstacle obs: obstacles) {
            if (snake.crash(obs)) {
                return true;
            }
        }
        return false;
    }
    
    //managing whether game is on
    public void setOnPause() {
        paused = true;
    }
    
    public void setOffPause() {
        paused = false;
    }
    
    public void triggerPause() {
        paused = !paused;
    }
}

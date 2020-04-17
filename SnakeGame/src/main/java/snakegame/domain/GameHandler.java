/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;

import java.util.*;
import javafx.scene.input.KeyCode;
/**
 *
 * @author salmison
 */
public class GameHandler {
    public boolean paused;
    private boolean over;
    private List<Obstacle> obstacles;
    private SnakeHead snake;
    private int points;
    
    public GameHandler(int width, int height) {
        paused = true;
        over = false;
        obstacles = new ArrayList<>();
        for (int i = 0; i < width; i += 10) {
            obstacles.add(new Obstacle(i, 0));
            obstacles.add(new Obstacle(i, height - 10));
        }
        for (int i = 0; i < height; i += 10) {
            obstacles.add(new Obstacle(0, i));
            obstacles.add(new Obstacle(width - 10, i));
        }
        snake = new SnakeHead(width / 2, height / 2);
        points = 0;
    }
    
    public List getObstacles() {
        return obstacles;
    }
    
    public boolean gameOver() {
        for (Obstacle obs: obstacles) {
            if (snake.crash(obs)) {
                over = true;
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
    
    //Snake handling
    public SnakeHead getSnake() {
        return snake;
    }
    
    public void moveSnake() {
        snake.move();
    }
    
    public boolean handleKeyPressed(KeyCode code) {
        if (!over) {
            if (code == KeyCode.LEFT) {
                snake.turnLeft();
            }
            if (code == KeyCode.RIGHT) {
                snake.turnRight();
            }
            if (code == KeyCode.UP) {
                snake.turnUp();
            }
            if (code == KeyCode.DOWN) {
                snake.turnDown();
            }
        }
        if (code == KeyCode.P) {
            this.triggerPause();
            return false;
        }
        return true;
    }
    
    //points
    public int getPoints() {
        return points;
    }
    
    public void addPoints() {
        points++;
    }
    
}

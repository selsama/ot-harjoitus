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
    private HashMap<KeyCode, String> snakeControls;
    private int points;
    
    public GameHandler(int width, int height) {
        paused = true;
        over = false;
        obstacles = new ArrayList<>();
        this.makeWalls(width, height);
        snake = new SnakeHead(width / 2, height / 2);
        snakeControls = new HashMap<>();
        this.setSnakeControls(snakeControls, KeyCode.UP, KeyCode.RIGHT, KeyCode.DOWN, KeyCode.LEFT);
        points = 0;
    }
    
    private void makeWalls(int width, int height) {
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
    
    public void setSnakeControls(HashMap<KeyCode, String> controls, KeyCode up, KeyCode right, KeyCode down, KeyCode left) {
        controls.put(up, "UP");
        controls.put(right, "RIGHT");
        controls.put(down, "DOWN");
        controls.put(left, "LEFT");
    }
    
    public void moveSnake() {
        snake.move();
    }
    
    public void turnSnake(String dir) {
        if (dir.equals("UP")) {
            snake.turnUp();
        }
        if (dir.equals("RIGHT")) {
            snake.turnRight();
        }
        if (dir.equals("DOWN")) {
            snake.turnDown();
        }
        if (dir.equals("LEFT")) {
            snake.turnLeft();
        }
    }
    
    public boolean handleKeyPressed(KeyCode code) {
        if (!over) {
            if (snakeControls.containsKey(code)) {
                this.turnSnake(snakeControls.get(code));
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
    
    public void addPoints(int points) {
        this.points += points;
    }
    
}

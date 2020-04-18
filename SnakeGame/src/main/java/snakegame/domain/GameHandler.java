/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;

import java.util.*;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.*;
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
    private HashMap<Integer, ArrayList<SnakeTail>> tailParts;
    private int points;
    
    //game initializing
    public GameHandler(int width, int height) {
        paused = true;
        over = false;
        obstacles = new ArrayList<>();
        this.makeWalls(width, height);
        this.makeSnake(width / 2, height / 2);
        points = 0;
    }
    
    private void makeSnake(int x, int y) {
        snake = new SnakeHead(x, y);
        snakeControls = new HashMap<>();
        this.setSnakeControls(snakeControls, KeyCode.UP, KeyCode.RIGHT, KeyCode.DOWN, KeyCode.LEFT);
        tailParts = new HashMap<>();
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
    
    //obstacles
    public List getObstacles() {
        return obstacles;
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
    
    public Shape moveSnake() {
        snake.move();
        SnakeTail tail = snake.leaveTail();
        if(!tailParts.containsKey(tail.getY())) {
            tailParts.put(tail.getY(), new ArrayList<>());   
        }
        tailParts.get(tail.getY()).add(tail);
        return tail.getShape();
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
    
    // general game mechanics
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
  
    public boolean gameOver() {
        for (Obstacle obs: obstacles) {
            if (snake.crash(obs)) {
                over = true;
                return true;
            }
        }
        int snakePositionY = (int) snake.getShape().getY();
        if(tailParts.containsKey(snakePositionY)) {
            for (SnakeTail tail : tailParts.get(snakePositionY)) {
                if (snake.crash(tail)) {
                    over = true;
                    return true;
                }
            }
        }
        return false;
    }
    
    //points
    public int getPoints() {
        return points;
    }
    
    public void addPoints(int points) {
        this.points += points;
    }
    
}

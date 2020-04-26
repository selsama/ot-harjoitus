/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;

import java.util.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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
    private HashMap<KeyCode, Direction> snakeControls;
    private ArrayList<ArrayList<SnakeTail>> tailParts;
    private int points;
    private int speed;
    
    //game initializing
    public GameHandler(int width, int height) {
        paused = true;
        over = false;
        obstacles = new ArrayList<>();
        this.makeWalls(width, height);
        this.makeSnake(width / 2, height / 2, height, Color.MEDIUMVIOLETRED);
        points = 0;
        speed = 40000000;
    }
    
    public void newGame(int width, int height) {
        paused = true;
        over = false;
        this.makeSnake(width / 2, height / 2, height, snake.getColor());
        points = 0;
    }
    
    private void makeSnake(int x, int y, int height, Color color) {
        snake = new SnakeHead(x, y);
        snake.setColor(color);
        snakeControls = new HashMap<>();
        this.setSnakeControls(snakeControls, KeyCode.UP, KeyCode.RIGHT, KeyCode.DOWN, KeyCode.LEFT);
        tailParts = new ArrayList<>();
        for (int i = 0; i <= height / 10; i++) {
            tailParts.add(new ArrayList<>());
        }
    }
    
    private void makeWalls(int width, int height) {
        obstacles.add(new Obstacle(0, 0, width, 10));
        obstacles.add(new Obstacle(0, height - 10, width, 10));
        obstacles.add(new Obstacle(0, 0, 10, height));
        obstacles.add(new Obstacle(width - 10, 0, 10, height));
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
    
    // game difficulty setting
    public void setSpeed(int d) {
       this.speed = d;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    //Snake handling
    public SnakeHead getSnake() {
        return snake;
    }
    
    public void setSnakeColor(Color color) {
        snake.setColor(color);
    }
    
    public void setSnakeControls(HashMap<KeyCode, Direction> controls, KeyCode up, KeyCode right, KeyCode down, KeyCode left) {
        controls.put(up, Direction.UP);
        controls.put(right, Direction.RIGHT);
        controls.put(down, Direction.DOWN);
        controls.put(left, Direction.LEFT);
    }
    
    public Shape moveSnake() {
        snake.move();
        SnakeTail tail = snake.leaveTail();
        tailParts.get(tail.getY() / 10).add(tail);
        return tail.getShape();
    }
    
    public void turnSnake(Direction dir) {
        if (dir == Direction.UP) {
            snake.turnUp();
        }
        if (dir == Direction.RIGHT) {
            snake.turnRight();
        }
        if (dir == Direction.DOWN) {
            snake.turnDown();
        }
        if (dir == Direction.LEFT) {
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
        for (SnakeTail tail: tailParts.get(snakePositionY / 10)) {
            if (snake.crash(tail)) {
                over = true;
                return true;
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

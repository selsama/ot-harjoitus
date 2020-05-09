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
    private boolean paused;
    private boolean started;
    private boolean over;
    private int width;
    private int height;
    private List<Obstacle> obstacles;
    private SnakeHead snake;
    private HashMap<KeyCode, Direction> snakeControls;
    private ArrayList<ArrayList<SnakeTail>> tailParts;
    private int points;
    private int speed;
    
    //game initializing
    
    /**
     * Creates a new GameHandler instance for the given screen size
     * @param width the width of the game area
     * @param height the height of the game area
     */
    public GameHandler(int width, int height) {
        paused = true;
        started = false;
        over = false;
        this.width = width;
        this.height = height;
        obstacles = new ArrayList<>();
        this.makeWalls();
        this.makeSnake(width / 2, height / 2, Color.MEDIUMVIOLETRED);
        points = 0;
        speed = 40000000;
    }
    
    /**
     * Sets the GameHandler ready for a new game
     */
    public void newGame() {
        paused = true;
        started = false;
        over = false;
        this.makeSnake(width / 2, height / 2, snake.getColor());
        points = 0;
    }
    
    /**
     * Creates and positions a new snake for the game.
     * 
     * Creates SnakeHead-instance, sets its controls and initializes the list
     * SnakeTail parts.
     * 
     * @param x the x-coordinate of the SnakeHead instance
     * @param y the y-coordinate of the SnakeHEad instance
     * @param color the Color of the created SnakeHead
     */
    private void makeSnake(int x, int y, Color color) {
        snake = new SnakeHead(x, y);
        snake.setColor(color);
        snakeControls = new HashMap<>();
        this.setSnakeControls(snakeControls, KeyCode.UP, KeyCode.RIGHT, KeyCode.DOWN, KeyCode.LEFT);
        tailParts = new ArrayList<>();
        for (int i = 0; i <= height / 10; i++) {
            tailParts.add(new ArrayList<>());
        }
    }
    
    /**
     * Creates walls out of Obstacles around the game area
     */
    private void makeWalls() {
        obstacles.add(new Obstacle(0, 0, width, 10));
        obstacles.add(new Obstacle(0, height - 10, width, 11));
        obstacles.add(new Obstacle(0, 0, 10, height));
        obstacles.add(new Obstacle(width - 10, 0, 11, height));
    }
    
    //obstacles
    
    /**
     * returns a list of Obstacles in the game
     * @return List of obstacles
     */
    public List getObstacles() {
        return obstacles;
    }  
    
    //managing whether game is on
    
    /**
     * sets the game on pause
     */
    public void setOnPause() {
        paused = true;
    }
    
    /**
     * sets the game off pause
     */
    public void setOffPause() {
        paused = false;
    }
    
    /**
     * triggers pause
     */
    public void triggerPause() {
        paused = !paused;
    }
    
    /**
     * tells whether the game is paused
     * @return true, if the game is on pause, otherwise false 
     */
    public boolean onPause() {
        return paused;
    }
    
    /**
     * start the game
     */
    public void start() {
        started = true;
        paused = false;
    }
    
    // game difficulty setting
    
    /**
     * sets the variable that refines the speed of the game
     * @param d speed
     */
    public void setSpeed(int d) {
        this.speed = d;
    }
    
    /**
     * returns the speed variable
     * @return speed
     */
    public int getSpeed() {
        return this.speed;
    }
    
    //Snake handling
    
    /**
     * returns the SnakeHead instance of the game
     * @return SnakeHead
     */
    public SnakeHead getSnake() {
        return snake;
    }
    
    /**
     * sets the color of the SnakeHead instance in the game
     * @param color the Color that the SnakeHead should be
     */
    public void setSnakeColor(Color color) {
        snake.setColor(color);
    }
    
    /**
     * sets the controls for the snake
     * @param controls where the controls are saved
     * @param up key that turns the SnakeHead up
     * @param right key that turns it right
     * @param down key that turns it down
     * @param left key that turns it left
     */
    private void setSnakeControls(HashMap<KeyCode, Direction> controls, KeyCode up, KeyCode right, KeyCode down, KeyCode left) {
        controls.put(up, Direction.UP);
        controls.put(right, Direction.RIGHT);
        controls.put(down, Direction.DOWN);
        controls.put(left, Direction.LEFT);
    }
    
    /**
     * Moves the SnakeHead instance, which then leaves a TailPart instance behind it,
     * which is returned.
     * @return the new TailPart instance
     */
    public Shape moveSnake() {
        snake.move();
        SnakeTail tail = snake.leaveTail();
        tailParts.get(tail.getY() / 10).add(tail);
        return tail.getShape();
    }
    
    /**
     * turn the SnakeHead to the given Direction
     * @param dir Direction to which the SnakeHead should be headed
     */
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
    
    /**
     * Gets the KeyCode of a pressed key and defines what to do with it. 
     * @param code KeyCode of the key pressed
     * @return True, if the game was not set on pause
     */
    public boolean handleKeyPressed(KeyCode code) {
        if (!started) {
            if (snakeControls.containsKey(code)) {
                snake.setDirection(snakeControls.get(code));
                this.start();
            }
        } else if (!over) {
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
  
    /**
     * Checks if the game is over, which happens if the snake crashes
     * @return true, if the snake has crashed into something, else false
     */
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
    
}

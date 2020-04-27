/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
/**
 *
 * @author salmison
 */
public class SnakeHead implements Crashable {
    
    private Rectangle head;
    private Direction dir;
    private Color color;
    
    /**
     * Create new SnakeHead instance
     * 
     * @param x x-coordinate of the left upper corner of the created SnakeHead
     * @param y y-coordinate of the right upper corner of the created SnakeHead
     */
    public SnakeHead(double x, double y) {
        this.head = new Rectangle(x, y, 10, 10);
        this.dir = Direction.UP;
        this.color = Color.MEDIUMVIOLETRED;
    }
    /**
     * 
     * @return Shape (Rectangle) of this SnakeHead instance
     */
    @Override
    public Rectangle getShape() {
        return head;
    }
    
    /**
     * Return the Color of this SnakeHead
     * 
     * @return Color of this SnakeHead
     */
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Set the Color of this SnakeHead
     * 
     * @param color instance of Color 
     */
    public void setColor(Color color) {
        this.head.setFill(color);
        this.color = color;
    }
    /**
     * Get the Direction that tells where the SnakeHead is headed
     * 
     * @return Direction
     */
    public Direction getDirection() {
        return dir;
    }
    
    /**
     * Set the Direction to which the SnakeHead is headed
     * 
     * @param dir instance of Direction
     */
    public void setDirection(Direction dir) {
        this.dir = dir; 
    }
    
    /**
     * Turn the SnakeHead left - set the Direction to LEFT.
     */
    public void turnLeft() {
        if (dir != Direction.RIGHT) {
            dir = Direction.LEFT;
        }
    }

    /**
     * Turn the SnakeHead right - set the Direction to RIGHT.
     */
    public void turnRight() {
        if (dir != Direction.LEFT) {
            dir = Direction.RIGHT;
        }
    }
    
    /**
     * Turn the SnakeHead up - set the Direction to UP.
     */
    public void turnUp() {
        if (dir != Direction.DOWN) {
            dir = Direction.UP;
        }
    }
    
    /**
     * Turn the snake down - set the Direction to DOWN.
     */
    public void turnDown() {
        if (dir != Direction.UP) {
            dir = Direction.DOWN;
        }
    }
    
    /**
     * Move the SnakeHead 10 pixels to the direction it is headed.
     */
    public void move() {
        if (dir == Direction.UP) {
            this.head.setY(head.getY() - 10);
        } else if (dir == Direction.RIGHT) {
            this.head.setX(head.getX() + 10);
        } else if (dir == Direction.DOWN) {
            this.head.setY(head.getY() + 10);
        } else if (dir == Direction.LEFT) {
            this.head.setX(head.getX() - 10);
        }
    }
    
    /**
     * Make a new SnakeTail instance to the position the SnakeHead was at before moving.
     * 
     * @return the new SnakeTail instance.
     */
    public SnakeTail leaveTail() {
        SnakeTail tail = null;
        if (dir == Direction.UP) {
            tail = new SnakeTail(head.getX(), head.getY() + 10, color);
        } else if (dir == Direction.RIGHT) {
            tail = new SnakeTail(head.getX() - 10, head.getY(), color);
        } else if (dir == Direction.DOWN) {
            tail = new SnakeTail(head.getX(), head.getY() - 10, color);
        } else if (dir == Direction.LEFT) {
            tail = new SnakeTail(head.getX() + 10, head.getY(), color);
        }
        return tail;
    }
    
    /**
     * Check if the SnakeHead instance crashes into other Crashable.
     * 
     * @param other instance of Crashable that is to be checked
     * 
     * @return true, if their shapes overlap, otherwise false
     */
    public boolean crash(Crashable other) {
        Shape collision = Shape.intersect(head, other.getShape());
        return collision.getBoundsInLocal().getWidth() != -1;
    }
}

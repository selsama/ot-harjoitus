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
    
    public SnakeHead(double x, double y) {
        this.head = new Rectangle(x, y, 10, 10);
        this.dir = Direction.UP;
    }
    
    public Rectangle getShape() {
        return head;
    }
    
    public void setColor(Color color) {
        this.head.setFill(color);
    }
    
    public void setDirection(Direction dir) {
        this.dir = dir; 
    }
    
    public Direction getDirection() {
        return dir;
    }
    
    public void turnLeft() {
        if (dir != Direction.RIGHT) {
            dir = Direction.LEFT;
        }
    }
    
    public void turnRight() {
        if (dir != Direction.LEFT) {
            dir = Direction.RIGHT;
        }
    }
    
    public void turnUp() {
        if (dir != Direction.DOWN) {
            dir = Direction.UP;
        }
    }
    
    public void turnDown() {
        if (dir != Direction.UP) {
            dir = Direction.DOWN;
        }
    }
    
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
    
    public SnakeTail leaveTail() {
        SnakeTail tail = null;
        if (dir == Direction.UP) {
            tail = new SnakeTail(head.getX(), head.getY() + 10, head.getFill());
        } else if (dir == Direction.RIGHT) {
            tail = new SnakeTail(head.getX() - 10, head.getY(), head.getFill());
        } else if (dir == Direction.DOWN) {
            tail = new SnakeTail(head.getX(), head.getY() - 10, head.getFill());
        } else if (dir == Direction.LEFT) {
            tail = new SnakeTail(head.getX() + 10, head.getY(), head.getFill());
        }
        return tail;
    }
    
    public boolean crash(Crashable other) {
        Shape collision = Shape.intersect(head, other.getShape());
        return collision.getBoundsInLocal().getWidth() != -1;
    }
}

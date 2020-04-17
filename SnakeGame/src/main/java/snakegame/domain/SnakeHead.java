/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;
import javafx.scene.shape.*;
/**
 *
 * @author salmison
 */
public class SnakeHead implements Crashable {
    
    private Rectangle head;
    
    public SnakeHead(double x, double y) {
        this.head = new Rectangle(x, y, 10, 20);
    }
    
    public Rectangle getShape() {
        return head;
    }
    
    public void turnLeft() {
        if (head.getRotate() != 90) {
            head.setRotate(-90);
        }
    }
    
    public void turnRight() {
        if (head.getRotate() != -90) {
            head.setRotate(90);
        }
    }
    
    public void turnUp() {
        if (head.getRotate() != 180) {
            head.setRotate(0);
        }
    }
    
    public void turnDown() {
        if (head.getRotate() != 0) {
            head.setRotate(180);
        }
    }
    
    public void move() {
        double dir = head.getRotate();
        if (dir == 0) {
            this.head.setY(head.getY() - 0.5);
        }
        if (dir == 90) {
            this.head.setX(head.getX() + 0.5);
        }
        if (dir == 180) {
            this.head.setY(head.getY() + 0.5);
        }
        if (dir == -90) {
            this.head.setX(head.getX() - 0.5);
        }
    }
    

    public boolean crash(Crashable other) {
        Shape collision = Shape.intersect(head, other.getShape());
        return collision.getBoundsInLocal().getWidth() != -1;
    }
}

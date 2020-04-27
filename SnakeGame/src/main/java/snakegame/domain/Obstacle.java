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
public class Obstacle implements Crashable {
    
    private Rectangle obstacle;
    
    /**
     * Create a new Obstacle in size 10x10 pixels in the given location
     * @param x x-coordinate of the upper left corner of the Obstacle
     * @param y y-coordinate of the upper left corner of the Obstacle
     */
    public Obstacle(double x, double y) {
        this.obstacle = new Rectangle(x, y, 10, 10);
    }
    
    /**
     * Create a new Obstacle in given size in the given location
     * @param x x-coordinate of the upper left corner of the Obstacle
     * @param y y-coordinate of the upper left corner of the Obstacle
     * @param width width of the Obstacle
     * @param height height of the Obstacle
     */
    public Obstacle(double x, double y, int width, int height) {
        this.obstacle = new Rectangle(x, y, width, height);
    }
    
    /**
     * Change the positioning of this Obstacle to the given point
     * @param x the x-coordinate of the upper left corner
     * @param y the y-coordinate of the upper left corner
     */
    public void setPosition(int x, int y) {
        obstacle.setTranslateX(x);
        obstacle.setTranslateY(y);
    }
    
    /**
     * Returns the Shape of this obstacle
     * @return Shape of this obstacle
     */
    @Override
    public Shape getShape() {
        return obstacle;
    }
    
}

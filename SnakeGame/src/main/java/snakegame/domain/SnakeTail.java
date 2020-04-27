/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
/**
 *
 * @author salmison
 */
public class SnakeTail implements Crashable {
    
    private Rectangle tailPart;
    
    /**
     * Create a new instance of TailPart to given location with given Color.
     * @param x x-coordinate of the left upper corner of the TailPart
     * @param y y-coordinate of the upper left corner of the TailPart
     * @param color Color that defines the fill of the TailPart
     */
    public SnakeTail(double x, double y, Paint color) {
        tailPart = new Rectangle(x, y, 10, 10);
        tailPart.setFill(color);
    }
    
    /**
     * Returns the Rectangle of which the TailPart consists
     * @return the Rectangle of this TailPart instance
     */
    @Override
    public Rectangle getShape() {
        return tailPart;
    }
    
    /**
     * 
     * @return the y-coordinate of the upper left corner of this SnakeTail
     */
    public int getY() {
        return (int) tailPart.getY();
    }
}

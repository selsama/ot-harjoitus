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
public class SnakeTail implements Crashable {
    
    private Rectangle tailPart;
    
    public SnakeTail(double x, double y, String dir) {
        if(dir.equals("HOR")) {
            tailPart = new Rectangle(x, y, 1, 10);
        }
        else if(dir.equals("VER")) {
            tailPart = new Rectangle(x, y, 10, 1);
        }
    }
    
    @Override
    public Shape getShape() {
        return tailPart;
    }
    
    public int getY() {
        return (int) tailPart.getY();
    }
}

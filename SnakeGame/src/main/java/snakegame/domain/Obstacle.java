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
    
    public Obstacle(int x, int y) {
        this.obstacle = new Rectangle(x, y, 10, 10);
    }
    
    public Shape getShape(){
        return obstacle;
    }
    
}

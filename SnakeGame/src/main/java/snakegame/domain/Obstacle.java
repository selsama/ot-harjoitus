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
    
    public Obstacle(double x, double y) {
        this.obstacle = new Rectangle(x, y, 10, 10);
    }
    
    public Obstacle(double x, double y, int width, int height) {
        this.obstacle = new Rectangle(x, y, width, height);
    }
    
    public void setPosition(int x, int y) {
        obstacle.setTranslateX(x);
        obstacle.setTranslateY(y);
    }
    
    @Override
    public Shape getShape() {
        return obstacle;
    }
    
}

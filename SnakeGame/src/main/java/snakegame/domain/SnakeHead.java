/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author salmison
 */
public class SnakeHead implements Crashable {
    
    private Rectangle head;
    
    public SnakeHead(double x, double y){
        this.head= new Rectangle(x,y,10,10);
    }
    
    public Rectangle getHead(){
        return head;
    }
    
    
    @Override
    public void crash(){
        //TODO how
    }
}

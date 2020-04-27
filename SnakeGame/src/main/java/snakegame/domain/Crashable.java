/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;
import javafx.scene.shape.*;
/**
 * Interface for instances that can crash into each other
 */
public interface Crashable { 
    
    /**
     * returns the Shape of the Crashable
     * @return Shape shape of the instance
     */
    Shape getShape();
    
}

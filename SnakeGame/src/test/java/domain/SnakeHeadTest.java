/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snakegame.domain.SnakeHead;

/**
 *
 * @author salmison
 */
public class SnakeHeadTest {
    
    SnakeHead head;

    @Before
    public void setUp() {
        head = new SnakeHead(10,10);
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void snakeHeadTurnLeftWorks(){
        head.turnLeft();
        double end = head.getHead().getRotate();
        assertEquals(-90,end,0.01);
    }
    
    @Test
    public void snakeHeadTurnLeftWontTurnIfHeadedRight(){
        head.getHead().setRotate(90);
        head.turnLeft();
        assertEquals(90,head.getHead().getRotate(),0.01);
    }
    
    @Test
    public void snakeHeadTurnRightWorks(){
        head.turnRight();
        double end = head.getHead().getRotate();
        assertEquals(90,end,0.01);
    }
    
    @Test
    public void snakeHeadTurnRightWontTurnIfHeadedLeft(){
        head.getHead().setRotate(-90);
        head.turnRight();
        assertEquals(-90,head.getHead().getRotate(),0.01);
    }
    
    @Test
    public void snakeHeadTurnDownWorks(){
        head.getHead().setRotate(90);
        head.turnDown();
        double end = head.getHead().getRotate();
        assertEquals(180,end,0.01);
    }
    
    @Test
    public void snakeHeadTurnDownWontTurnIfHeadedUp(){
        head.getHead().setRotate(0);
        head.turnDown();
        assertEquals(-0,head.getHead().getRotate(),0.01);
    }

    @Test
    public void snakeHeadTurnUpWorks(){
        head.getHead().setRotate(90);
        head.turnUp();
        assertEquals(0,head.getHead().getRotate(),0.01);
    }
    
    @Test
    public void snakeHeadTurnUpWontTurnIfHeadedDown(){
        head.getHead().setRotate(180);
        head.turnUp();
        assertEquals(180,head.getHead().getRotate(),0.01);
    }
    
//     @Test
//     public void snakeHeadBeginsInMiddle() {
//     }
//     
//     @Test
//     public void snakeHeadDoesNotMoveRightAway(){
//         
//     }
//     These are higher level tests, i think
//     @Test
//     public void snakeHeadMovesWhenKeyIsPressed(){
//         
//     }
}

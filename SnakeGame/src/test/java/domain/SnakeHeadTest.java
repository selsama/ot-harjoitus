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
        double end = head.getShape().getRotate();
        assertEquals(-90,end,0.01);
    }
    
    @Test
    public void snakeHeadTurnLeftWontTurnIfHeadedRight(){
        head.getShape().setRotate(90);
        head.turnLeft();
        assertEquals(90,head.getShape().getRotate(),0.01);
    }
    
    @Test
    public void snakeHeadTurnRightWorks(){
        head.turnRight();
        double end = head.getShape().getRotate();
        assertEquals(90,end,0.01);
    }
    
    @Test
    public void snakeHeadTurnRightWontTurnIfHeadedLeft(){
        head.getShape().setRotate(-90);
        head.turnRight();
        assertEquals(-90,head.getShape().getRotate(),0.01);
    }
    
    @Test
    public void snakeHeadTurnDownWorks(){
        head.getShape().setRotate(90);
        head.turnDown();
        double end = head.getShape().getRotate();
        assertEquals(180,end,0.01);
    }
    
    @Test
    public void snakeHeadTurnDownWontTurnIfHeadedUp(){
        head.getShape().setRotate(0);
        head.turnDown();
        assertEquals(-0,head.getShape().getRotate(),0.01);
    }

    @Test
    public void snakeHeadTurnUpWorks(){
        head.getShape().setRotate(90);
        head.turnUp();
        assertEquals(0,head.getShape().getRotate(),0.01);
    }
    
    @Test
    public void snakeHeadTurnUpWontTurnIfHeadedDown(){
        head.getShape().setRotate(180);
        head.turnUp();
        assertEquals(180,head.getShape().getRotate(),0.01);
    }
    
    @Test
    public void snakeHeadMoves(){
        double startX=head.getShape().getTranslateX();
        double startY=head.getShape().getTranslateY();
        head.move();
        assertTrue(startX!=head.getShape().getTranslateX()||startY!=head.getShape().getTranslateY());
    }
    
    @Test
    public void snakeHeadMovesRight(){
        head.getShape().setRotate(90);
        double start = head.getShape().getTranslateX();
        head.move();
        assertTrue(start<head.getShape().getTranslateX());
    }
    
    @Test
    public void snakeHeadMovesLeft(){
        head.getShape().setRotate(-90);
        double start = head.getShape().getTranslateX();
        head.move();
        assertTrue(start>head.getShape().getTranslateX());
    }
    
    @Test
    public void snakeHeadMovesUp(){
        head.getShape().setRotate(0);
        double start = head.getShape().getTranslateY();
        head.move();
        assertTrue(start>head.getShape().getTranslateY());
    }
    
    @Test
    public void snakeHeadMovesDown(){
        head.getShape().setRotate(180);
        double start = head.getShape().getTranslateY();
        head.move();
        assertTrue(start<head.getShape().getTranslateY());
    }
    
    @Test
    public void snakeHeadDoesNotMoveVerticallyWhenGoingHorizontally(){
        double start = head.getShape().getTranslateY();
        head.getShape().setRotate(90);
        head.move();
        head.getShape().setRotate(-90);
        head.move();
        head.move();
        assertEquals(start,head.getShape().getTranslateY(),0.01);
    }
    
    @Test
    public void snakeHeadDoesNotMoveHorizontallyWhenGoingVertically(){
        double start = head.getShape().getTranslateX();
        head.getShape().setRotate(0);
        head.move();
        head.getShape().setRotate(180);
        head.move();
        head.move();
        assertEquals(start,head.getShape().getTranslateX(),0.01);
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

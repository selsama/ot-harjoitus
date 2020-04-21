/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.scene.control.skin.TextInputControlSkin;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snakegame.domain.*;

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
        assertTrue("SnakeHead doesn't turn left", Direction.LEFT == head.getDirection());
        head.setDirection(Direction.RIGHT);
        head.turnLeft();
        assertFalse("SnakeHead shouldn't turn left when headed right", Direction.LEFT == head.getDirection());
    }
    
    @Test
    public void snakeHeadTurnRightWorks(){
        head.turnRight();
        assertTrue("SnakeHead doesn't turn right", Direction.RIGHT == head.getDirection());
        head.setDirection(Direction.LEFT);
        head.turnRight();
        assertFalse("SnakeHead shouldn't turn right when headed left", Direction.RIGHT == head.getDirection());
    }
    
    
    @Test
    public void snakeHeadTurnDownWorks(){
        head.turnDown();
        assertFalse("SnakeHead should not turn down when headed up", Direction.DOWN == head.getDirection());
        head.setDirection(Direction.RIGHT);
        head.turnDown();
        assertTrue("SnakeHead does not turn down", Direction.DOWN == head.getDirection());
    }

    @Test
    public void snakeHeadTurnUpWorks(){
        head.setDirection(Direction.RIGHT);
        head.turnUp();
        assertTrue("SnakeHead doesn't turn up", Direction.UP == head.getDirection());
        head.setDirection(Direction.DOWN);
        head.turnUp();
        assertFalse("SnakeHead shouldn't turn up when headed down", Direction.UP == head.getDirection());
    }
    
    @Test
    public void snakeHeadMoves(){
        double startX=head.getShape().getX();
        double startY=head.getShape().getY();
        head.move();
        assertTrue(startX!=head.getShape().getX() || startY!=head.getShape().getY());
    }
    
    @Test
    public void snakeHeadMovesRight(){
        head.setDirection(Direction.RIGHT);
        double start = head.getShape().getX();
        head.move();
        assertTrue(start<head.getShape().getX());
    }
    
    @Test
    public void snakeHeadMovesLeft(){
        head.setDirection(Direction.LEFT);
        double start = head.getShape().getX();
        head.move();
        assertTrue(start>head.getShape().getX());
    }
    
    @Test
    public void snakeHeadMovesUp(){
        head.setDirection(Direction.UP);
        double start = head.getShape().getY();
        head.move();
        assertTrue(start>head.getShape().getY());
    }
    
    @Test
    public void snakeHeadMovesDown(){
        head.setDirection(Direction.DOWN);
        double start = head.getShape().getY();
        head.move();
        assertTrue(start<head.getShape().getY());
    }
    
    @Test
    public void snakeHeadDoesNotMoveVerticallyWhenGoingHorizontally(){
        double start = head.getShape().getY();
        head.setDirection(Direction.RIGHT);
        head.move();
        head.setDirection(Direction.LEFT);
        head.move();
        head.move();
        assertEquals(start,head.getShape().getY(),0.01);
    }
    
    @Test
    public void snakeHeadDoesNotMoveHorizontallyWhenGoingVertically(){
        double start = head.getShape().getX();
        head.setDirection(Direction.DOWN);
        head.move();
        head.setDirection(Direction.UP);
        head.move();
        head.move();
        assertEquals(start,head.getShape().getX(),0.01);
    }    

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

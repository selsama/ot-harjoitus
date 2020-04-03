/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.ui;

import snakegame.domain.*;

import java.util.*;
import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SnakeUi extends Application {
    
    private Scene gameScene;
    private Scene startScene;
    private gameHandler game;
    public int SCENEWIDTH;
    public int SCENEHEIGHT;
    
    @Override
    public void init() {
        SCENEWIDTH = 600;
        SCENEHEIGHT = 600;
        
        game = new gameHandler(SCENEWIDTH, SCENEHEIGHT);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // create startScene
        BorderPane startPane = new BorderPane();
        Button newGameButton = new Button("new game");
        Button settingsButton = new Button("settings");
        Button highScoresButton = new Button("see highscores");
        
        newGameButton.setOnAction(e->{
            primaryStage.setScene(gameScene);
        }); 
        
        VBox startBox = new VBox();
        startBox.getChildren().addAll(newGameButton, settingsButton, highScoresButton);

        startPane.setCenter(startBox);
        
        startScene = new Scene(startPane, SCENEWIDTH, SCENEHEIGHT);
        
        // create gameScene
        Pane gamePane = new Pane();
        
        List<Obstacle> obstacles = game.getObstacles();
        for(Obstacle obs: obstacles) {
            gamePane.getChildren().add(obs.getShape());
        }
        
        SnakeHead snake = new SnakeHead(SCENEWIDTH / 2, SCENEHEIGHT / 2);
        gamePane.getChildren().addAll(snake.getShape());
        
        gameScene = new Scene(gamePane, SCENEWIDTH, SCENEHEIGHT);
        
        gameScene.setOnKeyPressed(event ->{
            if(event.getCode()== KeyCode.LEFT){
                snake.turnLeft();
            }
            if(event.getCode()==KeyCode.RIGHT){
                snake.turnRight();
            }
            if(event.getCode()==KeyCode.UP){
                snake.turnUp();
            }
            if(event.getCode()==KeyCode.DOWN){
                snake.turnDown();
            }
            if(event.getCode() == KeyCode.P){
                game.triggerPause();
            }
            else{
                game.setOffPause();
            }
        });
        
        
        new AnimationTimer(){
            @Override
            public void handle(long moment){
                if(!game.PAUSED){
                    snake.move();
                }
                if(game.gameOver(snake)) {
                    stop();
                }
            }
        }.start();
        
        // setup primary scene
        primaryStage.setTitle("SNAKES");
        primaryStage.setScene(startScene);
        primaryStage.show();
    }
    
    public static void main(String[] args)  {
        launch(args);
    }
}

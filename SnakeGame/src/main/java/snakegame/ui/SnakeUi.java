/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.ui;

import snakegame.domain.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SnakeUi extends Application {
    
    private Scene gameScene;
    private Scene startScene;
    
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
        
        startScene = new Scene(startPane,600,600);
        
        // create gameScene
        Pane gamePane = new Pane();
        
        SnakeHead snake = new SnakeHead(300,300);
        gamePane.getChildren().add(snake.getHead());
        gameScene = new Scene(gamePane,600,600);
        
        // setup primary scene
        primaryStage.setTitle("SNAKES");
        primaryStage.setScene(startScene);
        primaryStage.show();
    }
    
    public static void main(String[] args)  {
        launch(args);
    }
}

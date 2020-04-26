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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SnakeUi extends Application {
    
    private Stage stage;
    private Scene gameScene;
    private Scene menuScene;
    private Scene settingScene;
    private Scene gameOverScene;
    private GameHandler game;
    public int SCENEWIDTH;
    public int SCENEHEIGHT;
    
    @Override
    public void init() {
        SCENEWIDTH = 600;
        SCENEHEIGHT = 600;
        
        this.createMenuScene();
        this.createSettingScene();
        
        game = new GameHandler(SCENEWIDTH, SCENEHEIGHT);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        // create menuScene
//        BorderPane menuPane = new BorderPane();
//        Button newGameButton = new Button("new game");
//        Button settingsButton = new Button("settings");
//        Button highScoresButton = new Button("see highscores");
//        
//        newGameButton.setOnAction(e->{
//            primaryStage.setScene(gameScene);
//        }); 
//        settingsButton.setOnAction(e->{
//           primaryStage.setScene(settingScene); 
//        });
//        
//        VBox menuBox = new VBox();
//        menuBox.getChildren().addAll(newGameButton, settingsButton, highScoresButton);
//
//        menuPane.setCenter(menuBox);
//        
//        menuScene = new Scene(menuPane, SCENEWIDTH, SCENEHEIGHT);
        
        // create gameScene
        Pane gamePane = new Pane();
        
        Label pointCounter = new Label("Points: "+game.getPoints());
        pointCounter.setTranslateY(10);
        pointCounter.setTranslateX(10);
        
        List<Obstacle> obstacles = game.getObstacles();
        for(Obstacle obs: obstacles) {
            gamePane.getChildren().add(obs.getShape());
        }

        gamePane.getChildren().addAll(game.getSnake().getShape(),pointCounter);
        
        gameScene = new Scene(gamePane, SCENEWIDTH, SCENEHEIGHT);
        
        gameScene.setOnKeyPressed(event ->{
            if(game.handleKeyPressed(event.getCode())) {
                game.setOffPause();
            }
        });
        
        new AnimationTimer(){
            long previousMoment = 0;
            @Override
            public void handle(long moment){
                if (moment - previousMoment < game.getSpeed()) {
                    return;
                }
                previousMoment = moment;    
                if (!game.paused){
                    gamePane.getChildren().add(game.moveSnake());
                    game.addPoints(1);
                    pointCounter.setText("Points: "+game.getPoints());
                }
                if (game.gameOver()) {
                    stop();
                    SnakeUi.this.createGameOverScene();
                }
                
            }
        }.start();
        
        // create settingsScene
        
//        Pane settingPane = new Pane();
//        VBox settingBox = new VBox();
//        
//        Label settingsLabel = new Label("SETTINGS");
//        Label notif = new Label(); 
//        Button backButton = new Button("back to menu");
//
//        backButton.setOnAction(e->{
//            primaryStage.setScene(menuScene);
//        });
//                
//        VBox genBox = new VBox();
//        genBox.getChildren().addAll(settingsLabel, notif, backButton);
//        
//        Label  difficulty = new Label("Game difficulty:");
//        Button easyButton = new Button("easy");
//        Button hardButton = new Button("hard");
//        
//        easyButton.setOnAction(e->{
//            game.setSpeed(40000000);
//            notif.setText("difficulty set to easy");
//        });
//        hardButton.setOnAction(e->{
//            game.setSpeed(10000000);
//            notif.setText("difficulty set to hard");
//        });
//        
//        VBox diffBox = new VBox();
//        HBox diffButtonsBox = new HBox();
//        diffButtonsBox.getChildren().addAll(easyButton, hardButton);
//        diffBox.getChildren().addAll(difficulty, diffButtonsBox);
//        
//        Label colorLabel = new Label("Set snake color:");
//        ColorPicker colorPicker = new ColorPicker();
//        colorPicker.setValue(Color.MEDIUMVIOLETRED);
//        
//        colorPicker.setOnAction(e->{
//            game.setSnakeColor(colorPicker.getValue());
//        });
//        
//        VBox colorBox = new VBox();
//        colorBox.getChildren().addAll(colorPicker);
//        
//        settingBox.getChildren().addAll(genBox, diffBox, colorBox);
//        settingPane.getChildren().add(settingBox);
//        settingScene = new Scene(settingPane, SCENEWIDTH, SCENEHEIGHT);
        
        // setup primary scene
        primaryStage.setTitle("SNAKES");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
    
    public void createMenuScene() {
        BorderPane menuPane = new BorderPane();
        Button newGameButton = new Button("new game");
        Button settingsButton = new Button("settings");
        Button highScoresButton = new Button("see highscores");
        
        newGameButton.setOnAction(e->{
            this.stage.setScene(gameScene);
        }); 
        settingsButton.setOnAction(e->{
           this.stage.setScene(settingScene); 
        });
        
        VBox menuBox = new VBox();
        menuBox.getChildren().addAll(newGameButton, settingsButton, highScoresButton);

        menuPane.setCenter(menuBox);
        
        menuScene = new Scene(menuPane, SCENEWIDTH, SCENEHEIGHT);
    }
    
    public void createGameOverScene() {
        Button newGameButton = new Button("play again");
        newGameButton.setOnAction(e->{
            stage.setScene(gameScene);
        });
        Button menuButton = new Button("menu");
        menuButton.setOnAction(e->{
            stage.setScene(menuScene);
        });
        Label gameOverLabel = new Label("game over!");
        Label scoreLabel = new Label("your score: "+game.getPoints());
        Label newHighScoreLabel = new Label("new high score!");
        Label giveYourNickLabel = new Label("give your name: ");
        TextField nameField = new TextField();
        VBox newHighScoreBox = new VBox();
        if (game.getPoints() > 500) {
            newHighScoreBox.getChildren().addAll(newHighScoreLabel, giveYourNickLabel, nameField);
        }
        
        VBox gameOverBox = new VBox();
        gameOverBox.getChildren().addAll(gameOverLabel, scoreLabel, 
                newHighScoreBox, newGameButton, menuButton);
        
        BorderPane gameOverPane = new BorderPane();
        gameOverPane.setCenter(gameOverBox);
        this.gameOverScene = new Scene(gameOverPane, SCENEWIDTH, SCENEHEIGHT);
        
        stage.setScene(gameOverScene);
    }
    
    public void createSettingScene() {
        Pane settingPane = new Pane();
        VBox settingBox = new VBox();
        
        Label settingsLabel = new Label("SETTINGS");
        Label notif = new Label(); 
        Button backButton = new Button("back to menu");

        backButton.setOnAction(e->{
            this.stage.setScene(menuScene);
        });
                
        VBox genBox = new VBox();
        genBox.getChildren().addAll(settingsLabel, notif, backButton);
        
        Label  difficulty = new Label("Game difficulty:");
        Button easyButton = new Button("easy");
        Button hardButton = new Button("hard");
        
        easyButton.setOnAction(e->{
            game.setSpeed(40000000);
            notif.setText("difficulty set to easy");
        });
        hardButton.setOnAction(e->{
            game.setSpeed(10000000);
            notif.setText("difficulty set to hard");
        });
        
        VBox diffBox = new VBox();
        HBox diffButtonsBox = new HBox();
        diffButtonsBox.getChildren().addAll(easyButton, hardButton);
        diffBox.getChildren().addAll(difficulty, diffButtonsBox);
        
        Label colorLabel = new Label("Set snake color:");
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.MEDIUMVIOLETRED);
        
        colorPicker.setOnAction(e->{
            game.setSnakeColor(colorPicker.getValue());
        });
        
        VBox colorBox = new VBox();
        colorBox.getChildren().addAll(colorPicker);
        
        settingBox.getChildren().addAll(genBox, diffBox, colorBox);
        settingPane.getChildren().add(settingBox);
        settingScene = new Scene(settingPane, SCENEWIDTH, SCENEHEIGHT);
    }
    
    public void setMenuScene() {
        stage.setScene(menuScene);
    }
    
    public void setSettingScene() {
        stage.setScene(settingScene);
    }
    
    public void setGameOverScene() {
        stage.setScene(gameOverScene);
    }
    
    public static void main(String[] args)  {
        launch(args);
    }
}

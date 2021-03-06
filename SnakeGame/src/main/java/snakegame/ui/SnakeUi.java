/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.ui;

import snakegame.domain.*;
import snakegame.dao.*;

import java.io.*;
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

/**
 * Game interface
 */
public class SnakeUi extends Application {
    
    private Stage stage;
    private Scene gameScene;
    private Scene menuScene;
    private Scene settingScene;
    private Scene gameOverScene;
    private Scene highScoreScene;
    private GameHandler game;
    private PointHandler points;
    public int SCENEWIDTH;
    public int SCENEHEIGHT;
    
    @Override
    public void init() {
        SCENEWIDTH = 600;
        SCENEHEIGHT = 600;
        
        this.createMenuScene();
        this.createSettingScene();
        
        game = new GameHandler(SCENEWIDTH, SCENEHEIGHT);
        
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
            String HighScoreSQL = properties.getProperty("scoreSQL");
            points = new PointHandler(new SQLHighScoreDao(HighScoreSQL));
        } catch(Exception e) {
            System.out.println("Check config.properties");
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
           
        // setup primary scene
        primaryStage.setTitle("SNAKES");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
   /**
    * creates the Menu scene
    */ 
    public void createMenuScene() {
        BorderPane menuPane = new BorderPane();
        Button newGameButton = new Button("new game");
        Button settingsButton = new Button("settings");
        Button highScoresButton = new Button("see highscores");
        
        newGameButton.setOnAction(e->{
            this.createGameScene();
            this.stage.setScene(gameScene);
        }); 
        settingsButton.setOnAction(e->{
           this.stage.setScene(settingScene); 
        });
        highScoresButton.setOnAction(e->{ 
            this.createHighScoreScene();
            this.stage.setScene(highScoreScene);
        });
        
        VBox menuBox = new VBox();
        menuBox.getChildren().addAll(newGameButton, settingsButton, highScoresButton);

        menuPane.setCenter(menuBox);
        
        menuScene = new Scene(menuPane, SCENEWIDTH, SCENEHEIGHT);
    }
    
    /**
     * creates and sets a new Game Over scene
     */
    public void createGameOverScene() {
        Button newGameButton = new Button("play again");
        newGameButton.setOnAction(e->{
            this.createGameScene();
            stage.setScene(gameScene);
        });
        Button menuButton = new Button("menu");
        menuButton.setOnAction(e->{
            stage.setScene(menuScene);
        });
        Label gameOverLabel = new Label("game over!");
        Label scoreLabel = new Label("your score: "+points.getPoints());
        
        Label newHighScoreLabel = new Label("new high score!");
        Label giveYourNickLabel = new Label("give your name: ");
        TextField nameField = new TextField();
        Button submitButton = new Button("submit highscore!");
        Label messageLabel = new Label();
        submitButton.setOnAction(e->{
            if (points.addNewHighscore(nameField.getText())) {
                messageLabel.setText("done!");
                submitButton.setOnAction(event ->{
                    messageLabel.setText("already submitted!");
                });
            }
        });
        HBox submitbox = new HBox(nameField, submitButton, messageLabel);
        VBox newHighScoreBox = new VBox();
        if (points.checkIfHighScore()) {
            newHighScoreBox.getChildren().addAll(newHighScoreLabel, giveYourNickLabel, submitbox);
        }
        
        VBox gameOverBox = new VBox();
        gameOverBox.getChildren().addAll(gameOverLabel, scoreLabel, 
                newHighScoreBox, newGameButton, menuButton);
        
        BorderPane gameOverPane = new BorderPane();
        gameOverPane.setCenter(gameOverBox);
        this.gameOverScene = new Scene(gameOverPane, SCENEWIDTH, SCENEHEIGHT);
        
        stage.setScene(gameOverScene);
    }
    
    /**
     * creates the Settings scene
     */
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
        colorBox.getChildren().addAll(colorLabel, colorPicker);
        
        settingBox.getChildren().addAll(genBox, diffBox, colorBox);
        settingPane.getChildren().add(settingBox);
        settingScene = new Scene(settingPane, SCENEWIDTH, SCENEHEIGHT);
    }
    
    /**
     * creates and sets new game scene
     */
    public void createGameScene() {
        game.newGame();
        points.reset();
        
        Pane gamePane = new Pane();
        
        Label pointCounter = new Label("Points: "+points.getPoints());
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
                game.start();
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
                if (!game.onPause()){
                    gamePane.getChildren().add(game.moveSnake());
                    points.addPoints(1);
                    pointCounter.setText("Points: "+points.getPoints());
                }
                if (game.gameOver()) {
                    stop();
                    SnakeUi.this.createGameOverScene();
                }
                
            }
        }.start();
    }
    
    /**
     * creates and sets highscore scene
     */
    private void createHighScoreScene() {
        BorderPane highScorePane = new BorderPane();
        Label title = new Label("HIGHSCORES:");
        VBox scoresbox = new VBox();
        int i = 1;
        for(String s: points.getHighscores()) {
            scoresbox.getChildren().add(new Label(i+": "+s));
            i++;
        }
        Button backToMenuButton = new Button("back to main menu");
        backToMenuButton.setOnAction(e-> {
            stage.setScene(menuScene);
        });
        VBox allBox = new VBox();
        allBox.getChildren().addAll(title, scoresbox, backToMenuButton);
        highScorePane.setCenter(allBox);
        highScoreScene = new Scene(highScorePane, SCENEWIDTH, SCENEHEIGHT);
    }
    
    /**
     * sets the current scene to Menu
     */
    public void setMenuScene() {
        stage.setScene(menuScene);
    }
    
    /**
     * sets the current scene to Settings
     */
    public void setSettingScene() {
        stage.setScene(settingScene);
    }
    
    public static void main(String[] args)  {
        launch(args);
    }
}

package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuController {

    private Stage primaryStage;
    private Scene currentScene;
    private Parent parentRoot;

    private GameScene game;

    private String colorPicked = "#ffffff";


    static final int WIDTH = 900;
    static final int HEIGHT =700;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Text headerText;


    @FXML
    protected void colorPickerOnAction(ActionEvent event){
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Get the color that the user choose
        colorPicked = colorPicker.getValue().toString();
//        System.out.println(colorPicked);
        pane.setBackground(new Background(new BackgroundFill(Paint.valueOf(colorPicked), CornerRadii.EMPTY, Insets.EMPTY)));


    }

    @FXML
    protected void startButtonOnAction(ActionEvent event){

        // get the primaryStage
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // switch to Game Scene
        openGameScene();

        // Display it on the primaryStage

        this.primaryStage.show();


    }

    @FXML
    protected void exitButtonOnAction(){
        Platform.exit();
    }

    private void openGameScene(){

        // Initialize a new game
        this.game = new GameScene();


        // Initialize GameScene and EndGameScene
        Group gameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Paint.valueOf(colorPicked));

        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));

        // Open the game play
        this.game.Game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
        this.primaryStage.setScene(gameScene);


    }


}

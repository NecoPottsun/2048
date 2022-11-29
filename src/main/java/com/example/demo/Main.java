package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 700;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Group menuRoot = new Group();
//        Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT);
//        Group accountRoot = new Group();
//        Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));
//        Group getAccountRoot = new Group();
//        Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));
//        Group endgameRoot = new Group();
//        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, Color.rgb(250, 20, 100, 0.2));
//        Group rankRoot = new Group();
//        Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120, 0.3));
//        BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
//        Background background = new Background(background_fill);
//
//
//        Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
//        backgroundOfMenu.setX(WIDTH / 2 - 120);
//        backgroundOfMenu.setY(180);
//        menuRoot.getChildren().add(backgroundOfMenu);
//
//        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
//        backgroundOfMenuForPlay.setX(WIDTH / 2 - 120);
//        backgroundOfMenuForPlay.setY(180);
//        accountRoot.getChildren().add(backgroundOfMenuForPlay);
//
//        Group gameRoot = new Group();
//        setGameRoot(gameRoot);
//        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
//        setGameScene(gameScene);
//        primaryStage.setScene(gameScene);
//        GameScene game = new GameScene();
//        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
//
//        primaryStage.show();
        //          FXML Tester
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("LoadingScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),WIDTH , HEIGHT);
        primaryStage.setTitle("2048");
        primaryStage.getIcons().add(new Image(
                "https://icons.iconarchive.com/icons/alecive/flatwoken/512/Apps-2048-icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

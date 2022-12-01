package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EndGame {
    private static int WIDTH = 900;
    private static int HEIGHT = 700;
    private static EndGame singleInstance = null;
    private EndGame(){

    }
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(Scene endGameScene, EndGameController endGameController, Stage primaryStage, Account account){


            primaryStage.setScene(endGameScene);
            primaryStage.setResizable(false);
            endGameController.initData(account);
            primaryStage.show();




    }
}

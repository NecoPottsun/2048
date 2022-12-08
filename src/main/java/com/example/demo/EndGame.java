package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class EndGame {

    private static EndGame singleInstance = null;
    private MediaPlayer mediaPlayer;
    private EndGame(){

    }
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void endGameShow(Scene endGameScene, EndGameController endGameController, Stage primaryStage, Account account){


            primaryStage.setScene(endGameScene);
            primaryStage.setResizable(false);
            endGameController.initData(account);
            primaryStage.show();




    }
}

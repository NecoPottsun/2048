package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * To handle the EndGame process from the GameScene to EndGameScene
 * @author Thanh Mai Duyen Trang-modified
 */
public class EndGame {
    private static EndGame singleInstance = null;
    private Scene endGameScene;
    private EndGameController endGameController;
    private Stage primaryStage;
    private Account account;

    /**
     * The constructor of EndGame
     */
    private EndGame(){

    }

    /**
     * <code>getInstance()</code> help this EndGame class created one single object,
     * which follows the Singleton
     * @return EndGame
     */
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    /**
     * Gets the end game scene assigned to this EndGame
     * @return EndGameScene
     */
    public Scene getEndGameScene() {
        return endGameScene;
    }

    /**
     * Sets the end game scene of this EndGame
     * @param endGameScene the EndGameScene wanted to replace
     */
    public void setEndGameScene(Scene endGameScene) {
        this.endGameScene = endGameScene;
    }

    /**
     * Gets the end game controller of this EndGame
     * @return EndGameController
     */
    public EndGameController getEndGameController() {
        return endGameController;
    }

    /**
     * Sets the end game controller of this EndGame
     * @param endGameController the EndGameController wanted to replace
     */
    public void setEndGameController(EndGameController endGameController) {
        this.endGameController = endGameController;
    }

    /**
     * Gets the primary stage of this EndGame
     * @return the primary stage of this EndGame
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets the primary stage of this EndGame
     * @param primaryStage the primary stage of this EndGame
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Gets the account of this EndGame
     * @return Account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account of this EndGame
     * @param account the account of this EndGame
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Assigned the values for endGameScene, endGameController, primaryStage, account for this EndGame.
     * Open the end game scene
     * and also pass the account data to endGameController
     * @param endGameScene the end game scene of this EndGame
     * @param endGameController the end game controller of this EndGame
     * @param primaryStage the primary stage of this EndGame
     * @param account the account of this EndGame
     */
    public void endGameShow(Scene endGameScene, EndGameController endGameController, Stage primaryStage, Account account){
            this.endGameScene = endGameScene;
            this.endGameController = endGameController;
            this.primaryStage = primaryStage;
            this.account = account;

            primaryStage.setScene(endGameScene);
            primaryStage.setResizable(false);
            endGameController.initData(account);
            primaryStage.show();

    }
}

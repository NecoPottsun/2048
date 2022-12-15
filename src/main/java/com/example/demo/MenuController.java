package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is to handle the MenuScene of the game
 * @author Thanh Mai Duyen Trang
 */
public class MenuController implements Initializable {

    private Stage primaryStage;


    private static int HARD = 4;
    private static int EASY = 5;

    private GameScene game;

    private String colorPicked = "#5C2F81";
    @FXML
    private AnchorPane pane;

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField usernameTextField;

    @FXML
    private Rectangle backgroundColorRectangle;

    @FXML
    private Pane musicPane;


    /**
     * Create all the initial values, do the very first stuffs
     * when MenuScene is created
     * @param url the url
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colorPicker.setValue(Color.valueOf(colorPicked));
        backgroundColorRectangle.setFill(Paint.valueOf(colorPicked));


    }

    /**
     * Gets the color picked from the Color Picker in the Menu Scene
     * @return color picked from the Color Picker in the Menu Scene
     *<p>
     *     Default value: #5C2F81
     *</p>
     */
    public String getColorPicked() {
        return colorPicked;
    }

    /**
     * Loads the music pane in the Menu Scene
     * @param pane the music pane in the Scene
     */
    public static void loadMusicPane (Pane pane)  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane newLoadedPane = null;
        try {
            System.out.println("Loaded Music Pane");
            newLoadedPane = fxmlLoader.load(MenuController.class.getResource("MusicPane.fxml"));
            pane.getChildren().add(newLoadedPane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Opens the music pane inside the Menu Scene
     * @param event the event listened when the Music button is clicked
     */
    @FXML
    protected  void openMusicPaneButtonOnAction(ActionEvent event){
        loadMusicPane(musicPane);
        if(musicPane.isVisible()){
            musicPane.setVisible(false);
        }
        else{
            musicPane.setVisible(true);
        }
    }

    /**
     * Listens the chosen color to set the background color of the Game Scene
     * @param event the event listened when the color is changed
     */
    @FXML
    protected void colorPickerOnAction(ActionEvent event){
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Get the color that the user choose
        colorPicked = colorPicker.getValue().toString();
//        System.out.println(colorPicked);
        pane.setBackground(new Background(new BackgroundFill(Paint.valueOf(colorPicked), CornerRadii.EMPTY, Insets.EMPTY)));
        backgroundColorRectangle.setFill(Paint.valueOf(colorPicked));


    }
    /**
     * Opens the easy mode by passing the number of plain 5x5 grid of the Game Scene
     * @param event the event listened when the Easy button is clicked
     */
    @FXML
    protected void easyStartButtonOnAction(ActionEvent event){

        // get the primaryStage
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // switch to Game Scene
        openGameScene(EASY);

        // Display it on the primaryStage

        this.primaryStage.show();
    }
    /**
     * Opens the hard mode by passing the number of plain 4x4 grid of the Game Scene
     * @param event the event listened when the Hard button is clicked
     */
    @FXML
    protected void hardStartButtonOnAction(ActionEvent event){

        // get the primaryStage
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // switch to Game Scene
        openGameScene(HARD);

        // Display it on the primaryStage

        this.primaryStage.show();
    }
    /**
     * Opens the new Rank Scene when clicking the Score Board button inside
     * the MenuScene
     * @param event the event listened when the Score Board button is clicked
     * @throws IOException if errors occurred
     */
    @FXML
    protected void scoreBoardButtonOnAction(ActionEvent event) throws IOException {
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("RankScene.fxml"));

        Scene scene = new Scene(fxmlLoader.load(),Main.WIDTH , Main.HEIGHT);
        RankSceneController rankSceneController = fxmlLoader.getController();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Terminates the game system
     * @param event the event listened when the Quit button is clicked
     */
    @FXML
    protected void quitButtonOnAction(ActionEvent event){
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Dialog");
        alert.setHeaderText("Quit game");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            primaryStage.close();
            System.exit(0);
        }
    }

    /**
     * Creates a new Game Scene with chosen level
     * @param level the game level that had been chosen
     */
    private void openGameScene(int level){

        // Initialize a new game
        this.game = new GameScene();
        this.game.setN(level);
        System.out.println("Size = " + this.game.getN());
        System.out.println("LENGTH = " + this.game.getLENGTH());

        long newAccountId = AccountDao.getInstance().getAccounts().size() + 1;
        String newAccountUsername = usernameTextField.getText();
        if(newAccountUsername.isEmpty()){
            newAccountUsername = usernameTextField.getPromptText();
        }
        System.out.println(newAccountUsername);
        Account account = new Account(newAccountId,newAccountUsername);
        EndGameController endGameController;

        // Initialize GameScene and EndGameScene
        Group gameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, Main.WIDTH, Main.HEIGHT, Paint.valueOf(colorPicked));

//        Group endgameRoot = new Group();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("EndGameScene.fxml"));

        Scene endGameScene = null;
        try {
            endGameScene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
            endGameController = fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Open the game play
      //  this.game.setN(level);
        this.game.Game(gameScene, gameRoot, primaryStage, endGameScene, endGameController, account);
        this.primaryStage.setScene(gameScene);

    }



}

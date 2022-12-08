package com.example.demo;

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

public class MenuController implements Initializable {

    private Stage primaryStage;
    private Scene currentScene;
    private Parent parentRoot;

    static MediaPlayer mediaPlayer;

    private static int HARD = 4;
    private static int EASY = 5;

    private GameScene game;

    private String colorPicked = "#658354";


    static final int WIDTH = 900;
    static final int HEIGHT =700;
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

    @FXML
    private MusicPaneController musicPaneController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorPicker.setValue(Color.valueOf(colorPicked));
        backgroundColorRectangle.setFill(Paint.valueOf(colorPicked));




    }
    public void init(){
   //     String path = "music/soundtrack1.mp3";
    //    MusicPaneController.playSoundtrack(SoundtrackDatabase.getInstance().getSoundtrackAtPosition(1));
//        MusicPaneController.playSoundtrack(SoundtrackDatabase.getInstance().getSoundtrackAtPosition(0));
    }
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

    @FXML
    protected void colorPickerOnAction(ActionEvent event){
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Get the color that the user choose
        colorPicked = colorPicker.getValue().toString();
//        System.out.println(colorPicked);
        pane.setBackground(new Background(new BackgroundFill(Paint.valueOf(colorPicked), CornerRadii.EMPTY, Insets.EMPTY)));
        backgroundColorRectangle.setFill(Paint.valueOf(colorPicked));


    }

    @FXML
    protected void easyStartButtonOnAction(ActionEvent event){

        // get the primaryStage
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // switch to Game Scene
        openGameScene(EASY);

        // Display it on the primaryStage

        this.primaryStage.show();
    }
    @FXML
    protected void hardStartButtonOnAction(ActionEvent event){

        // get the primaryStage
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // switch to Game Scene
        openGameScene(HARD);

        // Display it on the primaryStage

        this.primaryStage.show();
    }
    @FXML
    protected void scoreBoardButtonOnAction(ActionEvent event) throws IOException {
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("RankScene.fxml"));

        Scene scene = new Scene(fxmlLoader.load(),WIDTH , HEIGHT);
        RankSceneController rankSceneController = fxmlLoader.getController();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
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
        }
    }

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
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Paint.valueOf(colorPicked));

//        Group endgameRoot = new Group();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("EndGameScene.fxml"));

        Scene endGameScene = null;
        try {
            endGameScene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
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

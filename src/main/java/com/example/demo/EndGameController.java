package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EndGameController implements Initializable {
    private static int WIDTH = Main.WIDTH;
    private static int HEIGHT = Main.HEIGHT;
    private Stage primaryStage;
    private Account account;
    @FXML
    private HBox congratulationHBox;

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Text scoreText;

    @FXML
    private Text timePlayText;

    @FXML
    private Button backButton;

    @FXML
    private Button quitButton;

    @FXML
    private ImageView santaIconImage;

    @FXML
    private ImageView blueJuminoIcon;

    @FXML
    private ImageView purpleJuminoIcon;

    @FXML
    private Pane musicPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TranslateTransition translate = new TranslateTransition();
//        translate.setNode(santaIconImage);
//        translate.setDuration(Duration.millis(5000));
//        translate.setCycleCount(TranslateTransition.INDEFINITE);
//        translate.setByX(WIDTH-200);
//        translate.setAutoReverse(true);
//        translate.play();
        MenuController.loadMusicPane(musicPane);

        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(blueJuminoIcon);
        translate1.setDuration(Duration.millis(5000));
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
        translate1.setByX(WIDTH-blueJuminoIcon.getFitWidth());
        translate1.setAutoReverse(true);
        translate1.play();

        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(purpleJuminoIcon);
        translate2.setDuration(Duration.millis(5000));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setByX(WIDTH-purpleJuminoIcon.getFitWidth()-5);
        translate2.setAutoReverse(true);
        translate2.play();


    }


    void initData(Account account){
  //      mainAnchorPane.setStyle("-fx-background-color:  " + pickedColor);
        System.out.println(account.getId());
        System.out.println(account.getPlayTime());
        this.account = account;
        scoreText.setText("Your score: " + account.getScore());
        timePlayText.setText("Time: " + account.getPlayTime());
        // add to excel
        AccountDao.getInstance().add(account);

        checkFirstRank();
    }

    @FXML
    protected  void openMusicPaneButtonOnAction(ActionEvent event){
        if(musicPane.isVisible()){
            musicPane.setVisible(false);
        }
        else{
            musicPane.setVisible(true);
        }
    }

    @FXML
    protected void scoreBoardButtonOnAction(ActionEvent event) throws IOException {
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("RankScene.fxml"));
        RankSceneController rankSceneController = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(),WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    protected void backButtonOnAction(ActionEvent event){
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Go To Menu");
        alert.setHeaderText("Go to Menu");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("MenuScene.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(),WIDTH , HEIGHT);
                MenuController menuController = fxmlLoader.getController();
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
            System.exit(0);
        }

    }

    private void checkFirstRank() {
        List<Account> accounts = AccountDao.getInstance().GetSortList();
        if (account.getId() == accounts.get(0).getId() && account.getUsername().equals(accounts.get(0).getUsername()) && account.getScore() == accounts.get(0).getScore() &&
                account.getPlayTime().equals(accounts.get(0).getPlayTime())) {
            congratulationHBox.setVisible(true);
        }
    }
}

package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * To control the RankScene, handle the interactions between the user
 * with the system in the Rank scene of the game
 * @author Thanh Mai Duyen Trang
 */
public class RankSceneController implements Initializable {
    private Stage primaryStage;
   @FXML
   private TableView<Account> scoreTable;

   @FXML
   private TableColumn<Account, String> usernameColumn;
   @FXML
   private TableColumn<Account, Integer> scoreColumn;
    @FXML
    private TableColumn<Account, String> timeColumn;

    @FXML
    private Pane musicPane;

   private ObservableList<Account> accountObservableList = FXCollections.observableArrayList();
    /**
     * Create all the initial values, do the very first stuffs
     * when RankScene is created
     * @param url the URL
     * @param resourceBundle the resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuController.loadMusicPane(musicPane);
        AccountDao.getInstance();
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("playTime"));
//        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsername());

        for(Account account : AccountDao.getInstance().GetSortList()){
            accountObservableList.add(account);
        }
        scoreTable.setItems(accountObservableList);
    }
    /**
     * Opens the music pane inside the RankScene
     * @param event the event listened when the Music button is clicked
     */
    @FXML
    protected  void openMusicPaneButtonOnAction(ActionEvent event){
        if(musicPane.isVisible()){
            musicPane.setVisible(false);
        }
        else{
            musicPane.setVisible(true);
        }
    }

    /**
     * Opens the Menu Scene when clicking the Back button in the RankScene
     * @param event the event listened when the Back button is clicked
     */
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
                Scene scene = new Scene(fxmlLoader.load(),Main.WIDTH , Main.HEIGHT);
                MenuController menuController = fxmlLoader.getController();
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

}

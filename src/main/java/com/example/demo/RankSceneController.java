package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RankSceneController implements Initializable {
    private static int WIDTH = 900;
    private static int HEIGHT = 700;

    private Stage primaryStage;
   @FXML
   private TableView<Account> scoreTable;

   @FXML
   private TableColumn<Account, String> usernameColumn;
   @FXML

   private TableColumn<Account, Integer> scoreColumn;

   private ObservableList<Account> accountObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountDao.getInstance();
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
//        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsername());

        for(Account account : AccountDao.getInstance().GetSortList()){
            accountObservableList.add(account);
        }
        scoreTable.setItems(accountObservableList);
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
        }

    }
}

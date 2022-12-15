package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * This is the Main class for initialize the game application
 * @author Thanh Mai Duyen Trang-modified
 */
public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 700;

    /**
     * Application starts
     * @param primaryStage the primary stage of the game
     * @throws Exception if errors occurred
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("LoadingScene.fxml"));
        System.out.println(Main.class.getResource("LoadingScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),WIDTH , HEIGHT);
        primaryStage.setTitle("2048");
        primaryStage.getIcons().add(new Image(
                "https://icons.iconarchive.com/icons/alecive/flatwoken/512/Apps-2048-icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // To end all the time counts in the system
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });


    }

    /**
     * Runs the mains
     * @param args the args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

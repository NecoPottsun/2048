package com.example.demo;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable{
    private Stage primaryStage;
    @FXML
    private AnchorPane pane;
    @FXML
    private Text progressNumber;
    @FXML
    private ProgressBar progressBar;

    static final int WIDTH = 900;
    static final int HEIGHT = 700;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        progressBar.setStyle("-fx-accent: #6600ff;");
       increaseProgress();

    }
    public void increaseProgress() {
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                int steps = 1000;
                for (int i = 0; i <= steps; i+= 10) {
                    Thread.sleep(50);
                    updateProgress(i, steps);
                    updateMessage(String.valueOf(i));
                    System.out.println(i);
                    String progress = String.valueOf(i/10) + "%";
                    progressNumber.setText(progress);

                }
                return null;
            }
        };
        // This method allows us to handle any Exceptions thrown by the task
        task.setOnFailed(wse -> {
            wse.getSource().getException().printStackTrace();
        });

        // If the task completed successfully, perform other updates here
        task.setOnSucceeded(wse -> {
            System.out.println("Done!");
            try {
                openMenuScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Before starting our task, we need to bind our UI values to the properties on the task
        progressBar.progressProperty().bind(task.progressProperty());
//        lblProgress.textProperty().bind(task.messageProperty());

        // Now, start the task on a background thread
        new Thread(task).start();
    }

    public void openMenuScene() throws IOException {
        primaryStage = (Stage) pane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("MenuScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),WIDTH , HEIGHT);
        this.primaryStage.setScene(scene);

    }
}

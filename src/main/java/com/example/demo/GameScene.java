package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.util.SystemOutLogger;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class GameScene {
    private static int WIDTH = Main.WIDTH;
    private static int HEIGHT = Main.HEIGHT;
    private static int n = 5;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private static Cell[][] cells;
    private Group root;
    private long score = 0;
    private long mergedCellScore = 0;
    private Timeline timeline;
    private Text timeText;
    private int second, minute, hardSecond, hardMinute;
    private String ddSecond, ddMinute, ddHardSecond, ddHardMinute;
    private String playTime;
    private DecimalFormat dFormat = new DecimalFormat("00");
    private Stage primaryStage;
    private Scene endGameScene;
    private EndGameController endGameController;
    private Pane musicPane;
    private Account account;


    public static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
        cells = new Cell[n][n];
    }
    public static int getN(){
        return n;
    }

    static double getLENGTH() {
        return LENGTH;
    }

    private void randomFillNumber(int turn) {

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }
        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
        xCell = random.nextInt(aForBound+1);
        yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
    }

    private int passDestination(int i, int j, char direct) {
        return direct == 'l' ? passLeft(i,j) : direct == 'r' ? passRight(i,j) : direct == 'd'
                ? passDown(i,j) : direct == 'u' ? passUp(i,j) : -1;
    }
    private int passLeft(int i, int j){
        int coordinate = j;
        System.out.println("passLeft() called, coordinate:" + coordinate);
        for (int k = j - 1; k >= 0; k--) {
            if (cells[i][k].getNumber() != 0) {
                coordinate = k + 1;
                break;
            } else if (k == 0) {
                coordinate = 0;
            }
        }
        return coordinate;
    }
    private int passRight(int i, int j){
        int coordinate = j;
        System.out.println("passRight() called, coordinate: " + coordinate);
        for (int k = j + 1; k <= n - 1; k++) {
            if (cells[i][k].getNumber() != 0) {
                coordinate = k - 1;
                break;
            } else if (k == n - 1) {
                coordinate = n - 1;
            }
        }
        return coordinate;
    }
    private int passDown(int i, int j){
        int coordinate = i;
        System.out.println("passDown() called, coordinate:" + coordinate);
        for (int k = i + 1; k <= n - 1; k++) {
            if (cells[k][j].getNumber() != 0) {
                coordinate = k - 1;
                break;

            } else if (k == n - 1) {
                coordinate = n - 1;
            }
        }
        return coordinate;
    }
    private int passUp(int i, int j){
        int coordinate = i;
        System.out.println("passUp() called, coordinate: " + coordinate);
        for (int k = i - 1; k >= 0; k--) {
            if (cells[k][j].getNumber() != 0) {
                coordinate = k + 1;
                break;
            } else if (k == 0) {
                coordinate = 0;
            }
        }
        return coordinate;
    }
    private void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }
    private void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }
    private void moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    private void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isMergedHorizontally(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            System.out.println("Move horizontally");
            cells[i][des].setModify(true);
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }
    }
    private void moveVertically(int i, int j, int des, int sign) {
        if (isMergedVertically(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);
    //        System.out.println("Move horizontally");
            //          System.out.printf("[%d,%d] merged = %d\n", des + sign, j, cells[des + sign][j].getNumber()+ cells[i][j].getNumber());
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
        }
    }
    private boolean isMergedHorizontally(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
  //              System.out.printf("[%d,%d] merged = %d\n", i, des + sign, cells[i][des + sign].getNumber() + cells[i][des + sign].getNumber());
                mergedCellScore += cells[i][des + sign].getNumber() + cells[i][des + sign].getNumber();
//                System.out.println("Score: " + mergedCellScore);
                return true;
            }
        }
        return false;

//        return (des + sign < n && des + sign >= 0) ? (cells[i][des + sign].getNumber() == cells[i][j].getNumber()
//                                                        && !cells[i][des + sign].getModify()
//                                                         && cells[i][des + sign].getNumber() != 0) ? true : false : false;
    }
    private boolean isMergedVertically(int i, int j, int des, int sign) {
     //   System.out.printf("i = %d, j = %d, des = %d, sign = %d\n", i,j,des,sign);
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
    //            System.out.printf("[%d,%d] merged = %d\n", des + sign, j, cells[des + sign][j].getNumber()+ cells[i][j].getNumber());
                mergedCellScore += cells[des + sign][j].getNumber()+ cells[i][j].getNumber();
                //       System.out.println("Score: " + mergedCellScore);
                return true;
            }

        return false;
    }

    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void sumCellNumbersToScore() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//               score += cells[i][j].getNumber();
                if(cells[i][j].getNumber() != 0){
                    score += cells[i][j].getNumber();
//                    System.out.println(cells[i][j].getNumber());
                }

            }
        }
    }

    public void Game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, EndGameController endGameController, Account account) {
        this.root = root;
        this.account = account;
        this.primaryStage = primaryStage;
        this.endGameScene = endGameScene;
        this.endGameController = endGameController;

        System.out.println(endGameScene);

        // create cells
        createCells();

        // add Stylesheet
        gameScene.getStylesheets().add(String.valueOf(getClass().getResource("css/style.css")));

        // musicPane
        createMusicPane();

        // Open music button
        Button openMusicPaneButton = new Button();
        root.getChildren().add(openMusicPaneButton);
        openMusicPaneButton.setText("Music");
        openMusicPaneButton.relocate(850,10);
        openMusicPaneButton.getStyleClass().add("button");
        openMusicPaneOnAction(openMusicPaneButton, musicPane);

        MenuController.loadMusicPane(musicPane);

        // Time Text
        timeText = createText(root, 790, 120, "00:00", 25);
        Text text1 = createText(root,770,170,"PLAYER: ", 30);
        Text playerText = createText(root,770, 220, account.getUsername(), 20);


        // add new mergedCellScore Text
        Text text2 = createText(root, 770 ,270, "SCORE : ", 30);
        Text mergedCellScoreText = createText(root, 770,320,"0", 20);

        // create button to go back to the MenuScene
        Button goBackButton = new Button();

        root.getChildren().add(goBackButton);
        goBackButton.setText("Back");
        goBackButton.relocate(10,10);
        goBackButton.getStyleClass().add("button");
        goBackOnAction(goBackButton, primaryStage);

        randomFillNumber(1);
        randomFillNumber(1);


        // Time

        if(n == 4){
            System.out.println("hard");
            second = 90;
            simpleTimer(1);
        }
        else if (n == 5){
            System.out.println("easy");
            second = 0;
            simpleTimer(0);
        }

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {
                boolean isMove;
                int haveEmptyCell;
                isMove = keyPressHandler(key);
                mergedCellScoreText.setText(mergedCellScore + "");
                // if a move key is pressed, then random numbers are filled or end the game
                if(isMove){
                    haveEmptyCell = GameScene.this.haveEmptyCell();
                    isMoveHandler(haveEmptyCell);
                }

            });
        });
    }

    private void createMusicPane() {
        musicPane = new Pane();
        musicPane.setStyle("-fx-background-color: #ffffff");
        musicPane.setPrefSize(250,70);
        musicPane.relocate(650, 40);
        musicPane.setVisible(false);
        root.getChildren().add(musicPane);
    }

    private Text createText(Group root, int x, int y,String textString,int size){
        Text text = new Text();
        root.getChildren().add(text);
        text.setText(textString);
        text.setFont(Font.font(size));
        text.setFill(Paint.valueOf("#ddead1"));
        text.relocate(x, y);
        return text;
    }
    private void createCells(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells + 60,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }
    }
    private Boolean keyPressHandler(KeyEvent key) {

        // Since the arrow keys cant work well (u have to press (ctrl + arrow_key) to work it), I'll add the WASD for handle it
        if (key.getCode() == KeyCode.DOWN || key.getCode() == KeyCode.S) {
            GameScene.this.moveDown();
            return true;
        } else if (key.getCode() == KeyCode.UP || key.getCode() == KeyCode.W) {
            GameScene.this.moveUp();
            return  true;
        } else if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A) {
            GameScene.this.moveLeft();
            return  true;
        } else if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D) {
            GameScene.this.moveRight();
            return  true;
        }
        return false;
    }
    public void simpleTimer(int mode){
        minute = secondToMinute(second);
        second = second - minute*60;
        ddSecond = dFormat.format(second);
        ddMinute = dFormat.format(minute);
        timeText.setText(ddMinute + ":" + ddSecond);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if(mode == 0){
                second++;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                timeText.setText(ddMinute + ":" + ddSecond);
                if(second == 60){
                    second = 0;
                    minute++;
                    timeText.setText(ddMinute + ":" + ddSecond);
                }
                playTime = ddMinute + ":" + ddSecond;
            }
            if(mode == 1){
                hardSecond++;
                ddHardSecond = dFormat.format(hardSecond);
                ddHardMinute = dFormat.format(hardMinute);
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                timeText.setText(ddMinute + ":" + ddSecond);
                if(hardSecond == 60){
                    hardSecond = 0;
                    hardMinute++;
                }
                if(minute > 0){
                    if(second > 0){
                        second--;
                    }
                    if(second == 0){
                        minute--;
                        second = 59;
                    }
                }
                else if(minute == 0){
                    second--;
                    if(second == 0){
                        playTime = ddHardMinute + ":" + ddHardSecond;
                        endGame();
                    }
                }
                playTime = ddHardMinute + ":" + ddHardSecond;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    public int secondToMinute(int second){
        return (second%3600)/60;
    }

    private void isMoveHandler(int haveEmptyCell) {
        if (haveEmptyCell == -1) {
            if (GameScene.this.canNotMove()) {
//                timer.cancel();
                endGame();
            }
        } else if(haveEmptyCell == 1)
            GameScene.this.randomFillNumber(2);
    }

    private void endGame() {
        System.out.println("End game called");
        System.out.println("Play time = " + playTime);
        timeline.stop();
        account.setScore(mergedCellScore);
        account.setPlayTime(playTime);
        EndGame.getInstance().endGameShow(this.endGameScene, endGameController, primaryStage, account);
        primaryStage.setScene(this.endGameScene);

        root.getChildren().clear();
        score = 0;
        mergedCellScore = 0;
    }

    private void goBackOnAction(Button goBackButton,Stage primaryStage) {
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                goBackToTheMenu(primaryStage);

            }
        });
    }
    private void openMusicPaneOnAction(Button openMusicPaneButton, Pane musicPane){
        openMusicPaneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(musicPane.isVisible()){
                    musicPane.setVisible(false);
                }
                else{
                    musicPane.setVisible(true);
                }
            }
        });
    }

    private void goBackToTheMenu(Stage primaryStage){

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
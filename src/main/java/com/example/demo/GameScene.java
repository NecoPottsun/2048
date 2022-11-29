package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

class GameScene {
    private static int WIDTH = 900;
    private static int HEIGHT = 700;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;
    private long score = 0;
    private long mergedCellScore = 0;

    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
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


    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isMergedHorizontally(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des].setModify(true);
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }
    }

    private boolean isMergedHorizontally(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                System.out.printf("[%d,%d] merged = %d\n", i, des + sign, cells[i][des + sign].getNumber() + cells[i][des + sign].getNumber());
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
        System.out.printf("i = %d, j = %d, des = %d, sign = %d\n", i,j,des,sign);
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                System.out.printf("[%d,%d] merged = %d\n", des + sign, j, cells[des + sign][j].getNumber()+ cells[i][j].getNumber());
                mergedCellScore += cells[des + sign][j].getNumber()+ cells[i][j].getNumber();
                //       System.out.println("Score: " + mergedCellScore);
                return true;
            }

        return false;
    }
    private void moveVertically(int i, int j, int des, int sign) {
        if (isMergedVertically(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
        }
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

    public void Game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
        this.root = root;
        // create cells
        createCells();

        // create the Texts in the game
        Text text1 = createText(root,770,100,"SCORE 1: ", 30);

        Text scoreText1 = createText(root,770, 150, "0", 20);

        // add new mergedCellScore Text
        Text text2 = createText(root, 770 ,300, "SCORE 2: ", 30);

        Text mergedCellScoreText = createText(root, 770,350,"0", 20);

        // create button to go back to the MenuScene
        Button goBackButton = new Button();
        root.getChildren().add(goBackButton);
        goBackButton.setText("Go back");
        goBackButton.relocate(5,10);
        goBackOnAction(goBackButton, primaryStage);

        randomFillNumber(1);
        randomFillNumber(1);

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {
                int haveEmptyCell;
                boolean isMove = false;
//                    System.out.println(key.getCode());
                // Since the arrow keys cant work well (u have to press (ctrl + arrow_key) to work it), I'll add the WASD for handle it
                if (key.getCode() == KeyCode.DOWN || key.getCode() == KeyCode.S) {
                    GameScene.this.moveDown();
                    isMove = true;
                } else if (key.getCode() == KeyCode.UP || key.getCode() == KeyCode.W) {
                    GameScene.this.moveUp();
                    isMove = true;
                } else if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A) {
                    GameScene.this.moveLeft();
                    isMove = true;
                } else if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D) {
                    GameScene.this.moveRight();
                    isMove = true;
                }
                // GameScene.this.sumCellNumbersToScore();
                scoreText1.setText(score + "");
                mergedCellScoreText.setText(mergedCellScore + "");

                // if a move key is pressed, then random numbers are filled or end the game
                if(isMove){
                    haveEmptyCell = GameScene.this.haveEmptyCell();
                    isMoveHandler(haveEmptyCell, primaryStage, endGameScene, endGameRoot);
                }

            });
        });
    }

    private void createCells(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells + 60,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }
    }
    private Text createText(Group root, int x, int y,String textString,int size){
        Text text = new Text();
        root.getChildren().add(text);
        text.setText(textString);
        text.setFont(Font.font(size));
        text.relocate(x, y);
        return text;
    }

    private void isMoveHandler(int haveEmptyCell, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
        if (haveEmptyCell == -1) {
            if (GameScene.this.canNotMove()) {
                primaryStage.setScene(endGameScene);

                EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, mergedCellScore);
                root.getChildren().clear();
                score = 0;
                mergedCellScore = 0;
            }
        } else if(haveEmptyCell == 1)
            GameScene.this.randomFillNumber(2);
    }

    private void goBackOnAction(Button goBackButton,Stage primaryStage) {
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                goBackToTheMenu(primaryStage);

            }
        });
    }

    private void goBackToTheMenu(Stage primaryStage){

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
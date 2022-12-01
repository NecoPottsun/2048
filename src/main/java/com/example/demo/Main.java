package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 700;
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {



//      FXML Tester
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("MenuScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),WIDTH , HEIGHT);
        primaryStage.setTitle("2048");
        primaryStage.getIcons().add(new Image(
                "https://icons.iconarchive.com/icons/alecive/flatwoken/512/Apps-2048-icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Excel test
//        URL url = getClass().getResource("data/account1.xls");
//        System.out.println(getClass().getResource("data/account1.xls"));
//        File file = new File(url.toURI());
//      //  File file = new File("\\data\\account.xlsx");   //creating a new file instance
//    //    BufferedReader in = new BufferedReader(new FileReader(file));
//
//        //obtaining input bytes from a file
//        FileInputStream fis=new FileInputStream(file);
//        //creating workbook instance that refers to .xls file
//        HSSFWorkbook wb=new HSSFWorkbook(fis);
//        //creating a Sheet object to retrieve the object
//        HSSFSheet sheet=wb.getSheetAt(0);
//        FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
//        for(Row row: sheet)     //iteration over row using for each loop
//        {
//            for(Cell cell: row)    //iteration over cell using for each loop
//            {
//                switch(formulaEvaluator.evaluateInCell(cell).getCellType())
//                {
//                    case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type
//                        //getting the value of the cell as a number
//                        System.out.print(cell.getNumericCellValue()+ "\t\t");
//                        break;
//                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type
//                        //getting the value of the cell as a string
//                        System.out.print(cell.getStringCellValue()+ "\t\t");
//                        break;
//                }
//            }
//            System.out.println();
//        }

        AccountDao.getInstance();
//        AccountDao.getInstance().findById(2);
//        AccountDao.getInstance().add(new Account(3,"hihihi",123));
//        Account account = new Account(3, "hihihi" , 123);
//        AccountDao.getInstance().add(account);
//
//        AccountDao.getInstance().delete(account);

    }




    public static void main(String[] args) {
        launch(args);
    }
}

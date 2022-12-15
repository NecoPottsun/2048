package com.example.demo;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The purpose of this class is to handle the text
 * inside the cells in the GameScene
 * consists of created a new Text component for cell,
 * and handles the swaps of two texts when movement
 */
class TextMaker {
    private static TextMaker singleInstance = null;

    /**
     * The constructor of TextMaker
     */
    private TextMaker() {

    }
    /**
     * <code>getInstance()</code>help this TextMaker follows the Singleton, because it only needs to be
     *  created once
     * @return AccountDao
     * */
    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     * Creates a new Text component inside the cell in the GameScene
     * @param input the String inside the Text
     * @param xCell the position of the cell on x-axis
     * @param yCell the position of the cell on y-axis
     * @param root the group root of the cell
     * @return the Text component at (x,y) with text as input in the root
     */
    Text madeText(String input, double xCell, double yCell, Group root) {
        double length = GameScene.getLENGTH();
        double fontSize = (3 * length) / 7.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /**
     * Swaps from Text1 to Text2, and Text2 to Text1
     * @param first the first text that wanted to swap
     * @param second the second text that wanted to swap with
     */
    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}

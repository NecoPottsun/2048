package com.example.demo;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


/**
 * Cell class is created in the GameScene, each cell has (double x, double y, double scale, Group root)
 * that is presented in the GameScene. (x,y) is the position of the Cell, scale is the width and height
 * of the cell
 */
public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    /**
     * This is the constructor of Cell
     * @param x the position of this Cell in x-axis
     * @param y the position of this Cell in y-axis
     * @param scale the width and height of this Cell
     * @param root the root of this Cell
     */
    public Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        this.root = root;
        rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    /**
     * Gets the position of this Cell in x-axis of this Cell
     * @return x
     */
    public double getX() {
        return rectangle.getX();
    }
    /**
     * Gets the position of this Cell in y-axis of this Cell
     * @return y
     */
    public double getY() {
        return rectangle.getY();
    }

    /**
     * Gets the number inside this Cell Text
     * @return number inside this Cell Text
     * <p>
     *     Default value: 0
     * </p>
     */
    public int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     * Gets the Text inside this Cell
     * @return Text of this Cell
     */
    public Text getTextClass() {
        return textClass;
    }

    /**
     * Gets modify value of this Cell
     * @return <code>True</code> if this cell needs modify;
     *         <code>False</code> if this cell no need to modify
     * <p>
     *     Default value: False
     * </p>
     */
    public boolean getModify() {
        return modify;
    }

    /**
     * Sets the textClass of this Cell
     * @param textClass the Text object inside this Cell
     */
    public void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Sets modify of this Cell
     * @param modify the modify value that wanted to change
     */
    public void setModify(boolean modify) {
        this.modify = modify;
    }


    /**
     * Changes the value and the color inside the cell whenever the
     * cell in the GameScene is created or merged
     * @param cell the Cell wanted to be changed
     */
    void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * Handles the merged cells
     *. Whenever the cell is merged with another cell
     * which have the same value, then 2 cells' value sum up,
     * and set the color of the cell by the new value.
     * @param cell the Cell wanted to merge with this Cell
     */
    public void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * This method is set the color to the cell base on the
     * value of the cell in the GameScene
     * @param number the number value of this Cell
     */
    void setColorByNumber(int number) {
        switch (number) {
            case 0:
                rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
                break;
            case 2:
                rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
                break;
            case 4:
                rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
                break;
            case 8:
                rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
                break;
            case 16:
                rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
                break;
            case 32:
                rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
                break;
            case 64:
                rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
                break;
            case 128:
                rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
                break;
            case 256:
                rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
                break;
            case 512:
                rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
                break;
            case 1024:
                rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
                break;
            case 2048:
                rectangle.setFill(Color.rgb(250,0,0,1));


        }

    }



}

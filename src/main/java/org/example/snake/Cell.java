package org.example.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {
    private CellType cellType = CellType.EMPTY;

    private Rectangle rect;

    public Cell(double posX, double posY){
        int width = 20, height = 20;
        rect = new Rectangle(width, height);
        rect.setX(posX);
        rect.setY(posY);
        rect.setFill(Color.LIGHTGRAY);
        rect.setStroke(Color.BLACK);
    }

    public CellType getCellType(){
        return cellType;
    }

    public void setCellType(CellType cellType){
        this.cellType = cellType;
        setCell();
    }

    private void setCell(){
        switch (cellType){
            case EMPTY:
                rect.setFill(Color.LIGHTGRAY);
                rect.setStroke(Color.BLACK);
                break;
            case FOOD:
                rect.setFill(Color.RED);
                rect.setStroke(Color.BLACK);
                break;
            case SNAKE:
                rect.setFill(Color.GREEN);
                rect.setStroke(Color.BLACK);
                break;
            default:
                System.out.println("Not a valid CellType.");
        }
    }

    public Rectangle getRect(){
        return rect;
    }
}

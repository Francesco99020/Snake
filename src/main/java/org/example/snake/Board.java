package org.example.snake;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Board {
    Cell[][] cells = new Cell[20][30]; // 20 rows and 30 columns

    public Board() {
        double posX, posY;
        for (int i = 0; i < cells.length; i++) {
            posY = i * 20;
            for (int j = 0; j < cells[i].length; j++) {
                posX = j * 20;
                cells[i][j] = new Cell(posX, posY);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void addNewFoodLocation(int[] pos){
        cells[pos[0]][pos[1]].setCellType(CellType.FOOD);
    }

    public void spawnSnake(int[] pos){
        cells[pos[0]][pos[1]].setCellType(CellType.SNAKE);
    }

    public void updateBoardGraphics(ObservableList<Node> root) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                root.add(cells[i][j].getRect());
            }
        }
    }
}

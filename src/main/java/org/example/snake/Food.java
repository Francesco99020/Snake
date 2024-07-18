package org.example.snake;
public class Food {
    public static int[] newFoodPos(Cell[][] cells){
        int posX = -1, posY = -1;
        while (posX == -1){
            int x = (int) (Math.random() * 20);
            int y = (int) (Math.random() * 30);
            if(cells[x][y].getCellType() == CellType.EMPTY){
                posX = x;
                posY = y;
            }
        }
        return new int[] {posX, posY};
    }
}

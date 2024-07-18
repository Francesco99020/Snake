package org.example.snake;

public class SnakeBodyPart {
    private int x;
    private int y;

    public SnakeBodyPart(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int[] getPosition(){
        return new int[]{x,y};
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}

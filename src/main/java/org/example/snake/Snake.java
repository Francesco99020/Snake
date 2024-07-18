package org.example.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeBodyPart> body;
    private Direction direction = Direction.RIGHT;

    public Snake(int startX, int startY) {
        body = new ArrayList<>();
        body.add(new SnakeBodyPart(startX, startY));
    }

    public int[] getHeadPosition() {
        return body.getFirst().getPosition();
    }

    public int[] move() {
        // Move each body part to the position of the previous one
        for (int i = body.size() - 1; i > 0; i--) {
            int[] prevPosition = body.get(i - 1).getPosition();
            body.get(i).setPosition(prevPosition[0], prevPosition[1]);
        }

        // Move the head in the current direction
        int[] headPosition = body.getFirst().getPosition();
        switch (direction) {
            case UP:
                body.getFirst().setPosition(headPosition[0] - 1, headPosition[1]);
                break;
            case DOWN:
                body.getFirst().setPosition(headPosition[0] + 1, headPosition[1]);
                break;
            case LEFT:
                body.getFirst().setPosition(headPosition[0], headPosition[1] - 1);
                break;
            case RIGHT:
                body.getFirst().setPosition(headPosition[0], headPosition[1] + 1);
                break;
        }
        return new int[] {body.getFirst().getPosition()[0], body.getFirst().getPosition()[1]};
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<SnakeBodyPart> getBody() {
        return body;
    }

    public void addNewSnakeBody(int x, int y){
        body.add(new SnakeBodyPart(x, y));
    }
}
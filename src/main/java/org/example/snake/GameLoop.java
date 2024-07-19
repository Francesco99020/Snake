package org.example.snake;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class GameLoop extends AnimationTimer {
    private final Board board;
    private final Snake snake;
    private final Game game;
    private final ObservableList<Node> rootChildren;
    private long lastUpdate = 0;
    private static final long UPDATE_INTERVAL = 200_000_000; // Update every 200 milliseconds (5 times per second)

    public GameLoop(Board board, Snake snake, ObservableList<Node> rootChildren, Game game) {
        this.board = board;
        this.snake = snake;
        this.game = game;
        this.rootChildren = rootChildren;
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= UPDATE_INTERVAL) {
            game.resetInputProcessed();
            update();
            draw();
            lastUpdate = now;
        }
    }

    private void update() {
        boolean hasEatenFood = false;
        int[] prevTailPos = snake.getBody().get(snake.getBody().size() - 1).getPosition();

        int[] headPos = snake.move();

        // Check if the head position is within the board boundaries
        if (headPos[0] < 0 || headPos[0] >= board.getCells().length ||
                headPos[1] < 0 || headPos[1] >= board.getCells()[0].length) {
            game.endGame(snake.getBody().size());
            stop();
            return;
        }

        // Check for collisions with food or snake itself
        CellType headCellType = board.getCells()[headPos[0]][headPos[1]].getCellType();
        if (headCellType == CellType.FOOD) {
            hasEatenFood = true;
        } else if (headCellType == CellType.SNAKE) {
            game.endGame(snake.getBody().size());
            stop();
            return;
        }

        // Update the board with the snake's body parts
        for (SnakeBodyPart bodyPart : snake.getBody()) {
            int[] bodyPosition = bodyPart.getPosition();
            board.getCells()[bodyPosition[0]][bodyPosition[1]].setCellType(CellType.SNAKE);
        }

        // Remove tail of snake if food not eaten
        if (!hasEatenFood) {
            board.getCells()[prevTailPos[0]][prevTailPos[1]].setCellType(CellType.EMPTY);
        } else {
            snake.addNewSnakeBody(prevTailPos[0], prevTailPos[1]);
            board.addNewFoodLocation(Food.newFoodPos(board.getCells()));
        }
    }

    private void draw() {
        // Clear the root children and redraw the board
        rootChildren.clear();
        board.updateBoardGraphics(rootChildren);
    }
}

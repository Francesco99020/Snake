package org.example.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    private boolean inputProcessed = false;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Board board = new Board();
        Snake snake = new Snake(5, 5);

        board.spawnSnake(snake.getHeadPosition());
        board.addNewFoodLocation(Food.newFoodPos(board.getCells()));
        board.updateBoardGraphics(root.getChildren());

        Scene scene = new Scene(root, 600, 400);

        scene.setOnKeyPressed(event -> handleKeyPress(event, snake));

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Make the window non-resizable
        primaryStage.show();

        GameLoop gameLoop = new GameLoop(board, snake, root.getChildren(), this);
        gameLoop.start();
    }

    public void resetInputProcessed() {
        inputProcessed = false; // Reset the flag at the beginning of each frame
    }

    private void handleKeyPress(KeyEvent event, Snake snake) {
        if (inputProcessed) return; // Ignore if input has already been processed this frame

        KeyCode code = event.getCode();
        Direction currentDirection = snake.getDirection();

        switch (code) {
            case W, UP -> {
                if (currentDirection != Direction.DOWN) {
                    snake.setDirection(Direction.UP);
                    inputProcessed = true;
                }
            }
            case S, DOWN -> {
                if (currentDirection != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                    inputProcessed = true;
                }
            }
            case A, LEFT -> {
                if (currentDirection != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                    inputProcessed = true;
                }
            }
            case D, RIGHT -> {
                if (currentDirection != Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                    inputProcessed = true;
                }
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
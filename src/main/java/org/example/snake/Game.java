package org.example.snake;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
    private Scene gameScene;
    private Scene titleScene;
    private Scene gameOverScene;
    private Stage primaryStage;
    private int score;
    private boolean inputProcessed = false;
    private GameLoop gameLoop;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        titleScene = createTitleScene();

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(titleScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Scene createGameOverScene() {
        StackPane root = new StackPane();
        Text gameOverText = new Text("Game Over");
        Text scoreText = new Text("Score: " + score);
        Button restartButton = new Button("Restart");
        Button titleButton = new Button("Back to Title");

        restartButton.setOnAction(e -> startGame());
        titleButton.setOnAction(e -> primaryStage.setScene(titleScene));

        root.getChildren().addAll(gameOverText, scoreText, restartButton, titleButton);
        StackPane.setAlignment(gameOverText, Pos.CENTER);
        StackPane.setAlignment(scoreText, Pos.TOP_CENTER);
        StackPane.setAlignment(restartButton, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(titleButton, Pos.BOTTOM_RIGHT);
        return new Scene(root, 600, 400);
    }

    private void startGame() {
        score = 0;
        gameScene = createGameScene();
        primaryStage.setScene(gameScene);
    }

    public void endGame(int finalScore) {
        score = finalScore;

        // Stop the game loop if it is running
        if (gameLoop != null) {
            gameLoop.stop();
        }

        gameOverScene = createGameOverScene();
        primaryStage.setScene(gameOverScene);
    }

    private Scene createTitleScene() {
        StackPane root = new StackPane();
        Text title = new Text("Snake Game");
        Button startButton = new Button("Start Game");
        Button exitButton = new Button("Exit");

        startButton.setOnAction(e -> startGame());
        exitButton.setOnAction(e -> primaryStage.close()); // Close the application

        root.getChildren().addAll(title, startButton, exitButton);
        StackPane.setAlignment(title, Pos.CENTER);
        StackPane.setAlignment(startButton, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        return new Scene(root, 600, 400);
    }

    private Scene createGameScene() {
        Pane root = new Pane();
        Board board = new Board();
        Snake snake = new Snake(5, 5);

        board.spawnSnake(snake.getHeadPosition());
        board.addNewFoodLocation(Food.newFoodPos(board.getCells()));
        board.updateBoardGraphics(root.getChildren());

        Scene scene = new Scene(root, 600, 400);

        scene.setOnKeyPressed(event -> handleKeyPress(event, snake));

        gameLoop = new GameLoop(board, snake, root.getChildren(), this);
        gameLoop.start();

        return scene;
    }

    public void resetInputProcessed() {
        inputProcessed = false;
    }

    private void handleKeyPress(KeyEvent event, Snake snake) {
        if (inputProcessed) return;

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
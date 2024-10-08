package com.example.colorgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainApplication extends Application {

    // Set up cube variables
    Cube cube1;
    Cube cube2;
    Cube cube3;
    Cube cube4;

    // Set up Order Class Variable
    Order order;

    // Boolean for checking if you pressed all the correct cubes
    boolean allCubesCorrect;

    // Start button
    Button startButton = new Button("Start");

    // Text for showing the players score
    PointCounter pointCounter;

    // Pane
    Pane rod;

    // Colors
    CustomColors customColors = new CustomColors();

    // Label for Game Over message
    Label gameOverLabel;

    // Main Start
    @Override
    public void start(Stage stage) throws IOException {

        // Set up our scene
        rod = new Pane();
        Scene scene = new Scene(rod, 500, 600);
        stage.setTitle("Color Memory Game");
        scene.setFill(customColors.bgColor);
        stage.setScene(scene);
        stage.show();

        // Set up the start button
        startButton.setLayoutX(215);
        startButton.setLayoutY(525);
        rod.getChildren().add(startButton);
        startButton.setOnAction(event -> {startGame();});

        // Set up the point counter class
        pointCounter = new PointCounter(rod);

        // Setup 4 cubes
        cube1 = new Cube(rod, 125, 100, customColors.cubeRed, 0);
        cube2 = new Cube(rod, 250, 100, customColors.cubeYellow, 1);
        cube3 = new Cube(rod, 125, 300, customColors.cubeGreen, 2);
        cube4 = new Cube(rod, 250, 300, customColors.cubeBlue, 3);

        /*
        Create mouse events for pressing a cube. Mouse events are created in the main class, so it's easier to
          do different methods in other classes.
        */
        cube1.setOnMouseClicked(mouseEvent -> pressedCube(cube1));
        cube2.setOnMouseClicked(mouseEvent -> pressedCube(cube2));
        cube3.setOnMouseClicked(mouseEvent -> pressedCube(cube3));
        cube4.setOnMouseClicked(mouseEvent -> pressedCube(cube4));

        // Set up the order variable with our new cubes
        order = new Order(this, cube1, cube2, cube3, cube4);

        // Set up Game Over label (but don't show it yet)
        gameOverLabel = new Label("Game Over!");
        gameOverLabel.setFont(new Font("Arial", 48));
        gameOverLabel.setTextFill(Color.RED);
        gameOverLabel.setLayoutX(100); // Centering manually
        gameOverLabel.setLayoutY(250);
        gameOverLabel.setTextAlignment(TextAlignment.CENTER);
        gameOverLabel.setVisible(false);  // Hidden initially
        rod.getChildren().add(gameOverLabel);
    }

    // Start game (or restart after game over)
    public void startGame() {
        // Hide Game Over message if it's visible
        gameOverLabel.setVisible(false);

        // Reset cubes and points
        cube1.setVisible(true);
        cube2.setVisible(true);
        cube3.setVisible(true);
        cube4.setVisible(true);
        pointCounter.setPoints(0);

        // Start the game again
        order.addNewCubeToOrder();
        order.playAllCubes();

        // Reset button text
        startButton.setText("Start");
    }

    // Disable pressing the cubes
    public void disableAllCubes() {
        cube1.setDisable(true);
        cube2.setDisable(true);
        cube3.setDisable(true);
        cube4.setDisable(true);
    }

    // Enable pressing the cubes
    public void enableAllCubes() {
        cube1.setDisable(false);
        cube2.setDisable(false);
        cube3.setDisable(false);
        cube4.setDisable(false);
    }

    // Method for what happens when pressing a cube
    public void pressedCube(Cube cubePressed) {
        cubePressed.scaleCube(cubePressed, true);

        // Make new scheduler to make an animation delay after you have pressed all the right cubes
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Check if the correct cube is pressed
        if (cubePressed.getCubeID(cubePressed) == order.tempOrderArray.getFirst()) {
            //System.out.println("Correct Cube Pressed");
            order.tempOrderArray.removeFirst();
            if (order.tempOrderArray.size() == 0) {
                allCubesCorrect = true;
                cubePressed.disablePress();
                order.addNewCubeToOrder();
                order.resetTempOrder();
                pointCounter.addPoint();

                // When all methods are executed, wait 2 second and run the method "playAllCubes"
                scheduler.schedule(() -> {
                    order.playAllCubes();
                }, 2, TimeUnit.SECONDS);
            }
        }
        else {
            lostGame();
        }
    }

    // Lost Game method
    public void lostGame() {
        allCubesCorrect = false;

        // Show Game Over message
        gameOverLabel.setVisible(true);

        // Update start button text to "Restart Game"
        startButton.setText("Restart Game");
        startButton.setVisible(true);

        // Hide cubes and reset game state
        cube1.setVisible(false);
        cube2.setVisible(false);
        cube3.setVisible(false);
        cube4.setVisible(false);
        pointCounter.setPoints(0);

        // Reset order arrays
        order.orderArray.clear();
        order.tempOrderArray.clear();
    }

    // Main
public static void main(String[] args) {
    launch();
    }
}
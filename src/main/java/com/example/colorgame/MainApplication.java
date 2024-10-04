package com.example.colorgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainApplication extends Application {

    // Cube Colors
    Color cubeRed = Color.web("EF476F");
    Color cubeBlue = Color.web("118AB2");
    Color cubeGreen = Color.web("06D6A0");
    Color cubeYellow = Color.web("FFD166");

    // Background color
    Color bgColor = Color.web("073b4c");

    // Set up cube variables
    Cube cube1;
    Cube cube2;
    Cube cube3;
    Cube cube4;

    // Set up Order Class Variable
    Order order;

    // Boolean for checking if you pressed all the correct cubes
    boolean allCubesCorrect;

    // Main Start
    @Override
    public void start(Stage stage) throws IOException {

        // Set up our scene
        Pane rod = new Pane();
        Scene scene = new Scene(rod, 500, 600);
        scene.setFill(bgColor);
        stage.setScene(scene);
        stage.setTitle("Klik Firkant");
        stage.show();

        // Setup 4 cubes
        cube1 = new Cube(rod, 125, 100, cubeRed, 0);
        cube2 = new Cube(rod, 250, 100, cubeYellow, 1);
        cube3 = new Cube(rod, 125, 300, cubeGreen, 2);
        cube4 = new Cube(rod, 250, 300, cubeBlue, 3);

        /*Create mouse events for pressing a cube. Mouse events are created in the main class, so it's easier to
          do different methods in other classes.
        */
        cube1.setOnMouseClicked(mouseEvent -> pressedCube(cube1));
        cube2.setOnMouseClicked(mouseEvent -> pressedCube(cube2));
        cube3.setOnMouseClicked(mouseEvent -> pressedCube(cube3));
        cube4.setOnMouseClicked(mouseEvent -> pressedCube(cube4));

        // Set up the order variable with our new cubes
        order = new Order(this, cube1, cube2, cube3, cube4);

        order.addNewCubeToOrder();
        order.playAllCubes();
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

        //System.out.println("Cube Pressed:" + cubePressed.getCubeID(cubePressed));
        //System.out.println("Last in Array: " + order.tempOrderArray.getLast());

        // Make new scheduler to make a animation delay after you have pressed all the right cubes
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

    // Method for losing the game
    public void lostGame() {
        allCubesCorrect = false;
        System.out.println("You Lost!");
    }

    // Main
public static void main(String[] args) {
    launch();
    }
}
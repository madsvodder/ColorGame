package com.example.colorgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HelloApplication extends Application {

    Order order;

    boolean allCubesCorrect;

    boolean readyToPlay = true;

    @Override
    public void start(Stage stage) throws IOException {

        // Set up our scene
        Pane rod = new Pane();
        Scene scene = new Scene(rod, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Klik Firkant");
        stage.show();

        // Setup 4 cubes
        Cube cube1 = new Cube(rod, 100, 100, Color.BLACK, 0);
        Cube cube2 = new Cube(rod, 250, 100, Color.BLUE, 1);
        Cube cube3 = new Cube(rod, 100, 250, Color.RED, 2);
        Cube cube4 = new Cube(rod, 250, 250, Color.GREEN, 3);

        /*Create mouse events for pressing a cube. Mouse events are created in the main class, so it's easier to
          do different methods in other classes.
        */
        cube1.setOnMouseClicked(mouseEvent -> pressedCube(cube1));
        cube2.setOnMouseClicked(mouseEvent -> pressedCube(cube2));
        cube3.setOnMouseClicked(mouseEvent -> pressedCube(cube3));
        cube4.setOnMouseClicked(mouseEvent -> pressedCube(cube4));

        // Set up the order variable with our new cubes
        order = new Order(cube1, cube2, cube3, cube4);

        order.addNewCubeToOrder();
        order.playAllCubes();
    }

    public void pressedCube(Cube cubePressed) {
        cubePressed.scaleCube(cubePressed, true);

        System.out.println("Cube Pressed:" + cubePressed.getCubeID(cubePressed));
        System.out.println("Last in Array: " + order.tempOrderArray.getLast());

        // Make new scheduler to make a animation delay after you have pressed all the right cubes
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Check if the correct cube is pressed
        if (cubePressed.getCubeID(cubePressed) == order.tempOrderArray.getFirst()) {
            System.out.println("Correct Cube Pressed");
            order.tempOrderArray.removeFirst();
            if (order.tempOrderArray.size() == 0) {
                allCubesCorrect = true;
                order.addNewCubeToOrder();
                order.resetTempOrder();

                // When all methods are executed, wait 1 second and run the method "playAllCubes"
                scheduler.schedule(() -> {
                    order.playAllCubes();
                }, 1, TimeUnit.SECONDS);
            }
        }
    }

public static void main(String[] args) {
    launch();
    }
}
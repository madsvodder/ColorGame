package com.example.colorgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    Order order;

    @Override
    public void start(Stage stage) throws IOException {

        // Set up our scene
        Pane rod = new Pane();
        Scene scene = new Scene(rod, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Klik Firkant");
        stage.show();

        // Setup 4 cubes
        Cube cube1 = new Cube(rod, 100, 100, Color.BLACK);
        Cube cube2 = new Cube(rod, 250, 100, Color.BLUE);
        Cube cube3 = new Cube(rod, 100, 250, Color.RED);
        Cube cube4 = new Cube(rod, 250, 250, Color.GREEN);

        /*Create mouse events for pressing a cube. Mouse events are created in the main class, so it's easier to
          do different methods in other classes.
        */
        cube1.setOnMouseClicked(mouseEvent -> cube1.scaleCube(cube1, true));
        cube2.setOnMouseClicked(mouseEvent -> cube2.scaleCube(cube2, true));
        cube3.setOnMouseClicked(mouseEvent -> cube3.scaleCube(cube3, true));
        cube4.setOnMouseClicked(mouseEvent -> cube4.scaleCube(cube4, true));

        // Set up the order variable with our new cubes
        order = new Order(cube1, cube2, cube3, cube4);
    }

    public void pressedCube() {
    }

    public static void main(String[] args) {
        launch();

    }
}
package com.example.colorgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
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

        // Set up the order class with our pre-made cubes
        Order order = new Order(cube1, cube2, cube3, cube4);

        // Testing for adding new cubes and playing the transitions
        order.addNewCubeToOrder();
        order.playAllCubes();

    }

    public static void main(String[] args) {
        launch();
    }
}
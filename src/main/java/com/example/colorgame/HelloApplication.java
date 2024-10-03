package com.example.colorgame;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        int x = 10;
        int y = 10;

        Pane rod = new Pane();

        Cube cube1 = new Cube(rod, 100, 100, Color.BLACK);
        Cube cube2 = new Cube(rod, 250, 100, Color.BLUE);
        Cube cube3 = new Cube(rod, 100, 250, Color.RED);
        Cube cube4 = new Cube(rod, 250, 250, Color.GREEN);

        Scene scene = new Scene(rod, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Klik Firkant");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
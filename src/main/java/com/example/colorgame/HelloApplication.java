package com.example.colorgame;

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

        Rectangle Cube1 = new Rectangle(100, 100, 100, 100);
        Rectangle Cube2 = new Rectangle(250, 100, 100, 100);
        Rectangle Cube3 = new Rectangle(100, 250, 100, 100);
        Rectangle Cube4 = new Rectangle(250, 250, 100, 100);

        Cube1.setFill(Color.BLUE);
        Cube2.setFill(Color.INDIANRED);
        Cube3.setFill(Color.YELLOW);
        Cube4.setFill(Color.GREEN);

        Cube1.setOnMouseClicked(event -> {scaleCube(Cube1);});
        Cube2.setOnMouseClicked(event -> {scaleCube(Cube2);});
        Cube3.setOnMouseClicked(event -> {scaleCube(Cube3);});
        Cube4.setOnMouseClicked(event -> {scaleCube(Cube4);});

        Pane rod = new Pane();
        rod.getChildren().addAll(Cube1, Cube2, Cube3, Cube4);

        Scene scene = new Scene(rod, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Klik Firkant");
        stage.show();

        //scaleCube(Cube2);
    }

    public void scaleCube(Node cube) {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.seconds(0.5));
        scaleTransition.setNode(cube);
        scaleTransition.setByY(0.2);
        scaleTransition.setByX(0.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

    }

    public static void main(String[] args) {
        launch();
    }
}
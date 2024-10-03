package com.example.colorgame;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Cube extends Rectangle {



    public Cube(Pane rod, int x, int y, Color color) {
        Rectangle Cube = new Rectangle(x, y, 100, 100);
        rod.getChildren().add(Cube);
        Cube.setFill(color);
        Cube.setOnMouseClicked(mouseEvent -> scaleCube(Cube));
    }

    public void scaleCube(Node node) {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        scaleTransition.setDuration(Duration.seconds(0.5));
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.setNode(node);
        scaleTransition.setByY(0.2);
        scaleTransition.setByX(0.2);
        scaleTransition.play();
    }
}



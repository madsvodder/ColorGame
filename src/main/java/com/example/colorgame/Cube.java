package com.example.colorgame;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Cube extends Rectangle {

    public int cubeID;

    public Cube(Pane rod, int x, int y, Color color, int cubeID) {
        super(x, y, 100, 100);
        rod.getChildren().add(this);
        this.setFill(color);
        this.cubeID = cubeID;

        //this.setOnMouseClicked(mouseEvent -> scaleCube(this, true));
    }

    public int getCubeID(Cube cubePressed) {
        return cubeID;
    }

    // Metode til at skalere kuben (eller andre noder) og returnere ScaleTransition
    public ScaleTransition scaleCube(Node node, boolean mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        scaleTransition.setDuration(Duration.seconds(0.5));
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.setNode(node);
        scaleTransition.setByY(0.2);
        scaleTransition.setByX(0.2);
        if (mouseEvent == true) {
            scaleTransition.play();
        }
        return scaleTransition; // Returner ScaleTransition i stedet for at starte den
    }
}

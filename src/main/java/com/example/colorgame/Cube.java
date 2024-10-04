package com.example.colorgame;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Cube extends Rectangle {

    public int initialWidth = 100;
    public int initialHeight = 175;
    public int cubeID;
    public boolean isTransitionPlaying = false;
    private double maxCubeScale = 1.2;

    public Cube(Pane rod, int x, int y, Color color, int cubeID) {
        super(x, y, 100, 175);
        this.setWidth(initialWidth);
        this.setHeight(initialHeight);
        this.setFill(color);
        this.cubeID = cubeID;
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(5);

        // Add Cube To Scene
        rod.getChildren().add(this);
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
        scaleTransition.setByY(0.2);
        scaleTransition.setByX(0.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.setNode(node);
        setIsTransitionPlaying(true);
        if (mouseEvent == true) {
            scaleTransition.play();
            setIsTransitionPlaying(true);
        }
        return scaleTransition; // Returner ScaleTransition i stedet for at starte den
    }

    public void setIsTransitionPlaying(boolean isTransitionPlaying) {
        this.isTransitionPlaying = isTransitionPlaying;
    }

    public boolean getIsTransitionPlaying() {
        return isTransitionPlaying;
    }

    public void enablePress() {
        this.setDisable(false);
    }

    public void disablePress() {
        this.setDisable(true);
    }

}

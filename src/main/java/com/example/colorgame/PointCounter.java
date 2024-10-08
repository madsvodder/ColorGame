package com.example.colorgame;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PointCounter extends Label {
    private int points;
    private Label pointLabel;

    public PointCounter(Pane rod) {
        this.points = 0;

        // Set up the label for points
        pointLabel = new Label();
        pointLabel.setLayoutX(215);
        pointLabel.setLayoutY(50);
        pointLabel.setFont(new Font("Arial", 20));
        pointLabel.setText("Points: " + points);
        rod.getChildren().add(pointLabel);
    }

    public void addPoint() {
        this.points++;
        System.out.println("Points: " + points);
        pointLabel.setText("Points: " + points);
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
        pointLabel.setText("Points: " + points);
    }
}

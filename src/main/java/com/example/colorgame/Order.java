package com.example.colorgame;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Order {

    // Create Array of Cube Order
    ArrayList<Integer> orderArray = new ArrayList<>(); // Create an ArrayList object

    // Create Array To Keep Track of Cubes
    ArrayList<Cube> cubesArray = new ArrayList<>();  // Angiver, at det er en liste af Cube-objekter

    // create instance of Random class
    Random randomCube = new Random();

    public Order(Cube cube1, Cube cube2, Cube cube3, Cube cube4) {
        cubesArray.add(cube1);
        cubesArray.add(cube2);
        cubesArray.add(cube3);
        cubesArray.add(cube4);
    }

    // Add a New Cube To The Order
    public void addNewCubeToOrder() {
        int randomNumber = randomCube.nextInt(4);
        orderArray.add(randomNumber);
        System.out.println("randomNumber: " + randomNumber);
    }

    public void playAllCubes() {
        SequentialTransition sequentialTransition = new SequentialTransition(); // Opret en SequentialTransition

        for (int i = 0; i < orderArray.size(); i++) {
            int x = orderArray.get(i);
            Cube cube = cubesArray.get(x);

            // Opret en ScaleTransition
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setDuration(Duration.seconds(0.5));
            scaleTransition.setNode(cube);
            scaleTransition.setByX(0.2);
            scaleTransition.setByY(0.2);
            scaleTransition.setAutoReverse(true);
            scaleTransition.setCycleCount(2);

            sequentialTransition.getChildren().add(scaleTransition); // Tilføj til den sekventielle overgang
        }

        sequentialTransition.play(); // Start sekventiel overgang
    }
}

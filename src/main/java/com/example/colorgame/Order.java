package com.example.colorgame;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Order {

    MainApplication mainApp;

    // Create Array of Cube Order
    ArrayList<Integer> orderArray = new ArrayList<>();

    // Create a temporary array, so its easy to keep track of which cubes have been pressed.
    ArrayList<Integer> tempOrderArray = new ArrayList<>();

    // Create Array To Keep Track of Cubes
    ArrayList<Cube> cubesArray = new ArrayList<>();

    // create instance of Random class
    Random randomCube = new Random();

    public Order(MainApplication mainApplication,Cube cube1, Cube cube2, Cube cube3, Cube cube4) {
        mainApp = mainApplication;
        cubesArray.add(cube1);
        cubesArray.add(cube2);
        cubesArray.add(cube3);
        cubesArray.add(cube4);
    }

    public void resetTempOrder() {
        tempOrderArray.clear();
        tempOrderArray.addAll(orderArray);
    }

    // Add a New Cube To The Order
    public void addNewCubeToOrder() {
        int randomNumber = randomCube.nextInt(4);
        orderArray.add(randomNumber);
        tempOrderArray.add(randomNumber);
        int x = randomNumber;
        //System.out.println("Firkant Nummer: " + x + "\2" + "Order: " + orderArray.get(0));
    }

    public void playAllCubes() {

        // Opret en SequentialTransition
        SequentialTransition sequentialTransition = new SequentialTransition();

        for (int i = 0; i < orderArray.size(); i++) {
            int x = orderArray.get(i);
            Cube cubeToAnimate = cubesArray.get(x);

            // Få ScaleTransition fra cube
            ScaleTransition scaleTransition = cubeToAnimate.scaleCube(cubeToAnimate, false);

            // Tilføj ScaleTransition fra cube til sequentialTransition
            sequentialTransition.getChildren().add(scaleTransition);
        }

        // Play Animations For All Cubes In The Correct order
        sequentialTransition.play();

        // Disable touching the cubes, while the order is being played
        mainApp.disableAllCubes();

        // Enable touching the cubes again, after the order is done playing
        sequentialTransition.setOnFinished(event -> {
            mainApp.enableAllCubes();
            System.out.println("Finished");
        });
    }
}

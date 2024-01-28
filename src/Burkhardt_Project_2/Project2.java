package Burkhardt_Project_2;

/**
 * UMGC CMSC 315
 * Project 2
 * Class that will read in a file and create an array of points.
 * After will display these points on a javaFX GUI and find the
 * Maximal Values using sort and built in calculations.
 * After finding maximal values will draw a line connecting each point.
 * @author Ryan Burkhardt
 * Date: 26Feb2024
 * Java 21
*/

//Import Necessary Classes
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

//Create our Application by extending application
public class Project2 extends Application {

    // Override our stage class and add our Pane Class
    @Override
    public void start(Stage primaryStage) {

        ThePane pointPane = new ThePane();// Create Pane
        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pointPane);
        primaryStage.setTitle("Project 2"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        // Read in file from point pane method
        pointPane.readPointsFromFile(
                "C:/Users/Ryan Burkhardt/iCloudDrive/Desktop/CMSC315/src/Burkhardt_Project_2/points.txt");

    }

    // Create the Point Class
    public static class ThePoint {

        // State our variables
        private double x;
        private double y;

        // Initialize the Point Object
        public ThePoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // return x
        public double getX() {
            return x;
        }

        // return y
        public double getY() {
            return y;
        }

        // Returns true if point is below and to the left of second point
        public boolean isBelowToLeft(ThePoint point2) {
            return y > point2.y && x > point2.x;
        }

        // compares two points x value
        public int compareTo(ThePoint point2) {
            int cmp = Double.compare(x, point2.x);
            return cmp;
        }
    }

    // Create the pane class
    public static class ThePane extends Pane {

        // Initialize our two Lists to store values
        private List<ThePoint> pointsList = new ArrayList<>();
        private List<ThePoint> maxList = new ArrayList<>();

        // Create the Pane
        public ThePane() {

            // Set preffered size to 500*500
            setPrefSize(500, 500);

            // Set our mouse clicks to add or remove a Point from our list
            setOnMouseClicked(event -> {// Add points and update max list
                if (event.getButton() == MouseButton.PRIMARY) {
                    addPoint(event.getX(), event.getY());
                    findMaxSet();

                    // Draw our points and lines
                    drawPoints();
                    drawLines();

                    // Remove a point from our list and update max list and do the same draw
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    removePoint(event.getX(), event.getY());
                    findMaxSet();
                    drawPoints();
                    drawLines();
                }
            });

        }

        // method to add points to our list with location
        private void addPoint(double x, double y) {
            pointsList.add(new ThePoint(x, y));
        }

        // Adds the method to remove a point from points and maxlist in the vicinity of
        // the click
        // If points are within 5 pixels of x and y locations they will be removed
        private void removePoint(double x, double y) {
            pointsList.removeIf(point -> point.getX() == x && point.getY() == y);
            pointsList.removeIf(point -> Math.abs(point.getX() - x) < 5 && Math.abs(point.getY() - y) < 5);
            maxList.removeIf(point -> point.getX() == x && point.getY() == y);
            maxList.removeIf(point -> Math.abs(point.getX() - x) < 5 && Math.abs(point.getY() - y) < 5);
        }

        // Creates the method to make the max list on every point addition
        private void findMaxSet() {
            // clears out max list to avoid duplicates
            maxList.clear();

            // Creates a for loop to run each point through a test
            for (ThePoint point : pointsList) {

                // Creates the boolean to see if it is a max value
                boolean isMax = true;

                // Creates a for loop to see if there is a point above and to the right
                for (ThePoint other : pointsList) {

                    // If there is a point then it will not be considered as a max value and set the
                    // boolean to false
                    if (point.getY() > other.getY() && other.getX() >= point.getX()) {
                        isMax = false;
                        break;
                    }
                }

                // Will add point to max if boolean is found to be true
                if (isMax) {
                    maxList.add(point);
                }
            }

            // Sorts by the y vlaue to ensure the lowest value of x is to the left
            maxList.sort(Comparator.comparingDouble(p -> p.y));

        }

        // Will draw the points
        private void drawPoints() {
            // Removes all previous points from screen
            getChildren().removeIf(node -> node instanceof Circle);
            // Will add points to Application
            for (ThePoint point : pointsList) {
                getChildren().add(new Circle(point.getX(), point.getY(), 5, Color.BLACK));
            }
        }

        // Draws lines on Application
        private void drawLines() {
            getChildren().removeIf(node -> node instanceof Line);
            if (!maxList.isEmpty()) {

                // Draw line from x=0 to first max point
                Line startLine = new Line(0, maxList.get(0).getY(), maxList.get(0).getX(), maxList.get(0).getY());
                startLine.setStroke(Color.BLACK);
                startLine.setStrokeWidth(2);
                getChildren().add(startLine);

                // Connect max points by their x and y-coordinates, draws a straight line and
                // horizontal line
                for (int i = 0; i < maxList.size() - 1; i++) {
                    ThePoint point1 = maxList.get(i);
                    ThePoint point2 = maxList.get(i + 1);
                    Line verticalLine = new Line(point1.getX(), point1.getY(), point1.getX(), point2.getY());
                    Line horizontalLine = new Line(point1.getX(), point2.getY(), point2.getX(), point2.getY());
                    verticalLine.setStroke(Color.BLACK);
                    verticalLine.setStrokeWidth(2);
                    horizontalLine.setStroke(Color.BLACK);
                    horizontalLine.setStrokeWidth(2);
                    getChildren().add(verticalLine);
                    getChildren().add(horizontalLine);
                }

                // Draw line from last max point to y=500
                ThePoint lastPoint = maxList.get(maxList.size() - 1);
                Line line2 = new Line(lastPoint.getX(), lastPoint.getY(), lastPoint.getX(), 500);
                line2.setStroke(Color.BLACK);
                line2.setStrokeWidth(2);
                getChildren().add(line2);
            }
        }

        // Add a method to read points from a text file
        public void readPointsFromFile(String filePath) {
            try {
                // Reads all lines in our file
                List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
                // Creates a for loop to read each line
                for (String line : lines) {
                    // Will use split to create our point assuming data is inputted properly
                    String[] parts = line.split(" ");
                    // When there are two numbers as strings it will create our point
                    if (parts.length == 2) {
                        double x = Double.parseDouble(parts[0]);// Assign X
                        double y = Double.parseDouble(parts[1]);// Assign Y
                        pointsList.add(new ThePoint(x, y));// Add to points List
                        findMaxSet();// Finds Max Values
                        drawPoints();// Draws the points
                        drawLines();// Draws the lines
                    }
                }
                // Will catch a read file exception and print out an error message
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Adding a test comment for github

    public static void main(String[] args) {
        launch(args);
    }

}

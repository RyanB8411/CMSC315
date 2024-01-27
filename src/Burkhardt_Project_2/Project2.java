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
        pointPane.readPointsFromFile(
                "C:/Users/Ryan Burkhardt/iCloudDrive/Desktop/CMSC315/src/Burkhardt_Project_2/points.txt");

    }

    // Create the Point Class
    public static class Point {

        // State our variables
        private double x;
        private double y;

        // Initialize the Point Object
        public Point(double x, double y) {
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
        public boolean isBelowToLeft(Point point2) {
            return y > point2.y && x > point2.x;
        }

        public int compareTo(Point point2) {
            int cmp = Double.compare(x, point2.x);
            return cmp;
        }
    }

    public static class ThePane extends Pane {
        private List<Point> points = new ArrayList<>();

        public ThePane() {
            setPrefSize(500, 500);
            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    addPoint(event.getX(), event.getY());
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    removePoint(event.getX(), event.getY());
                }
                findMaximalSet();
                drawPoints();
            });
            findMaximalSet();

        }

        private void addPoint(double x, double y) {
            points.add(new Point(x, y));
        }

        private void removePoint(double x, double y) {
            points.removeIf(point -> point.getX() == x && point.getY() == y);
            points.removeIf(point -> Math.abs(point.getX() - x) < 5 && Math.abs(point.getY() - y) < 5);
        }

        private void findMaximalSet() {
            List<Point> maximalSet = new ArrayList<>();
            for (Point point : points) {
                if (maximalSet.isEmpty() || point.compareTo(maximalSet.get(maximalSet.size() - 1)) > 0) {
                    maximalSet.add(point);
                }
            }
            Comparator<Point> MaxOrder = Comparator.comparingDouble(p -> p.x);
            maximalSet.sort(MaxOrder);
            System.out.println(maximalSet);
            for (int i = 0; i < maximalSet.size() - 1; i++) {
                drawLines(maximalSet.get(i), maximalSet.get(i + 1));
            }
            maximalSet.clear();
        }

        private void drawPoints() {
            getChildren().clear();
            for (Point point : points) {
                getChildren().add(new Circle(point.getX(), point.getY(), 5, Color.ORCHID));
            }
        }

        private void drawLines(Point point1, Point point2) {
            Line line = new Line(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            line.setStroke(Color.RED);
            line.setStrokeWidth(2);
            getChildren().add(line);
        }

        // Add a method to read points from a text file
        public void readPointsFromFile(String filePath) {
            try {
                List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
                for (String line : lines) {
                    String[] parts = line.split(" ");
                    if (parts.length == 2) {
                        double x = Double.parseDouble(parts[0]);
                        double y = Double.parseDouble(parts[1]);
                        points.add(new Point(x, y));
                        findMaximalSet();
                        drawPoints();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
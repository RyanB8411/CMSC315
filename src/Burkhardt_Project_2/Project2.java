package Burkhardt_Project_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Project2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        PointPane pointPane = new PointPane();
        pointPane.setStyle("-fx-border-color: blue");

        BorderPane pane = new BorderPane();
        pane.setCenter(pointPane);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Project 2"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public boolean isBelowAndToTheLeftOf(Point other) {
            return y > other.y && x > other.x;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        public static final Comparator<Point> X_ORDER = Comparator.comparingDouble(p -> p.x);
        public static final Comparator<Point> Y_ORDER = Comparator.comparingDouble(p -> p.y);

        public int compareTo(Point other) {
            int cmp = Double.compare(x, other.x);
            if (cmp == 0) {
                cmp = Double.compare(y, other.y);
            }
            return cmp;
        }
    }

    public static class PointPane extends Pane {
        private List<Point> points = new ArrayList<>();

        public PointPane() {
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
            drawPoints();
        }

        private void addPoint(double x, double y) {
            points.add(new Point(x, y));
        }

        private void removePoint(double x, double y) {
            points.removeIf(point -> point.isBelowAndToTheLeftOf(new Point(x, y)));
        }

        private void findMaximalSet() {
            List<Point> maximalSet = new ArrayList<>();
            for (Point point : points) {
                if (maximalSet.isEmpty() || point.compareTo(maximalSet.get(maximalSet.size() - 1)) > 0) {
                    maximalSet.add(point);
                }
            }
            maximalSet.sort(Point.X_ORDER);
            points.clear();
            points.addAll(maximalSet);
        }

        private void drawPoints() {
            getChildren().clear();
            for (Point point : points) {
                getChildren().add(new Circle(point.getX(), point.getY(), 5, Color.BLACK));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
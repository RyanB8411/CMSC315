import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.*;

public class PointPane extends Pane {
    private List<Point> points = new ArrayList<>();

    public PointPane(List<Point> initialPoints) {
        this.points = new ArrayList<>(initialPoints);
        setPrefSize(500, 500);
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                addPoint(event.getX(), event.getY());
            } else if (event.getButton() == MouseButton.SECONDARY) {
                removePoint(event.getX(), event.getY());
            }
            findMaximalSet();
        });
        findMaximalSet();
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
            if (maximalSet.isEmpty() || point.compareTo(maximalSet.get(0)) > 0) {
                maximalSet.clear();
                maximalSet.add(point);
            } else if (point.compareTo(maximalSet.get(maximalSet.size() - 1)) > 0) {
                maximalSet.add(point);
            }
        }
        Collections.sort(maximalSet, Point.X_ORDER);
        getChildren().clear();
        for (int i = 0; i < maximalSet.size() - 1; i++) {
            Line line = new Line(maximalSet.get(i).getX(), maximalSet.get(i).getY(),
                                maximalSet.get(i + 1).getX(), maximalSet.get(i + 1).getY());
            line.setStroke(Color.BLACK);
            getChildren().add(line);
        }
    }

    public static void main(String[] args) {
        launch(args);
      }
    }
    
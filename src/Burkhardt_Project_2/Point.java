package Burkhardt_Project_2;

import java.util.*;

public class Point implements Comparable<Point> {
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
        return this.y > other.y && this.x > other.x;
    }

    @Override
    public int compareTo(Point other) {
        if (this.x < other.x) {
            return -1;
        } else if (this.x > other.x) {
            return 1;
        } else {
            return 0;
        }
    }

    public static final Comparator<Point> X_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(p1.x, p2.x);
        }
    };
}
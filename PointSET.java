/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }
        points.add(point);
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }
        return points.contains(point);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<Point2D> insidePoints = new ArrayList<>();

        for (Point2D point : points) {
            if (rect.contains(point)) {
                insidePoints.add(point);
            }
        }
        return insidePoints;
    }

    public Point2D nearest(Point2D queryPoint) {
        if (queryPoint == null) {
            throw new IllegalArgumentException();
        }

        Point2D nearestPoint = null;
        double smallestDistance = Double.POSITIVE_INFINITY;

        for (Point2D point : points) {
            double distance = queryPoint.distanceSquaredTo(point);
            if (distance < smallestDistance) {
                smallestDistance = distance;
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }

    public static void main(String[] args) {

    }
}

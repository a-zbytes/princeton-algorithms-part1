/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private static class Node {
        Point2D point;
        Node left;
        Node right;
        Orientation orientation;

        public Node(Point2D point, Orientation orientation) {
            this.point = point;
            this.orientation = orientation;
        }
    }

    private enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    private Node root;
    private int size;

    public KdTree() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }
        root = insert(root, point, Orientation.VERTICAL);
    }

    private Node insert(Node node, Point2D point, Orientation orientation) {
        if (node == null) {
            size++;
            return new Node(point, orientation);
        }

        if (point.equals(node.point)) {
            return node;
        }

        if (node.orientation == Orientation.VERTICAL) {
            if (point.x() < node.point.x()) {
                node.left = insert(node.left, point, Orientation.HORIZONTAL);
            }
            else {
                node.right = insert(node.right, point, Orientation.HORIZONTAL);
            }
        }
        else {
            if (point.y() < node.point.y()) {
                node.left = insert(node.left, point, Orientation.VERTICAL);
            }
            else {
                node.right = insert(node.right, point, Orientation.VERTICAL);
            }
        }

        return node;
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }
        return contains(root, point);
    }

    private boolean contains(Node node, Point2D point) {
        if (node == null) {
            return false;
        }

        if (point.equals(node.point)) {
            return true;
        }

        if (node.orientation == Orientation.VERTICAL) {
            if (point.x() < node.point.x()) {
                return contains(node.left, point);
            }
            else {
                return contains(node.right, point);
            }
        }
        else {
            if (point.y() < node.point.y()) {
                return contains(node.left, point);
            }
            else {
                return contains(node.right, point);
            }
        }
    }

    public void draw() {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        draw(root, rect);
    }

    private void draw(Node node, RectHV rect) {
        if (node == null) {
            return;
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();

        StdDraw.setPenRadius(0.002);

        if (node.orientation == Orientation.VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), rect.ymin(), node.point.x(), rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rect.xmin(), node.point.y(), rect.xmax(), node.point.y());
        }

        RectHV leftRect = leftRect(node, rect);
        RectHV rightRect = rightRect(node, rect);

        draw(node.left, leftRect);
        draw(node.right, rightRect);
    }

    public Iterable<Point2D> range(RectHV queryRect) {
        if (queryRect == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<Point2D> insidePoints = new ArrayList<>();
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);

        range(root, rect, queryRect, insidePoints);
        return insidePoints;
    }

    private void range(Node node, RectHV rect, RectHV queryRect, ArrayList<Point2D> insidePoints) {
        if (node == null) {
            return;
        }

        if (!rect.intersects(queryRect)) {
            return;
        }

        if (queryRect.contains(node.point)) {
            insidePoints.add(node.point);
        }

        RectHV leftRect = leftRect(node, rect);
        RectHV rightRect = rightRect(node, rect);

        range(node.left, leftRect, queryRect, insidePoints);
        range(node.right, rightRect, queryRect, insidePoints);
    }

    public Point2D nearest(Point2D queryPoint) {
        if (queryPoint == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            return null;
        }

        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);

        Nearest nearest = nearest(root, rect, queryPoint, null);

        return nearest.point;
    }

    private Nearest nearest(Node node, RectHV rect, Point2D queryPoint, Nearest nearest) {
        if (node == null) {
            return nearest;
        }

        if (nearest != null && distance(rect, queryPoint) >= nearest.distance) {
            return nearest;
        }

        if (nearest == null || distance(node, queryPoint) < nearest.distance) {
            nearest = new Nearest(node, distance(node, queryPoint));
        }

        RectHV leftRect = leftRect(node, rect);
        RectHV rightRect = rightRect(node, rect);

        double leftDistance = node.left == null ? Double.POSITIVE_INFINITY :
                              distance(leftRect, queryPoint);
        double rightDistance = node.right == null ? Double.POSITIVE_INFINITY :
                               distance(rightRect, queryPoint);

        if (leftDistance < rightDistance) {
            nearest = nearest(node.left, leftRect, queryPoint, nearest);
            nearest = nearest(node.right, rightRect, queryPoint, nearest);
        }
        else {
            nearest = nearest(node.right, rightRect, queryPoint, nearest);
            nearest = nearest(node.left, leftRect, queryPoint, nearest);
        }

        return nearest;
    }

    private double distance(Node node, Point2D queryPoint) {
        return node.point.distanceSquaredTo(queryPoint);
    }

    private double distance(RectHV rect, Point2D queryPoint) {
        return rect.distanceSquaredTo(queryPoint);
    }

    private static class Nearest {
        Point2D point;
        double distance;

        public Nearest(Node node, double distance) {
            this.point = node.point;
            this.distance = distance;
        }
    }

    private RectHV leftRect(Node node, RectHV rect) {
        if (node.orientation == Orientation.VERTICAL) {
            return new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
        }
        else {
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
        }
    }

    private RectHV rightRect(Node node, RectHV rect) {
        if (node.orientation == Orientation.VERTICAL) {
            return new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
        }
        else {
            return new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
        }
    }


    public static void main(String[] args) {

    }
}

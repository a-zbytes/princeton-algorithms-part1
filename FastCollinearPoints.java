/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        Point[] copy = points.clone();
        Arrays.sort(copy);
        for (int i = 0; i < copy.length - 1; i++) {
            if (copy[i].compareTo(copy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        this.points = copy;
        findSegments();
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    private void findSegments() {
        int length = points.length;
        if (length < 2) {
            segments = new LineSegment[0];
            return;
        }
        ArrayList<LineSegment> segmentList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            Point[] copy = points.clone();
            Arrays.sort(copy, points[i].slopeOrder());

            Point smallest = points[i].compareTo(copy[1]) <= 0 ? points[i] : copy[1];
            Point largest = points[i].compareTo(copy[1]) >= 0 ? points[i] : copy[1];

            int count = 1;
            for (int j = 2; j < length;
                 j++) { // can start at 2 since i processed copy[1] and points[i] have been processed
                double currentSlope = points[i].slopeTo(copy[j]);
                double previousSlope = points[i].slopeTo(copy[j - 1]);

                if (currentSlope == previousSlope) {
                    count++;
                    if (copy[j].compareTo(smallest) < 0) {
                        smallest = copy[j];
                    }

                    if (copy[j].compareTo(largest) > 0) {
                        largest = copy[j];
                    }
                }
                else {
                    if (count >= 3) {
                        if (points[i].compareTo(smallest) == 0) {
                            segmentList.add(new LineSegment(smallest, largest));
                        }
                    }
                    count = 1;
                    smallest = points[i].compareTo(copy[j]) <= 0 ? points[i] : copy[j];
                    largest = points[i].compareTo(copy[j]) >= 0 ? points[i] : copy[j];
                }
            }
            if (count >= 3) {
                if (points[i] == smallest) {
                    segmentList.add(new LineSegment(smallest, largest));
                }
            }
        }
        segments = segmentList.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

    }
}
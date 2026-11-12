/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stopwatch;

public class KdTreeming {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Point2D[] points = new Point2D[n];

        for (int i = 0; i < n; i++) {
            double x = Math.random();
            double y = Math.random();

            points[i] = new Point2D(x, y);
        }

        Stopwatch watch = new Stopwatch();
        KdTree kd = new KdTree();

        for (Point2D point : points) {
            kd.insert(point);
        }

        double elapsedTime = watch.elapsedTime();

        System.out.println("Number of points: " + n);
        System.out.println("Construction time: " + elapsedTime + " seconds");

    }
}

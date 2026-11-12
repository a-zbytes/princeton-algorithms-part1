/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stopwatch;

public class NearestNeigborBenchmark {
    public static void main(String[] args) {

        KdTree kd = new KdTree();
        PointSET brute = new PointSET();

        In in = new In(args[0]);

        int n = 0;
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();

            Point2D point = new Point2D(x, y);
            kd.insert(point);
            brute.insert(point);

            n++;
        }

        int queryCount = 1000; // i used 100 earlier
        Point2D[] queries = new Point2D[queryCount];

        for (int i = 0; i < queryCount; i++) {
            queries[i] = new Point2D(Math.random(), Math.random());
        }

        Stopwatch kdWatch = new Stopwatch();
        for (Point2D query : queries) {
            kd.nearest(query);
        }
        double kdTime = kdWatch.elapsedTime();

        Stopwatch bruteWatch = new Stopwatch();
        for (Point2D query : queries) {
            brute.nearest(query);
        }
        double bruteTime = bruteWatch.elapsedTime();

        System.out.println("Number of points: " + n);
        System.out.println("Number of queries: " + queryCount);

        System.out.println("KdTree: ");
        System.out.println("Time: " + kdTime + " seconds");
        System.out.println("Queries per second: " + queryCount / kdTime);

        System.out.println("Brute force: ");
        System.out.println("Time: " + bruteTime + " seconds");
        System.out.println("Queries per second: " + queryCount / bruteTime);
    }
}

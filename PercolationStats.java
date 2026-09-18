/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private int trials;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("n or trials is less than 1");
        }
        this.trials = trials;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);

                percolation.open(row, col);
            }

            double threshold = (double) percolation.numberOfOpenSites() / (n * n);

            thresholds[i] = threshold;
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean() - (1.96) * stddev() / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + (1.96) * stddev() / Math.sqrt(trials);
    }


    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid number of arguments, use 2 arguments");
        }

        int n = toInt(args[0], "n");
        int trials = toInt(args[1], "trials");

        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.printf("95%% confidence interval = [%f , %f]%n", stats.confidenceLo(),
                          stats.confidenceHi());

    }

    private static int toInt(String string, String type) {
        string = string.trim();

        try {
            int value = Integer.parseInt(string);
            return value;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + type + " provided, Enter valid number",
                                               e);
        }
    }
}

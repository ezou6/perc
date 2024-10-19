import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] data;
    private int gridL;
    private int trialNum;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be postiive.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("T must be positive");
        }
        gridL = n;
        trialNum = trials;
        data = new double[trialNum];
        for (int i = 0; i < trialNum; i++) {
            Percolation test = new Percolation(gridL);
            while (!test.percolates()) {
                int x = StdRandom.uniformInt(0, n);
                int y = StdRandom.uniformInt(0, n);
                test.open(x, y);
            }
            data[i] = (double) test.numberOfOpenSites() / (gridL
                    * gridL); // how to call private method
            // in different program
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        // mean must take int array of p values of n trials
        return StdStats.mean(data);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(data);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean() - 1.96 * stddev() / Math.sqrt(trialNum));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean() + 1.96 * stddev() / Math.sqrt(trialNum));
    }


    // test client (see below)
    public static void main(String[] args) { // for running randomized
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats pS = new PercolationStats(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]));
        // calculations go here
        double time = stopwatch.elapsedTime();
        System.out.println("mean() = " + pS.mean());
        System.out.println("stddev() = " + pS.stddev());
        System.out.println(
                "confidenceLow() = " + pS.confidenceLow());
        System.out.println("confidenceHigh() = " + pS.confidenceHigh());
        System.out.print("elapsed time = " + time);


    }
}

/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF percolationUf;
    private WeightedQuickUnionUF fullUf;
    private boolean[] openSites;
    private int openCount;
    private int n;
    private int virtualTop;
    private int virtualBottom;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n is less than 1");
        }
        this.n = n;
        int totalSites = n * n;
        virtualTop = totalSites;
        virtualBottom = totalSites + 1;

        percolationUf = new WeightedQuickUnionUF(totalSites + 2);
        fullUf = new WeightedQuickUnionUF(totalSites + 1);
        openSites = new boolean[totalSites];
    }

    public void open(int row, int col) {
        checkBounds(row, col);

        int site = getSite(row, col);

        if (openSites[site]) {
            return;
        }

        openSites[site] = true;
        openCount++;

        if (row == 1) {
            percolationUf.union(site, virtualTop);
            fullUf.union(site, virtualTop);
        }
        if (row == n) {
            percolationUf.union(site, virtualBottom);
        }


        if (row > 1) {
            connectAdjacent(site, row - 1, col);
        }
        if (row < n) {
            connectAdjacent(site, row + 1, col);
        }
        if (col > 1) {
            connectAdjacent(site, row, col - 1);
        }
        if (col < n) {
            connectAdjacent(site, row, col + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        checkBounds(row, col);

        int site = getSite(row, col);
        return openSites[site];
    }

    public boolean isFull(int row, int col) {
        checkBounds(row, col);

        int site = getSite(row, col);
        if (!openSites[site]) {
            return false;
        }
        return fullUf.connected(site, virtualTop);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return percolationUf.connected(virtualTop, virtualBottom);
    }

    private void connectAdjacent(int site, int row, int col) {
        int adjacentSite = getSite(row, col);
        if (openSites[adjacentSite]) {
            percolationUf.union(site, adjacentSite);
            fullUf.union(site, adjacentSite);
        }
    }

    private int getSite(int row, int col) {
        return n * (row - 1) + (col - 1);
    }

    private void checkBounds(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row or col is out of bounds");
        }
    }


    public static void main(String[] args) {

    }
}

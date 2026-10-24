/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int[][] tiles;

    public Board(int[][] tiles) {

        this.tiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            this.tiles[i] = tiles[i].clone();
        }
    }

    public int hamming() {
        int length = tiles.length;
        int hamming = 0;
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                int tile = tiles[row][col];
                if (tile == 0) {
                    continue;
                }
                int goalRow = (tile - 1) / length;
                int goalCol = (tile - 1) % length;

                if (row != goalRow || col != goalCol) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    public int dimension() {
        return tiles.length;
    }

    public int manhattan() {
        int manhattan = 0;
        int length = tiles.length;
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                int tile = tiles[row][col];
                if (tile == 0) {
                    continue;
                }
                int goalRow = (tile - 1) / length;
                int goalCol = (tile - 1) % length;

                manhattan += Math.abs(row - goalRow) + Math.abs(col - goalCol);
            }
        }
        return manhattan;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        Board that = (Board) other;

        if (this.dimension() != that.dimension()) {
            return false;
        }

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                if (tiles[row][col] != that.tiles[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        int zeroCol = -1;
        int zeroRow = -1;
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                int tile = tiles[row][col];
                if (tile == 0) {
                    zeroCol = col;
                    zeroRow = row;
                    break;
                }
            }
            if (zeroCol >= 0) {
                break;
            }
        }
        List<Board> neighbors = new ArrayList<>();

        if (zeroRow - 1 >= 0) {
            Board neighbor = createSwappedBoard(zeroRow, zeroCol, zeroRow - 1, zeroCol);
            neighbors.add(neighbor);
        }
        if (zeroRow + 1 < tiles.length) {
            Board neighbor = createSwappedBoard(zeroRow, zeroCol, zeroRow + 1, zeroCol);
            neighbors.add(neighbor);
        }
        if (zeroCol - 1 >= 0) {
            Board neighbor = createSwappedBoard(zeroRow, zeroCol, zeroRow, zeroCol - 1);
            neighbors.add(neighbor);
        }
        if (zeroCol + 1 < tiles.length) {
            Board neighbor = createSwappedBoard(zeroRow, zeroCol, zeroRow, zeroCol + 1);
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    private Board createSwappedBoard(int zeroRow, int zeroCol, int newRow, int newCol) {
        int[][] copy = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            copy[i] = tiles[i].clone();
        }
        int temp = copy[zeroRow][zeroCol];
        copy[zeroRow][zeroCol] = copy[newRow][newCol];
        copy[newRow][newCol] = temp;

        return new Board(copy);
    }

    public Board twin() {
        int tile1Row = 0;
        int tile1Col = 0;
        int tile2Row = 0;
        int tile2Col = 1;

        if (tiles[tile1Row][tile1Col] == 0) {
            tile1Row = 1;
        }
        else if (tiles[tile2Row][tile2Col] == 0) {
            tile2Row = 1;
            tile2Col = 0;
        }

        return createSwappedBoard(tile1Row, tile1Col, tile2Row, tile2Col);
    }

    @Override
    public String toString() {
        StringBuilder all = new StringBuilder();
        all.append(tiles.length);
        for (int row = 0; row < tiles.length; row++) {
            all.append("\n ");
            for (int col = 0; col < tiles[row].length; col++) {
                all.append(tiles[row][col]).append("  ");
            }
        }
        all.append("\n");
        return all.toString();
    }

    public static void main(String[] args) {

    }
}

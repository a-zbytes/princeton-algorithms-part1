/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {
    private boolean solvable;
    private SearchNode solvedNode;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        MinPQ<SearchNode> mainPQ = new MinPQ<>();
        MinPQ<SearchNode> twinPQ = new MinPQ<>();

        SearchNode main = new SearchNode(initial, 0, null);
        mainPQ.insert(main);

        SearchNode twin = new SearchNode(initial.twin(), 0, null);
        twinPQ.insert(twin);

        start(mainPQ, twinPQ);
    }

    private void start(MinPQ<SearchNode> mainPQ, MinPQ<SearchNode> twinPQ) {
        while (true) {
            if (!mainPQ.isEmpty()) {
                if (solve(mainPQ, true)) {
                    break;
                }
            }
            if (!twinPQ.isEmpty()) {
                if (solve(twinPQ, false)) {
                    break;
                }
            }
        }
    }

    private boolean solve(MinPQ<SearchNode> pq, boolean isMain) {
        SearchNode node = pq.delMin();
        if (node.board.isGoal()) {
            if (isMain) {
                solvable = true;
                solvedNode = node;
            }
            return true;
        }
        for (Board board : node.board.neighbors()) {
            if (node.previous != null && board.equals(node.previous.board)) {
                continue;
            }
            pq.insert(new SearchNode(board, node.moves + 1, node));
        }
        return false;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (solvable) {
            return solvedNode.moves;
        }
        return -1;
    }

    public Iterable<Board> solution() {
        if (!solvable) {
            return null;
        }
        List<Board> boards = new ArrayList<>();
        SearchNode node = solvedNode;
        while (node != null) {
            boards.add(node.board);
            node = node.previous;
        }
        Collections.reverse(boards);
        return boards;
    }

    private static class SearchNode implements Comparable<SearchNode> {
        final SearchNode previous;
        final Board board;
        final int moves;
        final int priority;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = moves + board.manhattan();
        }

        public int compareTo(SearchNode other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    public static void main(String[] args) {

    }
}

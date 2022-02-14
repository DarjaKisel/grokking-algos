package com.dzinevich.leetcode.tree.breadthFirstSearch;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// GATE = 0
// WALL = W = -1
public class WallsAndGates {
    //
    // we're going to move from gates, 1 step from gate, 2 step from gate, etc..
    //
    //  |  |W |0 |  |    -    |  |W |0 |1 |    -    |  |W |0 |1 |    -    |3 |W |0 |1 |
    //  |  |  |  |W |    -    |  |  |1 |W |    -    |2 |2 |1 |W |    -    |2 |2 |1 |W |
    //  |  |W |  |W |    -    |1 |W |  |W |    -    |1 |W |2 |W |    -    |1 |W |2 |W |
    //  |0 |W |  |  |    -    |0 |W |  |  |    -    |0 |W |  |  |    -    |0 |W |3 |4 |
    //
    // BFS == QUEUE
    // TIME = O(m*n)
    //
    public void wallsAndGates(int[][] grid) {
        int rows = grid.length;
        int cols = rows == 0 ? 0 : grid[0].length;

        int[][] dirs = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};
        Queue<int[]> moves = new LinkedList<>();

        // ADD ALL GATES
        for (int r = 0; r < rows; r++) { // O(m*n)
            for (int c = 0; c < cols; c++) {

                if (grid[r][c] == 0) { // it's a gate!
                    moves.offer(new int[]{r, c});
                }
            }
        }

        // UPDATE DISTANCE
        while (!moves.isEmpty()) {
            int[] currPos = moves.poll();

            for (int[] dir : dirs) {
                int X = currPos[0] + dir[0];
                int Y = currPos[1] + dir[1];

                if (X >= 0 && X < rows && Y >= 0 && Y < cols && grid[X][Y] == Integer.MAX_VALUE) {
                    grid[X][Y] = grid[currPos[0]][currPos[1]] + 1;
                    moves.offer(new int[]{X, Y});
                }
            }
        }
    }

    public static void main(String[] args) {
        WallsAndGates w = new WallsAndGates();

        int[][] grid = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };
        w.wallsAndGates(grid);

        System.out.println(Arrays.deepToString(grid));

        Assert.assertArrayEquals(grid, new int[][]{
                {3, -1, 0, 1},
                {2, 2, 1, -1},
                {1, -1, 2, -1},
                {0, -1, 3, 4}
        });
    }
}

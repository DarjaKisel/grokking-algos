package com.dzinevich.leetcode.dynamicProgramming;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        //First row
        for(int i = 1; i < cols; i++) {
            grid[0][i] += grid[0][i-1];
        }

        //First column
        for(int j = 1; j < rows; j++) {
            grid[j][0] += grid[j-1][0];
        }

        // rest
        for(int i = 1; i < rows; i++) {
            for (int j=1; j<cols; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]); //[i-1][j] - is up, [i][j-1] - is left
            }
        }
        return grid[rows-1][cols-1];
    }

    public String maxPathSum(int[][] grid) {

        StringBuilder result = new StringBuilder();

        int rows = grid.length;
        int cols = grid[0].length;

        result.append(grid[rows-1][cols-1]);

        int i = rows-1;
        int j = cols-1;

        while (i > 0 && j > 0) {
            if (grid[i-1][j] > grid[i][j-1]) { //[i-1][j] - is up
                result.append(grid[i - 1][j]);
                i=i-1;
            } else {
                result.append(grid[i][j-1]); //[i][j-1] - is left
                j=j-1;
            }
        }

        result.append(grid[0][0]);

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        System.out.println(new MinimumPathSum().minPathSum(grid) + " should be 7");

        int[][] grid1 = new int[][]{
                {9, 9, 7},
                {9, 7, 2},
                {6, 9, 5},
                {9, 1, 2}
        };

        System.out.println(new MinimumPathSum().maxPathSum(grid1) + " should be \"997952\"");
    }
}

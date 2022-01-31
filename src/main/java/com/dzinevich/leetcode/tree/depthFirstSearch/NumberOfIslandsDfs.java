package com.dzinevich.leetcode.tree.depthFirstSearch;

/**
 * Given an m x n 2D binary grid
 * which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * <p>
 * An island is surrounded by water
 * and is formed by connecting adjacent lands
 * horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslandsDfs {

    public static int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int islands = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++){

                //DO THE MAGIC IF IT'S 1
                if (grid[row][col] == '1') {

                    //think of it as of 'syncing' the islands in a single big island
                    islands += dfs(grid, row, col);
                }
            }
        }

        return islands;
    }

    //this is recursive
    private static int dfs(char[][] grid, int currRow, int currCol) {

        if (currRow < 0 || currRow >= grid.length
            || currCol < 0 || currCol >= grid[currRow].length
            || grid[currRow][currCol] != '1') {
            return 0;
        }

        //mark as visited
        grid[currRow][currCol] = 'V';

        dfs(grid, currRow + 1, currCol); // down
        dfs(grid, currRow, currCol + 1); // right
        dfs(grid, currRow - 1, currCol); // up
        dfs(grid, currRow, currCol - 1); // left


        // THIS IS TO ACCOUNT FOR THE ORIGINAL FIRST ISLAND THAT WE'VE FOUND in the beginning
        return 1;
    }

    public static void main(String[] args) {
        char[][] example1 = new char[][]{
                {'1', '1', '0', '0'}, {'1', '1', '0', '0'}, {'0', '0', '1', '0'}, {'0', '0', '0', '1'}, {'0', '0', '0', '1'}
        };
        System.out.println("Should be 3");
        System.out.println(numIslands(example1));

        char[][] example2 = new char[][]{
                {'1', '1', '1', '0'}, {'1', '1', '1', '0'}, {'1', '0', '0', '0'}, {'1', '1', '0', '0'}, {'0', '0', '0', '0'}
        };
        System.out.println("Should be 1");
        System.out.println(numIslands(example2));

        //

        char[][] example11 = new char[][]{
                {'1', '1', '0', '0'}, {'1', '1', '0', '0'}, {'0', '0', '1', '0'}, {'0', '0', '0', '1'}, {'0', '0', '0', '1'}
        };
        System.out.println("Should be 3");
        System.out.println(repeatNumIslandsDfs(example11));

        char[][] example22 = new char[][]{
                {'1', '1', '1', '0'}, {'1', '1', '1', '0'}, {'1', '0', '0', '0'}, {'1', '1', '0', '0'}, {'0', '0', '0', '0'}
        };
        System.out.println("Should be 1");
        System.out.println(repeatNumIslandsDfs(example22));
    }


    private static int repeatNumIslandsDfs(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int islands = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                if (grid[row][col] == '1') {

                    islands += dfsRepeat(grid, row, col);
                }
            }
        }

        return islands;
    }



    private static int dfsRepeat(char[][] grid, int currRow, int currCol) {

        // FIRST - CHECK IF VALID
        if (currRow < 0 || currRow >= grid.length || currCol < 0 || currCol >= grid[currRow].length || grid[currRow][currCol] != '1') {
            return 0;
        }

        // THEN - MARK AS VISITED!
        grid[currRow][currCol] = 'V';

        dfsRepeat(grid, currRow + 1, currCol); //down
        dfsRepeat(grid, currRow - 1, currCol); //up
        dfsRepeat(grid, currRow, currCol + 1); //right
        dfsRepeat(grid, currRow, currCol - 1); //left

        // DON'T FORGET!!! THIS IS THE ORIGINAL ISLAND THAT WE'VE FOUND IN THE BEGINNING AND CAME HERE IN THIS DFS
        return 1;
    }
}

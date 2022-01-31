package com.dzinevich.leetcode.tree.breadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

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
public class NumberOfIslandsBfs {

    public static int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int islands = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        //directions
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> moves = new LinkedList<>();

        //traverse the 2d matrix

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (grid[r][c] == '1') {
                    islands++;

                    moves.add(new int[]{r, c});

                    //mark as visited
                    grid[r][c] = '2';

                    //this loop finds all the connected land
                    while (!moves.isEmpty()) {

                        int[] current = moves.poll();

                        for (int[] dir : directions) {
                            //the coordinate contains 2 directions row and column [row][col]
                            int rowMove = current[0] + dir[0];
                            int colMove = current[1] + dir[1];

                            //if the new coordinate to move is valid- we'll add it to queue and visit it
                            if (rowMove >= 0 && rowMove < rows && colMove >= 0 && colMove < cols && grid[rowMove][colMove] == '1') {
                                moves.add(new int[]{rowMove, colMove});
                                grid[rowMove][colMove] = '2';
                            }
                        }
                    }
                }
            }
        }
        return islands;
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
        System.out.println(repeatNumOfIslandsBfs(example11));

        char[][] example22 = new char[][]{
                {'1', '1', '1', '0'}, {'1', '1', '1', '0'}, {'1', '0', '0', '0'}, {'1', '1', '0', '0'}, {'0', '0', '0', '0'}
        };
        System.out.println("Should be 1");
        System.out.println(repeatNumOfIslandsBfs(example22));
    }


    public static int repeatNumOfIslandsBfs(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int islands = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        //define 4 directions
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //store our moves
        Queue<int[]> moves = new LinkedList<>();

        //traverse
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                //everything is inside this IF!!!
                //we do the magic ONLY IF there is a land

                if (grid[row][col] == '1') {
                    islands++;

                    moves.add(new int[]{row, col});

                    //mark as visited
                    grid[row][col] = '2';

                    //find all connected lands
                    while (!moves.isEmpty()) {

                        int[] current = moves.poll();

                        //check all 4 sides
                        for (int[] dir : directions) {
                            //we have 2 coordinates to move
                            // if current is {1,2} and first move is {0, 1} (down)
                            // then our new coordinate will be {1+0, 2+1} == {1, 3} (one step down)
                            int rowMove = current[0] + dir[0];
                            int colMove = current[1] + dir[1];

                            //is this move valid?
                            if (rowMove >= 0 && rowMove < rows && colMove >= 0 && colMove < cols && grid[rowMove][colMove] == '1') {
                                moves.add(new int[]{rowMove, colMove});
                                //mark as visited
                                grid[rowMove][colMove] = '2';
                            }
                        }
                    }
                }
            }
        }

        return islands;
    }
}

package com.dzinevich.leetcode.backtracking;

import java.util.*;

/**
 * Given an m x n grid of characters board and a string word,
 * return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * <p>
 * The same letter cell may not be used more than once.
 * <p>
 * [A,B,C,e]
 * [s,f,C,s]
 * [a,D,E,e]
 * <p>
 * [A,B,C,C,E,D] - true
 *
 */
public class WordSearch {

    static class Position{
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    int rows;
    int cols;
    Set<Position> visited = new HashSet<>();

    String word;
    char[][] board;

    // O(n*m*dfs)= n*m*4^len(word) = n*m*4^n
    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;

        this.board = board;
        this.word = word;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (dfs(r, c, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int row, int col, int targetCharInTheWord) {
        if (targetCharInTheWord == word.length()) { // we reach the end
            return true;
        }

        Position position = new Position(row, col);
        if (row < 0 || col < 0 || row >= rows || col >= cols // out of bounds
                || word.charAt(targetCharInTheWord) != board[row][col]  // not a match
                || visited.contains(position)) { // already visited
            return false;
        }

        visited.add(position);

        boolean result =
                dfs(row + 1, col, targetCharInTheWord + 1) // row+1 is down
                        || dfs(row - 1, col, targetCharInTheWord + 1)// row-1 is up
                        || dfs(row, col - 1, targetCharInTheWord + 1) // col-1 is left
                        || dfs(row, col + 1, targetCharInTheWord + 1);// col+1 is right


        visited.remove(position);

        return result;
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String target = "ABCCED";

        System.out.println(new WordSearch().exist(board, target) + " should be true");

        String target2 = "ABCB";

        System.out.println(new WordSearch().exist(board, target2) + " should be false");

    }

}

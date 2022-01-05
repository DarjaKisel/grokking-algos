package com.dzinevich.leetcode.dynamicProgramming;

/**
 * A robot is located at the TOP_LEVEL CORNER START of a m x n grid;
 *
 * The robot can only move either DOWN or RIGHT at any point in time.
 * The robot is trying to reach the BOTTOM_RIGHT CORNER FINISH of the grid
 *
 * How many possible unique paths are there?
 *
 * |-S-|---|---|---|---|---|---|
 * |---|---|---|---|---|---|---|
 * |---|---|---|---|---|---|-F-|
 *
 * let's stand at the F and try to find a way to it
 *
 * |-S-|---|---|---|---|---|---|
 * |---|---|---|---|---|---|---|
 * |-1-|-1-|-1-|-1-|-1-|-1-|-F-|
 *   0   0   0   0   0   0
 *
 * so at every position in the bottom line the only move you can make is move right,
 * down is out-of-bounds that's why it's marked as zero
 *
 * |-S-|---|---|---|---|---|-1-| 0
 * |---|---|---|---|---|---|-1-| 0
 * |-1-|-1-|-1-|-1-|-1-|-1-|-F-|
 *   0   0   0   0   0   0
 *
 * same for any position in the right column, if you stand there you can only move down,
 * right is out-of-bounds that's why it's marked as zero
 *
 * |-S-|---|---|---|---|---|-1-| 0
 * |---|---|---|---|---|-2-|-1-| 0
 * |-1-|-1-|-1-|-1-|-1-|-1-|-F-|
 *   0   0   0   0   0   0
 *
 *  if you stand where 2 - there are two ways from that point:
 *  fist - right then down, second - down then right
 *
 * |-S-|---|---|---|---|---|-1-| 0
 * |-7-|-6-|-5-|-4-|-3-|-2-|-1-| 0
 * |-1-|-1-|-1-|-1-|-1-|-1-|-F-|
 *   0   0   0   0   0   0
 *
 *  same applies for any position in that row, you just do 1+2, 1+3, 1+4 etc..
 *
 * |-S-|-21|-15|-10|-6-|-3-|-1-| 0
 * |-7-|-6-|-5-|-4-|-3-|-2-|-1-| 0     RESULT = 21+8=28 unique paths
 * |-1-|-1-|-1-|-1-|-1-|-1-|-F-|
 *   0   0   0   0   0   0
 */
public class UniquePaths {

    // Complexity = O (n * m)

    //Input: m = 3, n = 7
    //Output: 28
    public int uniquePaths(int m, int n) {

        //fill in first bottom row with 1
        int[] currRow = new int[n];
        for (int i = 0; i < n; i++) {
            currRow[i] = 1;
        }

        //for every row above
        for (int i = 0; i < m - 1; i++) {

            // 1.cerate a new row above and fill it with 1
            int[] newRow = currRow;

            // 2.calculate values in new row
            // we know that the right column(the last item in any row) == 1
            // go in reverse order
            for (int j = n - 2; j >= 0; j--) {
                newRow[j] = newRow[j + 1] + currRow[j]; // value at right + value below
            }

            // 3. update the old row
            currRow = newRow;
        }

        return currRow[0];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(3, 7) + " : should be 28");
        System.out.println(new UniquePaths().uniquePaths(7, 3) + " : should be 28");
        System.out.println(new UniquePaths().uniquePaths(3, 3) + " : should be 6");
    }


}

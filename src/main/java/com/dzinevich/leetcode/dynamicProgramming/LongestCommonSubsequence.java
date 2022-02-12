package com.dzinevich.leetcode.dynamicProgramming;

/**
 * Given two strings text1 and text2,
 * return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 */
public class LongestCommonSubsequence {

    // 2-DIMENSIONAL Dynamic Programming problem

    //            [j]
    //         a   c  e          ->         a   c  e
    //      a |  |  |  | 0|      ->      a | 3|  |  | 0|
    //      b |  |  |  | 0|      ->      b |  | 2|  | 0|
    // [i]  c |  |  |  | 0|      ->      c |  | 2|  | 0|
    //      d |  |  |  | 0|      ->      d |  |  | 1| 0|
    //      e |  |  |  | 0|      ->      e |  |  | 1| 0|
    //        | 0| 0| 0| 0|      ->        | 0| 0| 0| 0|

    // if we have a match like (e) and (e) we put 1 and move diagonally
    // if we don't have a match we put max value from right and down
    // like (c) and (b) - we move down, there is max value 2 because (c) and (c) are match

    //in the code we go bottom up


    //Time Complexity = O(t1.length * t2.length)

    public int longestCommonSubsequence(String t1, String t2) {

        int [][] dp = new int[t1.length()+1][t2.length()+1]; // +1 because last row and col is zero

        for (int i = t1.length()-1; i >=0; i--) { // row // length-1 because the last row is 0
            for (int j = t2.length()-1; j>= 0; j--) { // column // length-1 because the last col is 0
                if (t1.charAt(i) == t2.charAt(j)) {
                    dp[i][j] = 1 + dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]); // i+1 = row+1 = down, j+1 = col+1 = right
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence L = new LongestCommonSubsequence();
        System.out.println(L.longestCommonSubsequence("abcde", "ace") + " : ex 3");
        System.out.println(L.longestCommonSubsequence("ace", "ace") + " : ex 3");
    }

























}

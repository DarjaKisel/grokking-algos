package com.dzinevich.leetcode.dynamicProgramming;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {

    // using caching memorizing
    // bottom-up DP

    /**
     * see ClimbingStairs.png
     */
    public static int howManyDistinctWaysWeHave(int steps) {
        int one = 1;
        int two = 1;

        for (int i = 0; i < steps - 1; i++) {
            int temp = one; // store current one before shifting
            one = one + two;
            two = temp;
        }

        return one;
    }

    public static void main(String[] args) {
        System.out.println(howManyDistinctWaysWeHave(5));
        System.out.println("^ Should be 8");

        System.out.println(repeatSolution(5));
        System.out.println("^ Should be 8");
    }

    /**
     * let's say we have 5 stairs with 0 as the bottom
     * |0|1|2|3|4|5|
     * we start from 5 as we do bottom-up dynamic programming
     * we need one step to get to 5 from nowhere
     * we need one step to get from 5 to 4
     * | | | | |1|1|
     * to get to 3 we'll need to sum up 2 previous moves which is 1+1=2
     * | | | |2|1|1|
     * to get to 2 we'll need to sum up 2 previous moves which is 1+2=3
     * | | |3|2|1|1|
     * to get to 1 we'll need to sum up 2 previous moves which is 3+2=5
     * | |5|3|2|1|1|
     * to get to 0 we'll need to sum up 2 previous moves which is 5+3=8
     * |8|5|3|2|1|1|
     *
     * Result:
     * 1. we need to repeat loop (steps-1) times
     * 2. we return last sum which is 8 in our case
     */
    public static int repeatSolution(int steps) {
        int one = 1;
        int two = 1;

        for (int i = 0; i < steps - 1; i++) {
            int temp = one; // store the current on before moving
            one = one + two; // move one left and sum up last two elements
            two = temp; // two is moved to the place where was one
        }

        return one;
    }
}

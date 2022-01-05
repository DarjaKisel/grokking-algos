package com.dzinevich.leetcode.dynamicProgramming;

/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them
is that adjacent houses have security systems connected,
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house,
 return the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {
    public int rob(int[] nums) {

        int max1 = 0;
        int max2 = 0;

        //let's say we have [1,2,3,1]
        //we actually have only 2 choices - start from element 0 or element 1
        //if we start from 0, then we need a max of the remaining arr from 2 to end
        //if we start from 1, then we need a max of the arr from 1 to end
        //looking for a max in the remaining arr - recurrent subproblem!
        // rob = max(arr[0] + rob[2:n], rob[1:n])
        //
        for(int i : nums) {
            // max2 was the last house we robbed
            // max1 was the one before that
            // current position is i, so i and max1 has a gap between them which is max2
            int temp = Math.max(i + max1, max2);

            max1 = max2; // should update max1 (move pointer to the right)
            max2 = temp; // max2 is the max value from last visited houses
        }

        return max2;
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobber().rob(new int[]{1, 2, 0, 1, 1}) + " : should be 3");
    }

}

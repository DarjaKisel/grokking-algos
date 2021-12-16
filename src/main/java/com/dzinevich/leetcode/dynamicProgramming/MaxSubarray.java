package com.dzinevich.leetcode.dynamicProgramming;

/**
 * Given an integer array nums,
 * find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Input: nums = [1]
 * Output: 1
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 */
public class MaxSubarray {

    // I think this is sliding window approach
    public static int maxSubArray(int[] arr) {

        int maxSubArray = arr[0]; //[-2,1,-3,4,-1,2,1,-5,4] start from [-2]
        int currentSum = 0; //default is 0

        for (int i : arr) { //-2//1//-3//4//-1//2//1//-5//4

            // check if our prefix is less than default (which is 0)
            // if it is - reset the currSum back to 0
            if (currentSum < 0) {
                currentSum = 0;
            }

            currentSum += i; // 0-2=-2// 0+1=1// 1-3=-2// 0+4=4// 4-1=3// 3+2=5// 5+1=6// 6-5=1// 1+4=5

            maxSubArray = Math.max(maxSubArray, currentSum); //-2// 1// 1// 4// 4// 5// 6// 6// 6
        }

        return maxSubArray; //return 6
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("Should be 6");

        System.out.println(maxSubArray(new int[]{-10,-9,-8,-7,-6,-5,-4,-3,-20}));
        System.out.println("Should be -3");

        //

        System.out.println(repeatMaxSubarray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("Should be 6");

        System.out.println(repeatMaxSubarray(new int[]{-10,-9,-8,-7,-6,-5,-4,-3,-20}));
        System.out.println("Should be -3");
    }

    private static int repeatMaxSubarray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = 0;

        for (int i : nums) {

            // CURRENT SUM MAY NOT BE 0
            // SO FIRST CHECK IF IT"S NEGATIVE
            // IF YES - RESET TO 0
            // AND THEN SUM IT WITH THE NEXT NR

            if (currentSum < 0) {
                currentSum = 0;
            }

            currentSum += i;

            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}

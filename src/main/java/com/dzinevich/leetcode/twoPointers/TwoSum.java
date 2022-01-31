package com.dzinevich.leetcode.twoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 */
public class TwoSum {

    // complexity O(n0
    // memory O(n)
    public static int[] findTwoIndexesThatSumUpToTheGivenTarget(int[]arr, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int valueToAddUpToTheTarget = target - arr[i];
            if (hashMap.get(valueToAddUpToTheTarget) != null) {
                return new int[]{hashMap.get(valueToAddUpToTheTarget), i};
            } else {
                hashMap.put(arr[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                findTwoIndexesThatSumUpToTheGivenTarget(new int[]{2, 7, 11, 15}, 9)
        ));
        System.out.println("^ Should be [0, 1]");
    }
}

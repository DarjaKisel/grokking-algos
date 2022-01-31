package com.dzinevich.leetcode.twoPointers;

import java.util.*;

/**
 * Given an integer array nums,
 * return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */
public class ThreeSum {

    //
    // Time - O(n^2)
    // Mem - may be O(n) worst case
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //sort is O(n*logn) time complexity
        Arrays.sort(nums);

        // we use each number of input array nums as a possible first value of triplet
        // the other two se solve as 2Sum

        Set<Integer> firstNumber = new HashSet<>();
        //loop through every element once - O(n)
        for (int i = 0; i < nums.length-1; i++) {
            // we don't allow the same number to be the first element of the triplet twice, otherwise we may get a duplicate triplet
            if (i > 0 && firstNumber.contains(nums[i])) {
                continue;
            }

            int left = i+1;
            int right = nums.length-1;

            //again O(n) is the worst case
            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];
                if (threeSum == 0) {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    // [2,2,0,0,-2,-2]
                    //  ^           ^
                    //    ^         ^
                    // are the same, so we have to move again
                    left++;
                    while (left < right && nums[left] == nums[left-1]){
                        left++;
                    }
                } else if (threeSum > 0) {
                    right--;
                } else {
                    left++;
                }
            }

            firstNumber.add(nums[i]);
        }

        return result;

        // we have O(n*logn) + O(n) * O(n) = O(n*logn) + O(n^2) = O(n^2)
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}) + " : should be [[-1,-1,2],[-1,0,1]]");
        System.out.println(new ThreeSum().threeSum(new int[]{0,0,0}) + " : should be [[0,0,0]");
    }
}

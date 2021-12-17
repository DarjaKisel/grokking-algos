package com.dzinevich.leetcode.binary;

/**
 * Given an array of integers nums which is sorted in ascending order,
 * and an integer target,
 * write a function to search target in nums.
 *
 * If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
public class BinarySearch {

    // two pointers
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            // !!! left +
            // left + in the beginning is needed because otherwise we'll go past integer.max_value
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // move left pointer to the mid+1
                // as target is greater than mid
                left = mid + 1;
            } else if (nums[mid] > target) {
                // move right pointer to the mid-1
                // as target is less than mid
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 9) + " Should be 4");
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2) + " Should be -1");
    }
}

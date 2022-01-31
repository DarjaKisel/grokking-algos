package com.dzinevich.leetcode.twoPointers;

import com.dzinevich.leetcode.twoPointers.TwoSum;

import java.util.Arrays;

/**
 * the same as {@link TwoSum} but the Array is sorted
 */
public class  TwoSumTwo {
    //
    // since the array is sorted we can use 2 pointers
    // [1, 3, 4, 5, 7, 11] -- target 9
    //  ^              ^   -- 1 + 11 = too big, decrease right pointer
    //  ^           ^      -- 1 + 7 = too small, increase left pointer
    //     ^        ^      -- 3 + 7 = too big, decrease right pointer
    //     ^     ^         -- 3 + 5 = too small, increase left pointer
    //        ^  ^         -- 4 + 5 = found!
    //
    // should return indexes starting from 1 (not 0) -- return [3,4]
    //
    // Time complexity O(n), no extra memory
    //
    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length-1;

        while (numbers[left] + numbers[right] != target) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }

        return new int[]{left+1, right+1};
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSumTwo().twoSum(new int[]{1, 3, 4, 5, 7}, 9)) + " : should be [3, 4]");
        System.out.println(Arrays.toString(new TwoSumTwo().twoSum(new int[]{2, 7, 11, 15}, 9)) + " : should be [1, 2]");
    }
}

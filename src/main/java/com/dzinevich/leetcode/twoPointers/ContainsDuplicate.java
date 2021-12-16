package com.dzinevich.leetcode.twoPointers;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums,
 * return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 *
 * Examples:
 *
 * Input: nums = [1,2,3,1]
 * Output: true
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 *
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {
        if (nums.length == 1) {
            return false;
        }

        Set<Integer> hashSet = new HashSet<>();
        for (int i : nums) {
            if (hashSet.contains(i)) {
                return true;
            } else {
                hashSet.add(i);
            }
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println(repeatContainsDuplicate(new int[]{1, 2, 3, 1}) + " should be true");
        System.out.println(repeatContainsDuplicate(new int[]{1, 2, 3, 4}) + " should be false");
        System.out.println(repeatContainsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}) + " should be true");
    }


    private static boolean repeatContainsDuplicate(int[] nums) {
        if (nums.length <=1) {
            return false;
        }

        Set<Integer> ints  = new HashSet<>();
        for (int i : nums) {
            if (ints.contains(i)) {
                return true;
            } else {
                ints.add(i);
            }
        }

        return false;
    }
}

package com.dzinevich.leetcode.binary;

/**
 * Given an array nums
 * containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 */
public class MissingNumber {

    /**
     * let's say we have [3, 0, 1, 4] we should find 2
     */
    public static int findMissingNumberUsingSumOfTwoArrays(int[] arr) {

        // we know that range is [0, n] where N is max and last number in the sorted array
        // let's take an array of [0,1,2,3,4] sum all elements and subtract sum of [3,0,1,4]
        // take every el from 0 to n add and subtract every el of arr
        // like this: 0 += i - arr[i]
        // we need to set result initially to arr.length since we're missing one number in the input array

        int result = arr.length; // 4

        for (int i : arr) {
            result += i - arr[i];
            //
            // 4 + 0 - 3 = 1
            // 1 + 1 - 0 = 2
            // 2 + 2 - 1 = 3
            // 3 + 3 - 4 = 2
            // result = 2
        }

        return result;
    }

    /**
     * Gauss formula to sum up all elements of a range from 0 to n [0,n] is n*(n+1)/2
     */
    public int findMissingNumberWithGaussFormula(int[] arr) {
        int sumWithoutMissingNr = (arr.length * (arr.length + 1))/2;

        for (int i : arr) {
            sumWithoutMissingNr -= i;
        }
        // it will become the difference between two sums :)
        return sumWithoutMissingNr;
    }
}

package com.dzinevich.leetcode.arrays;

import java.util.Arrays;

/**
 * Given an integer array nums, return an array answer
 * such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
public class ProductOfArrayExceptSelf {

    /**
     *                              [1, 2, 3, 4 ]
     * prefix - mult from left    ->[1, 2, 6, 24]
     * postfix - mult from right    [24,24,12,4 ]<-
     *
     * for every value we need (prefix * postfix)
     *                              [24,12,8,6]
     *      O(N) complex
     *      O(N) space
     *
     * to reduce memory we're storing prefix and postfix in the output array
     *
     * so the prefix for 3 is (1*2) and we store it at position 3 in the output array
     *                             [ , , 2, ]
     * the postfix for 3 is (1*4) and we store it at the same spot but multiply with prefix
     *                             [ , ,2*4, ]
     *
     *      and we have  O(N) complex and O(1) mem
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int multResultBeforeCurrNum = 1;

        for (int i = 0; i < nums.length; i++) {
            result[i] = multResultBeforeCurrNum;
            multResultBeforeCurrNum = nums[i] * multResultBeforeCurrNum;
        }

        int multResultAfterCurrNum = 1;
        for (int i = nums.length-1; i>=0; i--) {
            result[i] = result[i] * multResultAfterCurrNum; //(prefix * postfix)
            multResultAfterCurrNum = nums[i] * multResultAfterCurrNum; // postfix for 3 is 4*3=12
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new ProductOfArrayExceptSelf()
                .productExceptSelf(new int[]{1, 2, 3, 4})) + " should be [24,12,8,6]");
    }


}

package com.dzinevich.leetcode.twoPointers;

/**
 * You are given an integer array height of length n. There are n vertical lines.
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 */
public class ContainerWithMostWater {

    //BRUTEFORCE SOLUTION -- O(N^2)
    public static int maxWaterBruteForce(int[] height) {
        int result = 0;

        for(int leftPointer = 0; leftPointer < height.length; leftPointer++) {
            for (int rightPointer = leftPointer+1; rightPointer < height.length; rightPointer++) {
                int area = Math.min(height[leftPointer], height[rightPointer]) * (rightPointer - leftPointer);
                result = Math.max(result, area);
            }
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(maxWaterBruteForce(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}) + " should be 49");
        System.out.println(maxWater(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}) + " should be 49");
    }

    //TWO-POINTER TECHNIQUE -- O(N)
    public static int maxWater(int[] height) {
         int result = 0;

         int left = 0;
         int right = height.length - 1;

         while (left < right) {
             int area = Math.min(height[left], height[right]) * (right - left);
             result = Math.max(result, area);

             //let's try to increase the area
             //by finding some bigger height
             if (height[left] < height[right]) {
                 left++;
             } else {
                 right--;
             }
         }

         return result;
    }

}

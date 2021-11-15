package com.dzinevich.educative._01_slidingWindow;

public class SmallestSubarrayWithGivenSum {

    //complexity is O(N)
    public static int findMinSubarray(int[] args, int S) {
        int minLength = Integer.MAX_VALUE;
        int windowStart = 0;
        int windowSum = 0;

        for (int windowEnd = 0; windowEnd < args.length; windowEnd++) {
            windowSum += args[windowEnd]; // add the next element
            // !! while the windowSum is >= S we shrink the window as small as we can
            while (windowSum >= S) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= args[windowStart]; // remove left element
                windowStart++; // move window to the right
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        int sum1 = 7;
        System.out.println("The smallest subarray with a sum greater than or equal to " + sum1 + " is " +
                findMinSubarray(new int[] {2, 1, 5, 2, 3, 2}, sum1)); // should be 2
        int sum2 = 7;
        System.out.println("The smallest subarray with a sum greater than or equal to " + sum2 + " is " +
                findMinSubarray(new int[] {2, 1, 5, 2, 8}, sum2)); // should be 1
        int sum3 = 8;
        System.out.println("The smallest subarray with a sum greater than or equal to " + sum3 + " is " +
                findMinSubarray(new int[] {3, 4, 1, 1, 6}, sum3)); // should be 3
    }

}

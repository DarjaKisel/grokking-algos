package com.dzinevich.educative._01_slidingWindow;

public class MaxSumOfSubarrayOfSizeK {

    // complexity O(N*K) (N - total number of elements, k - window size)
    // this is wrong because each time we take new sum of window, however
    // we should take into account nums that were already in previous window
    public static int findMaxSumSubarray(int k, int[] arr) {
        int maxSum = 0;
        int windowSum;

        for (int i = 0; i <= arr.length - k; i++) {
            windowSum = 0;
            for (int j = i; j < i + k; j++) {
                windowSum += arr[j];
            }
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    public static int findMaxSumSubarray_1(int k, int[] arr) {
        int windowSum = 0;
        int maxSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // add the next element
            //slide the window, we don't need to slide if we have not hit the required window size of 'K'
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart]; //subtract the left element
                windowStart++; // slide the window right
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Maximum sum of a subarray of size k: " +
                findMaxSumSubarray(3, new int[] {2, 1, 5, 1, 3, 2}));
        System.out.println("Maximum sum of a subarray of size k: " +
                findMaxSumSubarray(2, new int[] {2, 3, 4, 1, 5}));

        System.out.println("Maximum sum of a subarray of size k: " +
                findMaxSumSubarray_1(3, new int[] {2, 1, 5, 1, 3, 2}));
        System.out.println("Maximum sum of a subarray of size k: " +
                findMaxSumSubarray_1(2, new int[] {2, 3, 4, 1, 5}));
    }
}

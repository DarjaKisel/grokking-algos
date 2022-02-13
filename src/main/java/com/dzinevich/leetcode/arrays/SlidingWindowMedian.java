package com.dzinevich.leetcode.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 *
 * You are given an integer array nums and an integer k.
 * There is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10^-5 of the actual value will be accepted.
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 */
public class SlidingWindowMedian {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public double[] medianSlidingWindow(int[] nums, int k) {
        // the top is min. all elements are greater than a top
        minHeap = new PriorityQueue<>(Comparator.naturalOrder()); // 1,3,5,7
        // the top is max. all elements are less than a top
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // -1,-3,-5

        return slidingWindow(nums, k);
    }

    // TIME = O(logk), k == win size and effectively a size of our heap
    public void add(int n) {
        if (maxHeap.isEmpty() || maxHeap.peek() > n) { // if max heap top element > n -> n is good to go in max heap under maxheap top
            maxHeap.add(n);
        } else {
            minHeap.add(n);
        }
        balance();
    }

    public void balance() {
        if (maxHeap.size() > minHeap.size()+1) { // max is allowed to be 1 element bigger
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()){
            maxHeap.add(minHeap.poll());
        }
    }

    // TIME = O(logk), k == win size and effectively a size of our heap
    public void remove(int n) {
        if (n <= maxHeap.peek()) { // is n within maxHeap?
            maxHeap.remove(n);
        } else {
            minHeap.remove(n);
        }
        balance();
    }

    public double countMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }

        return maxHeap.peek()/2.0 + minHeap.peek()/2.0;
    }

    // resulting TIME = O(n * logK)
    public double[] slidingWindow(int[] nums, int k) {

        int l = 0;
        double[] res = new double[nums.length - k +1]; // if nums length =5, k =3, we'll have 3 medians in result array (5-3+1)=3

        for (int r = 0; r < nums.length; r++) { // O(n)

            add(nums[r]);

            int winSize = (r - l +1);

            if (winSize == k) {
                res[l] = countMedian();
                remove(nums[l]); // we move right - remove left el from heaps
                l++; // move window
            }
        }

        return res;
    }



    public static void main(String[] args) {
        SlidingWindowMedian sm = new SlidingWindowMedian();

        System.out.println(Arrays.toString(sm.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)) +
                "\n ex : [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]");
    }






























}

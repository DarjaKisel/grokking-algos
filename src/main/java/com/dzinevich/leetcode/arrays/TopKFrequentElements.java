package com.dzinevich.leetcode.arrays;

import java.util.*;

/**
 * Given an integer array nums and an integer k,
 * return the k most frequent elements.
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * You may return the answer in any order.
 */
public class TopKFrequentElements {

    //Min Heap
    //
    //PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //Max Heap
    //
    //PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    //

    // we use bucket sort since it has O(n) time complexity
    public int[] topKFrequent(int[] nums, int k) {

        // key - num from array
        // val - times it occur
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, 1 + count.getOrDefault(n, 0));
        }

        // max size of the map is == to the size of input array
        // as worst case is all elements are unique and there will be nums.length records
        // as best case is all elements are the there will be 1 record
        // frequency:
        // key - times
        // val - numbers that occur key times
        Map<Integer, List<Integer>> freq = new HashMap<>(nums.length);

        count.forEach((key, val) -> {
            var numsThatOccurValTimes = freq.getOrDefault(val, new ArrayList<>());
            numsThatOccurValTimes.add(key);
            freq.put(val, numsThatOccurValTimes);
        });

        int[] result = new int[k];
        int c = 0;

        for (int i = nums.length; i >=0; i--) {
            if (freq.containsKey(i)) {
                for (int n : freq.get(i)) {
                    result[c] = n;
                    c++;
                    if (c == k) {
                        return result;
                    }
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] kFrequent = new TopKFrequentElements().topKFrequent(new int[]{-1,-1}, 1);
        System.out.println(Arrays.toString(kFrequent));
    }

}

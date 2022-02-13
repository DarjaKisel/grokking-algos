package com.dzinevich.leetcode.arrays;


import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;

// WITH MAX HEAP
// java - PriorityQueue
// Time = K*logN (average)
public class TopKthLargestIntegerInTheArray_HEAP_KLogN {

    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<BigInteger> heap = new PriorityQueue<>(Comparator.reverseOrder()); // DESC, LARGEST FIRST !!!

        for (String n : nums) {
            heap.add(new BigInteger(n));
        }

      // if k = 4, then we poll 3 times and peek the 4th time
        for (int i = 0; i < k-1; i++) { // O(k)
            heap.poll();
        }

        return String.valueOf(heap.peek());
    }


    public static void main(String[] args) {
        TopKthLargestIntegerInTheArray_HEAP_KLogN T = new TopKthLargestIntegerInTheArray_HEAP_KLogN();
        System.out.println(T.kthLargestNumber(new String[]{"3", "6", "7", "10"}, 4) + " : ex 3");
        System.out.println(T.kthLargestNumber(new String[]{"2","21","12","1"}, 3) + " : ex 2");
        System.out.println(T.kthLargestNumber(new String[]{"0","0"}, 2) + " : ex 0");
    }
}

package com.dzinevich.leetcode.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums,
 * return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Length: = 4
 * The longest consecutive elements sequence is [1, 2, 3, 4].
 */
public class LongestConsecutiveSequence {

    // visualize
    // <-[1][2][3][4]-------[100]-------[200]------>
    //   |   l=4    |       | l=1|      | l=1|
    //
    // look, a sequence has NO LEFT NEIGHBOR
    //
    // 1. convert our array into a set
    // 2. take a number 100 - does it have a left neighbor? - no
    //    does our set have the value 99 - no
    // 3. is there a 101 in our set? - no
    // 4. longest seq is length 1 so far..
    //
    // 2.1. take a number 4 - does it have a left neighbor? - yes
    //    does our set have the value 3 - yes - move on then to the right
    //
    // 2.2. take a number 3 - does it have a left neighbor? - yes
    //    does our set have the value 2 - yes - move on then to the right
    //
    // and so on...

    // Time O(n) and Mem O(n)
    public int longestConsSeq(int[] nums) {
        Set<Integer> vals = new HashSet<>();
        for (int n : nums) {
            vals.add(n);
        }

        int maxResult = 0;

        for (int n : nums) {
            if (!vals.contains(n-1)) {
                int tempResult = 0;
                while (vals.contains(n + tempResult)) {
                    tempResult++;
                }
                maxResult = Math.max(maxResult, tempResult);
            }
        }

        return maxResult;
    }

    private int sillySolution(int[] nums) {
        Arrays.sort(nums); // O(n*logn)

        int length = 0;

        int p = 0;
        int temp = 1;
        while (p < nums.length-1) { // O(n)

            if (nums[p+1] - nums[p] == 1) {
                temp++;
                length = Math.max(length, temp);

            } else if (nums[p+1] - nums[p] != 0){
                length = Math.max(length, temp);
                temp = 0;
            }

            p++;
        }

        return length;
        // resulting Time = O(n*logn)
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence L = new LongestConsecutiveSequence();
        System.out.println(L.longestConsSeq(new int[]{100, 4, 200, 1, 3, 2}) + " : ex 4");
        System.out.println(L.longestConsSeq(new int[]{0,3,7,2,5,8,4,6,0,1}) + " : ex 9");
        System.out.println(L.sillySolution(new int[]{100, 4, 200, 1, 3, 2}) + " : ex 4");
        System.out.println(L.sillySolution(new int[]{0,3,7,2,5,8,4,6,0,1}) + " : ex 9");
    }
}

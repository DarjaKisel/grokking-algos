package com.dzinevich.leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of intervals
 * where intervals[i] = [starti, endi],
 * merge all overlapping intervals,
 * and return an array
 * of the non-overlapping intervals
 * that cover all the intervals in the input.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {


    // if we draw a line it becomes obvious
    //
    // |-----5-----10-----15-----20--->
    //  1--3
    //    2---6
    //          8--10     15--18
    //
    // let's sort all intervals depending on their starting number
    // as we don't really care about the end number when sorting.
    //
    // sort by start value
    // than iterate and ask if the current overlap with the previous
    public static int[][] merge(int[][] intervals) {

        // O(n * log(n))
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));

        // we don't know the size of an output arrays, so let's put them in a list
        List<int[]> outputArrays = new ArrayList<>();

        int[] currentInterval = intervals[0];
        outputArrays.add(currentInterval);

        for (int[] interval: intervals) {
            int currentEnd = currentInterval[1];

            int nextBegin = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextBegin) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                currentInterval = interval;
                outputArrays.add(currentInterval);
            }
        }

        // wow!
        // this is to convert it into a 2d array
        return outputArrays.toArray(new int[outputArrays.size()][]);
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {8,10},
                {15,18},
                {2,6},
                {1,3}
        };
        for (int[] ints : arr) {
            System.out.println(ints[0] + ", " + ints[1]);
        }

        System.out.println();

        for (int[] ints : merge(arr)) {
            System.out.println(ints[0] + ", " + ints[1]);
        }
    }

    //public int[][] merge(int[][] intervals) {
    //
    //        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
    //
    //        List<int[]> output = new ArrayList<>();
    //        int[] currentInterval = intervals[0];
    //        output.add(currentInterval);
    //
    //        for(int[] interval: intervals) {
    //
    //            int currentEnd = currentInterval[1];
    //
    //            int nextBegin = interval[0];
    //            int nextEnd = interval[1];
    //
    //            if(currentEnd >= nextBegin) {
    //                currentInterval[1] = Math.max(nextEnd, currentEnd);
    //            } else {
    //                currentInterval = interval;
    //                output.add(currentInterval);
    //            }
    //        }
    //
    //        return output.toArray(new int[output.size()][]);
    //
    //    }


}

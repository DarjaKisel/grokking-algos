package com.dzinevich.leetcode.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthElementInArray {


    /**
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     *
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     */
    public int findKthLargest(int[] nums, int k) {

        // we know that kth largest is kth element from the end
        int kthIdx = nums.length - k;

        return quickSelect(nums, 0, nums.length-1, kthIdx);
    }


    // [3,2,6,7,9,1,2,4,8,5] and k4
    // 3,2 remain on their places, i = 2, curr =2
    // 6,7,9 remain, i = 5, curr = 2
    // 1 is swapped with 6 -- [3,2,1,7,9,6,2,4,8,5],   i = 6, curr = 3
    //                               ^
    // 2 is swapped with 7 -- [3,2,1,2,9,6,7,4,8,5], i = 7, curr = 4
    //                                 ^
    // 4 is swapped with 9 -- [3,2,1,2,4,6,7,9,8,5], i = 8, curr = 5
    //                                   ^
    // 6,8 remains
    // 5 remains as for loop < 9 (exclusive) and 5 has index 9

    // [3,2,1,2,4,6,7,9,8,5]
    //            ^
    // curr is at idx 5 --> it means everything after that idx is greater than pivot nums[right]
    //
    // we do last swap
    // whatever is at curr we swap with pivot nums[right]
    // [3,2,1,2,4,5,7,9,8,6]
    //            ^

    //
    // currIdx 5, kth index is 6 (because nums.length-4=10-4=6)
    // currIdx < kth -> repeat search with left = curr+1=6, right=9

    // [3,2,1,2,4,5,7,9,8,6]
    //              ^
    // 7,9,8,6 remain as they are greater than pivot, i = 8, curr = 6
    //
    // we do last swap
    // whatever is at curr we swap with pivot nums[right]
    // [3,2,1,2,4,5,6,9,8,7]
    //              ^

    //
    // currIdx 6, kth index is 6
    // currIdx = kth -> return nums[currIdx]
    //

    // left and right - on which partition we're running the quick select now
    private int quickSelect(int[] nums, int left, int right, int kthIdx) {
        int pivot = nums[right];
        int currIdx = left;
        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                int temp = nums[currIdx];
                nums[currIdx] = nums[i];
                nums[i] = temp;
                currIdx += 1;
            }
        }
        // swap the pivot value (right most value) with the value at currIdx
        // because since all values after currIdx are greater than pivot
        // it means pivot should be between the left part and everything that is greater
        int temp = nums[currIdx]; // temp = 5
        nums[currIdx] = nums[right]; // [3] = 4
        nums[right] = temp; // piv = 5

        if (currIdx > kthIdx) {
            return quickSelect(nums, left, currIdx-1, kthIdx); // repeat on left side of the portion
        } else if (currIdx < kthIdx) { // we have not reach out our kth element yet
            return quickSelect(nums, currIdx + 1, right, kthIdx); // repeat on right side of the portion
        } else {
            return nums[currIdx];
        }
    }

    //------------------------sorting---------------------------------------
    //
    /**
     * Arrays.sort(nums);
     *  Complexity = n*logn
     */
    public int useSorting_findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    //
    //-----------------------------------------------------------------------
    //

    //
    //----------------------quick select repeat-------------------------------------
    //
    public int findKthLargestElement(int[] nums, int kth) {
        int kthIdx = nums.length - kth;
        return quickSelectRepeat(nums, 0, nums.length-1, kthIdx);
    }

    public int quickSelectRepeat(int[] nums, int left, int right, int kth) {

        int pivot = nums[right];
        int curr = left;

        for (int i = left; i < right; i++) {

            if (nums[i] <= pivot) {
                int temp = nums[curr];
                nums[curr] = nums[i];
                nums[i] = temp;

                curr += 1;
            }
        }

        //now everything after curr is greater than pivot
        int temp = nums[curr];
        nums[curr] = nums[right];
        nums[right] = temp;

        if (curr < kth) { // didnt reach yet
            return quickSelectRepeat(nums, curr+1, right, kth); // take the right portion from curr+1 to right
        } else if (curr > kth) {
            return quickSelectRepeat(nums, left, curr - 1, kth); // take the left portion from left to curr-1
        } else {
            return nums[curr];
        }

    }


    //-----------------------------heap---------------------------------------
    /**
     * ! -Use PriorityQueue- !
     *  1. heapify the array - O(n) time
     *  2. pop k times - O(logn) time
     *  complexity (n + k*logn) - if k relatively small - heap is better than sorting
     */
    public int findKthLargestElement_heap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int n : nums) {
            heap.add(n);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.poll();

    }


    public static void main(String[] args) {
        int nums[] = new int[]{3,2,1,5,6,4};
        int nums2[] = new int[]{3,2,3,1,2,4,5,5,6};
        int nums3[] = new int[]{3,2,6,7,9,1,2,4,8,5};
        System.out.println(new KthElementInArray().useSorting_findKthLargest(nums, 2) + " : expected 5");
        System.out.println(new KthElementInArray().findKthLargest(nums, 2) + " : expected 5");
        System.out.println(new KthElementInArray().findKthLargest(nums2, 4) + " : expected 4");
        System.out.println(new KthElementInArray().findKthLargest(nums3, 4) + " : expected 6");

        System.out.println(new KthElementInArray().findKthLargestElement(nums3, 4) + " : expected 6");

        System.out.println(new KthElementInArray().findKthLargestElement_heap(nums3, 4) + " : expected 6");
    }

}

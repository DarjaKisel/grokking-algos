package com.dzinevich.leetcode.sort;

import java.util.Arrays;

public class QuickSort {

    /**
     * put pivot in the right place
     * all nums at the left of pivot are < than pivot
     * all nums at the right of pivot are > than pivot
     * @return index of the pivot, the partition index
     */
    public int putPivotInRightPlace(int[] nums, int left, int right) {

        int pivot = nums[right];
        int curr = left;

        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {

                swap(nums, i, curr); //FIRST. swap

                curr++; //THEN. curr++
            }
        }

        // at this moment all elements after curr  are > than pivot
        swap(nums, curr, right);

        //if there was 1,9,8,3,2,5 it will become 1,3,2,5,9,8
        return curr;
    }

    public void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int partIndex = putPivotInRightPlace(nums, l, r);

            //left part before partition index
            quickSort(nums, l, partIndex -1);
            //right part after partition index
            quickSort(nums, partIndex +1, r);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,9,8,3,2,5};
        int[] nums2 = new int[]{1,9,8,7,-2,13,-4,-8,0,0,9,1,2,3,3,2,100,5};
        int[] nums3 = new int[]{-3, -2, -1, -9, -5, -1, -19, -33};

        new QuickSort().quickSort(nums, 0, nums.length-1);
        new QuickSort().quickSort(nums2, 0, nums2.length-1);
        new QuickSort().quickSort(nums3, 0, nums3.length-1);

        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
    }
}

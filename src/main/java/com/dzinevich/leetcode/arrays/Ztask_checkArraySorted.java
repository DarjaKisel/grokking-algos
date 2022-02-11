package com.dzinevich.leetcode.arrays;

public class Ztask_checkArraySorted {

    public boolean isSorted(int[] nums) {
        if (nums == null || nums.length < 3) {
            return true;
        }

        if (isSorted(nums, true)) {
            return true;
        } else {
            return isSorted(nums, false);
        }
    }

    public boolean isSorted(int[] nums, boolean asc) {
        int first = nums[0];
        for (int n : nums) {
            if (asc) {
                if (n < first) { //[1,2,3] asc , n should be > first
                    return false;
                } else {
                    first = n;
                }
            } else {
                if (n > first) { //[1,2,3] asc , n should be < first
                    return false;
                } else {
                    first = n;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Ztask_checkArraySorted z = new Ztask_checkArraySorted();
        System.out.println(z.isSorted(new int[]{1,2,3,4,5}) + " : ex: true");
        System.out.println(z.isSorted(new int[]{1,2,1,4,5}) + " : ex: false");
        System.out.println(z.isSorted(new int[]{1,1}) + " : ex: true");
        System.out.println(z.isSorted(new int[]{1}) + " : ex: true");
        System.out.println(z.isSorted(new int[]{}) + " : ex: true");
    }
}

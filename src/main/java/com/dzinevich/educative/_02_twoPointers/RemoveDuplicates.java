package com.dzinevich.educative._02_twoPointers;

/**
 * Given an array of sorted numbers, remove all duplicates from it
 */
public class RemoveDuplicates {

    // time complexity is O(N) where N - number of elements in the array
    public static int remove(int[] arr) {
        int uniquePositionAndCounter = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[uniquePositionAndCounter - 1] != arr[i]) {
                arr[uniquePositionAndCounter] = arr[i];
                uniquePositionAndCounter++;
            }
        }
        return uniquePositionAndCounter;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 3, 3, 3, 6, 9, 9};
        System.out.println(remove(arr1) + " Should be 4");

        int[] arr2 = new int[]{2, 2, 2, 11};
        System.out.println(remove(arr2) + " Should be 2");
    }
}

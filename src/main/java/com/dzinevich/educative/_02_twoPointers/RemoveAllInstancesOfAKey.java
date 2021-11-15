package com.dzinevich.educative._02_twoPointers;

/**
 * Given an unsorted array of numbers and a target ‘key’,
 * remove all instances of ‘key’ in-place and return the new length of the array.
 */
public class RemoveAllInstancesOfAKey {

    // time complexity is O(N) where N - number of elements in the array
    public static int remove(int[] arr, int key) {
        int notAKeyCounter = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != key) {
                arr[notAKeyCounter] = arr[i];
                notAKeyCounter++;
            }
        }

        return notAKeyCounter;

    }

    public static void main(String[] args) {
        int[] arr1 = new int[] {3, 2, 3, 6, 3, 10, 9, 3};
        int key1 = 3;
        System.out.println(remove(arr1, key1) +  " Should be 4");

        int[] arr2 = new int[] {2, 11, 2, 2, 1};
        int key2 = 2;
        System.out.println(remove(arr2, key2) +  " Should be 2");
    }
}

package com.dzinevich.educative.twoPointers;

import java.util.HashMap;

public class PairWithTargetSum {

//    public static int[] search(int[] arr, int target) {
//        int left = 0;
//        int right = arr.length - 1;
//
//        while (left < right) {
//            int currentSum = arr[left] + arr[right];
//            if (currentSum == target) {
//                return new int[] {left, right}; // found the pair
//            }
//            if (target > currentSum) {
//                left++; //we need a pair with a bigger sum so we could try increasing the left number
//            } else {
//                right--; //we need a pair with a smaller sum so we could try decreasing the right number
//            }
//        }
//
//        return new int[] {-1, -1};
//    }

    public static void main(String[] args) {
//        int[] result = search(new int[] {1, 2, 3, 4, 6}, 6);
//        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]"); // should be [1, 3]
//        result = search(new int[] {2, 5, 9, 11}, 11);
//        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]"); //should be [0, 2]
        //
        //
        //
        int [] result = searchWithHashTable(new int[] {1, 2, 3, 4, 6}, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]"); // should be [1, 3]
        result = searchWithHashTable(new int[] {2, 5, 9, 11}, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]"); //should be [0, 2]
    }

    // the time complexity is O(N) where N - number of elements in array

    public static int[] search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (currentSum == target) {
                return new int[] {left, right}; //found the pair!
            }
            if (currentSum < target) {
                left++; //the sum is too small, try to increase the left number
            } else {
                right--; //the sum is too big, try to decrease the right number
            }
        }
        return new int[] {-1, -1};
    }

    // ALTERNATIVE approach
//    public static int[] searchWithHashTable(int[] arr, int targetSum) {
//        HashMap<Integer, Integer> numbers = new HashMap<>(); //store numbers and their indexes
//
//        for (int i = 0; i < arr.length; i++) {
//            if (numbers.containsKey(targetSum - arr[i])) { // if there is such a number to compose a sum
//                return new int[] {numbers.get(targetSum - arr[i]), i};
//            } else {
//                numbers.put(arr[i], i); // put a number and it's index to the map
//            }
//        }
//        return new int[] {-1, -1}; // not found
//    }

    // repeating
    // the time complexity is O(N) where N - number of elements in array
    // worst case - we just put the entire arr (all N elements) in the hashtable
    public static int[] searchWithHashTable(int[] arr, int targetSum) { // {1,2,3}, 4
        HashMap<Integer, Integer> numbersAndIndexes = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (numbersAndIndexes.containsKey(targetSum - arr[i])) { // do we have 4 - 1 = 3 ?
                return new int[] {numbersAndIndexes.get(targetSum - arr[i]), i};
            } else {
                numbersAndIndexes.put(arr[i], i); // 1, 0
            }
        }
        return new int[] {-1, -1};
    }


}

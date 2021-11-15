package com.dzinevich.educative._02_twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletSumToZero {

    /**
     * sorting takes O(N∗logN)
     * searchPair takes O(N)
     * as we're calling searchPair for every number,
     * the overall searchTriplets will take O(N∗logN+N^2) which is == O(N^2)
     */
    private static List<List<Integer>> searchTriplets(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i + 1]) { // skip same elements to avoid duplicate triplets
                // i > 0 means that we have already processed at lest the first element
                continue;
            }
            searchPair(arr, -arr[i], i + 1, triplets);
            // -arr[i] -- means -X = Y + Z since we find a pair y + z whose sum is eq to -x
            // i + 1 -- means left since we start looking for a pair beginning from Y in -X = Y + Z statement
        }
        return triplets;
    }

    private static void searchPair(int[] arr, int targetSum, int left, List<List<Integer>> triplets) {
        int right = arr.length - 1;
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (currentSum == targetSum) { // found the triplet
                triplets.add(Arrays.asList(-targetSum, arr[left], arr[right]));// don't forget to put - minus targetSum

                left++;
                right--;

                while (left < right && arr[left] == arr[left - 1]) {
                    left++; // skip same elements to avoid duplicate triplets
                }
                while (left < right && arr[right] == arr[right + 1]) {
                    right--; // skip same elements to avoid duplicate triplets
                }
            } else if (targetSum > currentSum) {
                left++; //we need a pair with a bigger sum
            } else {
                right--; // we need a pair with a smaller sum
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(searchTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2}));
        System.out.println("Expected: [[-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]]");
        System.out.println("------------------------------");
        System.out.println(searchTriplets(new int[]{-5, 2, -1, -2, 3}));
        System.out.println("Expected: [[-5, 2, 3], [-2, -1, 3]]");
    }
}

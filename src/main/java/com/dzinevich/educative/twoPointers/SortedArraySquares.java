package com.dzinevich.educative.twoPointers;

/**
 * Given a sorted array, create a new array
 * containing squares of all the numbers
 * of the input array in the sorted order.
 */
public class SortedArraySquares {

    // The time complexity of the above algorithm will be O(N) as we are iterating the input array only once.
    public static int[] repeatMakeSquares(int[] arr) {
        int l = arr.length;
        int[] squares = new int[l];

        int highestSquareNrIdx = l - 1; //array is sorted, so the highest square will be put at the end

        int left = 0; // pointer at the beginning
        int right = arr.length - 1; // pointer at the end

        while (left <= right) { // make sure we process the middle element <=
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];

            if (leftSquare > rightSquare) { // if the left was bigger then we say that it should be put at the end of the new arr
                squares[highestSquareNrIdx] = leftSquare;
                left++; // move pointer one step right
            } else {
                squares[highestSquareNrIdx] = rightSquare; // if left wasn't bigger - we put the right at the end of the new arr
                right--; // move second pointer one step left
            }
            highestSquareNrIdx--; // we just put something bit on the end, nothing can be bigger than it, we decrement index for the next square number
        }
        return squares;
    }

    public static void main(String[] args) {
        int[] result = repeatMakeSquares(new int[] {-2, -1, 0, 2, 3});
        for (int num : result) {
            System.out.print(num + ", ");
        }
        System.out.println("Should be 0 1 4 4 9");

        int[] result2 = repeatMakeSquares(new int[] {-3, -1, 0, 1, 2});
        for (int num : result2) {
            System.out.print(num + ", ");
        }
        System.out.println("Should be 0 1 1 4 9");
    }
}

package com.dzinevich.leetcode.binary;

//TODO - i still don't get it. need to revisit
public class FirstMissingPositive {

    public int find(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) { // neutralize enegative values as the are useless for us
                A[i] = 0;
            }
        }

        for (int i = 0; i < A.length; i++) {

            int val = Math.abs(A[i]);

            if (1 <= A[i] && A[i] <= A.length) { // map if a value exist in arr or not (only positive)

                if (A[val -1] > 0) {
                    A[val - 1] *= (-1); // override it to be negative if it exists, if it's already negative - do nothing

                } else if (A[val -1] == 0) {
                    A[val-1] = -1*(A.length + 1);
                }

            }
        }

        for (int i = 1; i < A.length + 1; i++) {
            if(A[i-1] >= 0) {
                return i;
            }
        }

        return A.length+1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstMissingPositive().find(new int[]{1, 0, -2, 3}) +  " should be 2");
    }
}

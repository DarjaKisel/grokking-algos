package com.dzinevich.leetcode.slidingWindow;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] arr) {
        int left = 0;           //left - buy
        int right = left + 1;   //right - sell

        int maxProfit = 0;

        while (right < arr.length) {
            //is this a profitable transaction?

            if (arr[left] < arr[right]) {
                int profit = arr[right] - arr[left];
                maxProfit = Math.max(maxProfit, profit);
            } else {
                // move left to the right, since right is now the lowest position that we know
                left = right;
            }
            right++;
        }

        return maxProfit;
    }


    public static void main(String[] args) {
        int maxProfit = maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(maxProfit);
    }
}

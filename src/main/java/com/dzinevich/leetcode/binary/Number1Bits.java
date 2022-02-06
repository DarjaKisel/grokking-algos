package com.dzinevich.leetcode.binary;

/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 *
 * TODO: investigate Java solution
 */
public class Number1Bits {

    // 1011 %2 -- mod it by 2
    // shift bit to the right ->
    // 101 %2 -- mod it by 2
    // shift bit to the right ->
    // 10 %2 -- mod it by 2
    // shift bit to the right ->
    // 1 %2 -- mod it by 2
    // shift bit to the right ->
    // 0 %2 -- mod it by 2
    public int hammingWeight(int num) {
        int result = 0;

        while (num > 0) {
            result += num % 2;
            num = num >>> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Number1Bits nb = new Number1Bits();

        System.out.println(nb.hammingWeight(Integer.parseInt("00000000000000000000000000001011")) + " : expected : 3");
        System.out.println(nb.hammingWeight(Integer.parseInt("00000000000000000000000010000000")) + " : expected : 1");
        System.out.println(nb.hammingWeight(Integer.parseInt("-3")) + " : expected : 31");
    }
}

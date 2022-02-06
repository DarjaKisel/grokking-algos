package com.dzinevich.leetcode.binary;

/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 *
 * TODO: investigate Java solution
 */
public class Number1Bits {

    //num & 1 :  0 & 1 == 0, 1 & 1 == 1
    public int hammingWeight(int num) {
        int result = 0;

        while (num != 0) {
            result += (num & 1);
            num = num>>>1;
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

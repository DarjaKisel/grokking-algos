package com.dzinevich.leetcode.strings;

/**
 * The power of the string is the maximum length of a non-empty substring
 * that contains only one unique character.
 *
 * Given a string s, return the power of s.
 * leetcode - ee - power is 2
 */
public class ConsecutiveCharacters {

    public int maxPower(String s) {
        int max = 1;

        int l = 0;
        int r = l +1;

         while (r < s.length()){
             int times = 1;
             while (r < s.length() && s.charAt(l) == s.charAt(r)) {
                 times++;
                 r++;
             }
             l = r;
             r++;
             max = Math.max(max, times);
         }

         return max;
    }

    public static void main(String[] args) {
        System.out.println(new ConsecutiveCharacters().maxPower("leetcode") + " : ex 2");
        System.out.println(new ConsecutiveCharacters().maxPower("abbcccddddeeeeedcba") + " : ex 5");
        System.out.println(new ConsecutiveCharacters().maxPower("ccbccbb") + " : ex 2");
    }

}

package com.dzinevich.leetcode.slidingWindow;

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 *  Example 2:
 *
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromicSubstrings {

    private String s;
    private int result = 0;

    public int countSubstrings(String s) {
        this.s = s;

        for (int i = 0; i < s.length(); i++) { // we consider i as a centre of a palindrome

            // ODD length palindrome
            int left = i;
            int right = i;

            result = countPalindromes(left, right);

            // EVEN length palindrome
            left = i;
            right = left +1;

            result = countPalindromes(left, right);
        }


        return result;
    }

    private int countPalindromes(int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            result = result + 1;

            left--; // expand!
            right++;
        }

        return result;
    }

    public static void main(String[] args) {
        PalindromicSubstrings ps = new PalindromicSubstrings();
        System.out.println(ps.countSubstrings("abc") + " : expected : 3");
        System.out.println(ps.countSubstrings("aaa") + " : expected : 6");
    }
}

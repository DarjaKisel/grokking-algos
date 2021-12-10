package com.dzinevich.leetcode.dynamicProgramming;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindrome {

    public static String longestPalindrome(String input) {
        String result = "";
        int maxLength = 0;

        // go through every single position in this string
        // considering it as a middle of a palindrome

        for (int i = 0; i < input.length(); i++) {
            //ODD length palindrome
            int left = i;
            int right = i;

            while (left >= 0 && right < input.length()
             && input.charAt(left) == input.charAt(right)) {
                // it's a palindrome!!
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    result = String.valueOf(input.subSequence(left, right + 1));
                }

                left--;
                right++;
            }

            //EVEN length palindrome
            left = i;
            right = i + 1;

            while (left >= 0 && right < input.length()
                    && input.charAt(left) == input.charAt(right)) {
                // it's a palindrome!!
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    result = String.valueOf(input.subSequence(left, right + 1));
                }

                left--;
                right++;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(repeatLongestPalindrome("babad"));
        System.out.println("Should be 'bab'\n\n");
        System.out.println(repeatLongestPalindrome("cbbd"));
        System.out.println("Should be 'bb'\n\n");
        System.out.println(repeatLongestPalindrome("a"));
        System.out.println("Should be 'a'\n\n");
        System.out.println(repeatLongestPalindrome("ac"));
        System.out.println("Should be 'a'\n\n");
    }




    private static String repeatLongestPalindrome(String s) {
        String result = "";
        int maxLength = 0;

        // loop the string and every char a possible centre of a palindrome
        for (int i = 0; i < s.length(); i++) {

            //ODD Length palindrome like bab or aba

            int left = i;
            int right = i;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    result = String.valueOf(s.subSequence(left, right + 1));
                }
                left--;
                right++;
            }

            // EVEN Length palindrome like assa or dffd or gg

            left = i;
            right = i + 1;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    result = String.valueOf(s.subSequence(left, right + 1));
                }
                left--;
                right++;
            }
        }

        return result;
    }
}

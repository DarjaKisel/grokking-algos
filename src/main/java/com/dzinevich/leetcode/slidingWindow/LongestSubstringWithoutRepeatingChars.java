package com.dzinevich.leetcode.slidingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s,
 * find the length of the longest substring without repeating characters.
 * see {@link com.dzinevich.educative._01_slidingWindow.LongestSubsctringKDistinct}
 *
 * Examples:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingChars {

    public static int lengthOfLongestSubstringDistinct(String s) {
        int result = 0;

        Set<Character> uniqueChars = new HashSet<>();

        // create a sliding window
        // need two pointers
        int left = 0;

        // right wil expand to the right
        for (int right = 0; right < s.length(); right++) {

            //while there's a duplicate in the substring
            while(uniqueChars.contains(s.charAt(right))) {
                // FIRST REMOVE
                uniqueChars.remove(s.charAt(left));
                // THEN MOVE LEFT
                left++;
            }

            uniqueChars.add(s.charAt(right));

            result = Math.max(result, uniqueChars.size());
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstringDistinct(s) + " -> should be 3");

        String s1 = "pwwkew";
        System.out.println(lengthOfLongestSubstringDistinct(s1) + " -> should be 3");
    }


    //repeat
    //
    public int lengthOfLongestSubstring(String s) {

        int result = 0;

        Set<Character> uniqueChars = new HashSet<>();

        //define the window
        int left = 0;
        for(int right = 0; right < s.length(); right++) {

            while(uniqueChars.contains(s.charAt(right))) {
                //remove from the left
                uniqueChars.remove(s.charAt(left));
                // shrink the window from the left
                left++;
            }

            // once there are no more duplicates - expand the window to the right
            uniqueChars.add(s.charAt(right));

            //maybe that's the biggest result so far
            result = Math.max(result, uniqueChars.size());
        }

        return result;
    }
}

package com.dzinevich.leetcode.slidingWindow;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter
 * you can get after performing the above operations.
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 */
public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {

        int maxResult = 0;

        Map<Character, Integer> countChars = new HashMap<>(26);

        //initial window L and R are at zero
        int left = 0;
        // right is self-increasing
        for (int right = left; right < s.length(); right++) { // this is time O(n)

            // 1. increment count for charAt(right)
            countChars.put(s.charAt(right), 1 + countChars.getOrDefault(s.charAt(right), 0));

            // 2. check if window valid for K replacements
            // if the length of window minus most frequent char count is <= K - then yes
            int mostFreq = countChars.values().stream().max(Comparator.naturalOrder()).get();
            while ((right - left + 1) - mostFreq > k) { // this is time O(26)
                countChars.put(s.charAt(left), countChars.get(s.charAt(left)) -1);
                left++;
            }

            // 3. update our result
            maxResult = Math.max(maxResult, right - left + 1);
        }

        return maxResult;

    }


    public static void main(String[] args) {
        var L = new LongestRepeatingCharacterReplacement();
        System.out.println(L.characterReplacement("ABABBA", 2) + " : expected : 5");
    }





}

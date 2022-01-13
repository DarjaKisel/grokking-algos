package com.dzinevich.leetcode.dynamicProgramming;

import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *
 * Note that you are allowed to reuse a dictionary word.
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] cache = new boolean[s.length()+1];
        cache[s.length()] = true;

        for (int i = s.length() - 1; i >=0; i--) {
            for (String disct : wordDict) {

                if (i + disct.length() <= s.length() // enough characters
                    && s.substring(i, i+disct.length()).equals(disct)) { // strings are equal
                    cache[i] = cache[i + disct.length()];
                    if (cache[i]) {
                        break;
                    }
                }
            }
        }

        return cache[0];
    }


    public static void main(String[] args) {
        System.out.println(new WordBreak().wordBreak("leetcode",  List.of("leet", "code")) + " should be true");
//        System.out.println(new WordBreak().wordBreak("leetcode",  List.of("neet", "code")) + " should be false");
//        System.out.println(new WordBreak().wordBreak("leetcodeleet",  List.of("code", "leet")) + " should be true");
    }
}

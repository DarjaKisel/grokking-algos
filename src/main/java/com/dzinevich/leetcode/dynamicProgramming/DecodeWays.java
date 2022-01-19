package com.dzinevich.leetcode.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 *  message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 */
public class DecodeWays {

    Map<Integer, Integer> cache = new HashMap<>();

    //TODO kinda get it, need to revisit
    public int numDecodings(String s) {
        cache.put(s.length(), 1);

//        for (int i = s.length()-1; i >=0; i--) {
//
//            if (s.charAt(i) == '0') { //invalid
//                cache.put(i, 0);
//            } else {
//                cache.put(i, cache.get(i+1));
//            }
//
//            if (i + 1 < s.length()) {
//                if (s.charAt(i) == '1' || (s.charAt(i) == '2' && Integer.parseInt(String.valueOf(s.charAt(i + 1))) < 7)) {
//                    cache.put(i, cache.get(i) + cache.get(i+2));
//                }
//            }
//        }
//        return cache.get(0);

        return dfs(0, s);
    }

    public int dfs(int i, String s) {
        if (cache.containsKey(i)) { // good base case
            return cache.get(i);
        }

        if (s.charAt(i) == '0') { //bad base case
            return 0;
        }

        int result = dfs(i+1, s);
        if (i + 1 < s.length()) {
            if (s.charAt(i) == '1' || (s.charAt(i) == '2' && Integer.parseInt(String.valueOf(s.charAt(i + 1))) < 7)) {
                result += dfs(i + 2, s);
            }
        }

        cache.put(i, result);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("12") + " : should be 2");
        System.out.println(new DecodeWays().numDecodings("226") + " : should be 3");
        System.out.println(new DecodeWays().numDecodings("06") + " : should be 0");
    }
}

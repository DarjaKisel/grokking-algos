package com.dzinevich.leetcode.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagrams {

    // O ( m * n * 26) === O(mn)
    // m - words in input arr
    // n = average length of a word

    public List<List<String>> groupAnagrams(String[] strs) {

        // we're going to have a HashMap
        // key - [a-1, e-1, t-1] - letters count
        // value - [eat, tea, ate] - words that consist of these letters

        Map<Map<Character, Integer>, List<String>> result = new HashMap<>();

        for (String s : strs) {

            char[] chars = s.toCharArray();

            Map<Character, Integer> charsCount = new HashMap<>();

            for (char c : chars) {
                charsCount.put(c, 1+ charsCount.getOrDefault(c, 0));
            }

            List<String> wordsMadeOfTheseChars = result.getOrDefault(charsCount, new ArrayList<>());
            wordsMadeOfTheseChars.add(s);

            result.put(charsCount, wordsMadeOfTheseChars);
        }

        return new ArrayList<>(result.values());
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();

        System.out.println(ga.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println("[[\"bat\"],[\"nat\",\"tan\"],[\"ate\",\"eat\",\"tea\"]] <== expected");

        System.out.println(ga.groupAnagrams(new String[]{"ac", "d"}));
        System.out.println("[[\"d\"],[\"ac\"]] <== expected");
    }
}

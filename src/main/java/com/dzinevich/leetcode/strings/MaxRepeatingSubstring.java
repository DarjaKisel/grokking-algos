package com.dzinevich.leetcode.strings;

import java.util.HashMap;
import java.util.Map;

public class MaxRepeatingSubstring {

    // seq = "abababsab word = ab
    public int maxRepeating(String sequence, String word) {

        String find = word;
        int count = 0;

        while (sequence.contains(find)) {
            count++;
            find += word;
        }

        return count;
    }


    public int maxSameTLetters(String str) {
        Map<Character, Integer> map = new HashMap<>(26);
        int count = 0;

        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
            count = Math.max(count, map.get(c));
        }

        return count;
    }


    public static void main(String[] args) {
        System.out.println(new MaxRepeatingSubstring().maxRepeating("abababsab", "ab") + " : expected 3");
        System.out.println(new MaxRepeatingSubstring().maxRepeating("abababsab", "s") + " : expected 1");

        System.out.println(new MaxRepeatingSubstring().maxSameTLetters("asad") + " : expected 2");
        System.out.println(new MaxRepeatingSubstring().maxSameTLetters("asaadddd") + " : expected 4");
    }
}

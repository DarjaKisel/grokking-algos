package com.dzinevich.leetcode.strings;

import java.util.Arrays;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] sCharCount = new int[26];
        int[] tCharCount = new int[26];

        for (char c : s.toCharArray()) {
            sCharCount[c - 'a'] += 1;
        }

        for (char c : t.toCharArray()) {
            tCharCount[c - 'a'] += 1;
        }

        return Arrays.equals(sCharCount, tCharCount);
    }

    public static void main(String[] args) {
        System.out.println(new ValidAnagram().isAnagram("anagram", "nagaram") + ": should be true");
        System.out.println(new ValidAnagram().isAnagram("rat", "cat") + ": should be false");
    }
}

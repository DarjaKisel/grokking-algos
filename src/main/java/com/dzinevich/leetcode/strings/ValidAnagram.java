package com.dzinevich.leetcode.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        ValidAnagram va = new ValidAnagram();

        System.out.println(va.isAnagram("anagram", "nagaram") + ": should be true");
        System.out.println(va.isAnagram("rat", "cat") + ": should be false");

        System.out.println(va.isValidAnagram("anagram", "nagaram") + ": should be true");
        System.out.println(va.isValidAnagram("rat", "cat") + ": should be false");

        System.out.println(va.isValidAnagram_compareArrays("anagram", "nagaram") + ": should be true");
        System.out.println(va.isValidAnagram_compareArrays("rat", "cat") + ": should be false");
    }

    private boolean isValidAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> countS = new HashMap<>();
        Map<Character, Integer> countT = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            countS.put(s.charAt(i), 1+ countS.getOrDefault(s.charAt(i), 0));
            countT.put(t.charAt(i), 1+ countT.getOrDefault(t.charAt(i), 0));
        }

        return countS.equals(countT);
    }

    private boolean isValidAnagram_compareArrays(String s, String t) {

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        Arrays.sort(sArr);
        Arrays.sort(tArr);

        return Arrays.equals(sArr, tArr);

    }
}

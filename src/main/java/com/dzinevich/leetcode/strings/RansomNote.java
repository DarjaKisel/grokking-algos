package com.dzinevich.leetcode.strings;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {

        Map<Character, Integer> mapChars = new HashMap<>(26);

        for(char c : magazine.toCharArray()) { // O(m)
            mapChars.put(c, 1 + mapChars.getOrDefault(c, 0));
        }

        for (char c : ransomNote.toCharArray()) { // O(n)
            if (!mapChars.containsKey(c)) {
                return false;
            }
            if (mapChars.containsKey(c)) {
                if (mapChars.get(c) < 1) {
                    return false;
                }
                mapChars.put(c, mapChars.get(c) -1);
            }
        }

        return true;
        // result TIME = O(m+n)
    }

    public static void main(String[] args) {
        RansomNote rn = new RansomNote();
        System.out.println(rn.canConstruct("a", "b") + " : ex false");
        System.out.println(rn.canConstruct("aa", "ab") + " : ex false");
        System.out.println(rn.canConstruct("aa", "aab") + " : ex true");
    }
}

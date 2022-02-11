package com.dzinevich.leetcode.twoPointers;

public class Ztask_countPatterns {

    // "abab" pattern "ab" -> return 2
    // "aaa" pattern "a" -> return 3
    // "abcab" pattern "ab" -> return 2
    // "accbccaccb" pattern "ab" -> return 2
    public int countPatterns(String s, String p) {
        int result = 0;

        int left = 0;

        while (left < s.length()) { // iterate through string
            int right = 0;
            while (right < p.length() && left < s.length()) { // iterate through pattern, but this is not a loop inside a loop, we move to the right, both pointers move
                if (s.charAt(left) == p.charAt(right)) {
                    right++;
                }
                left++;
            }

            if (right == p.length()) {
                result+=1;
            }

        }
        return result;
        // should be O(n)
    }

    public static void main(String[] args) {
        Ztask_countPatterns z = new Ztask_countPatterns();
        System.out.println(z.countPatterns("abab", "ab") + " : should be 2");
        System.out.println(z.countPatterns("aaa", "a") + " : should be 3");
        System.out.println(z.countPatterns("abcab", "ab") + " : should be 2");
        System.out.println(z.countPatterns("accbccaccb", "ab") + " : should be 2");
        System.out.println(z.countPatterns("accbccabccb", "ab") + " : should be 2");
        System.out.println(z.countPatterns("accbaccabccb", "ab") + " : should be 2");
    }
}

package com.dzinevich.leetcode.strings;

public class ReverseString {
    // use reverse()
    public String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public String reverse_inPlace(String s) {
        return helper(s.toCharArray(), 0, s.length() -1);
    }

    public String helper(char[] s, int left, int right) {
        if (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            helper(s, left+1, right-1);
        }
        return new String(s);
    }

    public static void main(String[] args) {
        System.out.println(new ReverseString().reverse("abcde"));
        System.out.println(new ReverseString().reverse_inPlace("abcde"));
    }

}

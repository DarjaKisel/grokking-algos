package com.dzinevich.leetcode.strings;

public class ValidPalindrome {


    // !! SOLUTION !!
    //
    // REMOVE ALL SPECIAL CHARACTERS like "'" , "," "-", spaces, etc
    // BUILD A NEW REVERSED STRING
    // AND CHECK IF IT IS EQUAL TO THE ORIGINAL ONE
    //
    public boolean validPalindrome(String s) {

        StringBuilder sb = new StringBuilder(s);

        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(String.valueOf(c).toLowerCase());
            }
        }

        String result = sb.toString();
        String reverse = sb.reverse().toString();
        return result.equals(reverse);
    }

    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();

        System.out.println(vp.validPalindrome("A man, a plan, a canal: Panama") + " : expected: true");
        System.out.println(vp.validPalindrome("race a car") + " : expected: false");
    }
}

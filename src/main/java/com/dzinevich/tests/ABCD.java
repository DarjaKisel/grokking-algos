package com.dzinevich.tests;

import java.util.HashMap;
import java.util.Map;

public class ABCD {
    StringBuilder str;
    Map<Character, Character> combinationsMap = new HashMap<>(){
        {
            put('A', 'B');
            put('C', 'D');
        };
    };

    public String newSolution(String S) {
        str = new StringBuilder(S);
        findAndRemoveABCD(0);

        return str.toString();
    }

    // i is a pointer to the curr position
    private void findAndRemoveABCD(int i) {
        if (str == null || str.length() < 2 || i >= str.length()) {
            return;
        }

        int length = str.length();

        combinationsMap.forEach((a, b) -> {
            tryRemove(i, a, b);
            tryRemove(i, b, a);
        });

        findAndRemoveABCD(length != str.length() ? i : i+1);
        // we move pointer only if we don't reduce str.length =? O(n*combinations)
        // if we didn't find anyth in first 4 chars, we don't have to repeat again
        // we start from fifth and find all combination nearby,
        // if the str reduces - we update index i inside tryRemove()
    }

    private void tryRemove(int i, char a, char b) {
        if (str == null || str.length() < 2 || i >= str.length()) {
            return;
        }

        if (str.charAt(i) == a) {
            if ((i - 1) >= 0 && (i - 1) < str.length()) { //check if left position is valid
                if (str.charAt(i - 1) == b) {
                    str.deleteCharAt(i - 1);
                    str.deleteCharAt(i - 1);
                }
            }
            if ((i + 1) >= 0 && (i + 1) < str.length()) { //check if right position is valid
                if (str.charAt(i + 1) == b) {
                    str.deleteCharAt(i + 1);
                    str.deleteCharAt(i);
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new ABCD().newSolution("CBACD") + " should be C");
        System.out.println(new ABCD().newSolution("CABABD") + " should be ' '");
        System.out.println(new ABCD().newSolution("ACBDACBD") + " should be ACBDACBD");
        System.out.println(new ABCD().newSolution("ACBDCACBD") + " should be ACCBD");
    }


    /**
     *
     *
     *
     * below is what I proposed during the test
     */

    String[] combinations = new String[]{"AB", "BA", "CD", "DC"};
    int original;
    StringBuilder input;

    public String solution(String S) {
        original = S.length();
        input = new StringBuilder(S);

        return findAndRemove(input).toString();
    }

    public StringBuilder findAndRemove(StringBuilder input) {
        if (input == null || input.length() < 2){
            return input;
        }

        int left = 0;
        int right = left +1;

        // O(n^2*combinations)
        while (right < input.length()) {
            char l = input.charAt(left);
            char r = input.charAt(right);

            StringBuilder sb = new StringBuilder();
            sb.append(l);
            sb.append(r);
            String res = sb.toString();

            for (String s : combinations) {
                if (res.equals(s)) {
                    input.delete(left, right+1);
                    break;
                }
            }

            right++;
            left++;
        }

        if (input.length() == original) {
            return input;
        }

        return findAndRemove(input);
    }


//    public static void main(String[] args) {
//        System.out.println(new CBBA().solution("CBACD") + " should be C");
//        System.out.println(new CBBA().solution("CABABD") + " should be ' '");
//        System.out.println(new CBBA().solution("ACBDACBD") + " should be ACBDACBD");
//    }
}

package com.dzinevich.tests;

public class ABCD {
    StringBuilder str;

    public String newSolution(String S) {
        str = new StringBuilder(S);
        findAndRemoveABCD(0);

        return str.toString();
    }

    private void findAndRemoveABCD(int i) {
        if (str == null || str.length() < 2 || i >= str.length()) {
            return;
        }

        int length = str.length();

        tryRemove(i, 'A', 'B');
        tryRemove(i, 'B', 'A');
        tryRemove(i, 'C', 'D');
        tryRemove(i, 'D', 'C');;

        findAndRemoveABCD(length != str.length() ? i : i+1);
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

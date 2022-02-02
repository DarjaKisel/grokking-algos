package com.dzinevich.leetcode.strings;

import org.junit.Assert;

public class PigLatin {

    public static void main(String[] args) {
        System.out.println(new PigLatin().toPigLatin("I speak Goat Latin") + " : expected: \"Imaa peaksmaaa oatGmaaaa atinLmaaaaa\"");
        Assert.assertEquals(new PigLatin().toPigLatin("I speak Goat Latin"), "Imaa peaksmaaa oatGmaaaa atinLmaaaaa");
        System.out.println(new PigLatin().toPigLatin("yDumm") + " : expected: \"Dummymaa\"");
        Assert.assertEquals(new PigLatin().toPigLatin("yDumm"), "Dummymaa");
    }

    public String toPigLatin(String sentence) {
        String[] strings = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < strings.length; i++) {
            if (result.length() != 0) {
                result.append(" ");
            }

            char first = strings[i].charAt(0);
            if (first == 'a' || first == 'A'
                    || first == 'o' || first == 'O'
                    || first == 'u' || first == 'U'
                    || first == 'i' || first == 'I'
                    || first == 'e' || first == 'E') {
                strings[i] += "ma";
            } else {
                strings[i] = strings[i].substring(1) + first + "ma";
            }

            strings[i] += "a".repeat(i + 1); // i + 1 because we begin with 0 and we need to start with at least 1 :)
            result.append(strings[i]);
        }

        return result.toString();
    }

}

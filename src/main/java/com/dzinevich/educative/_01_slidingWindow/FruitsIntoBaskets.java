package com.dzinevich.educative._01_slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class FruitsIntoBaskets {

    //complexity O(N) where N == chars.length
    public static int findMaxFruitsIn2Baskets(char[] chars) {
        int windowStart = 0;
        int maxLength = 0;

        Map<Character, Integer> uniqueFruitsMap = new HashMap<>();
        //try to expand window size, move it to the right
        for (int windowEnd = 0; windowEnd < chars.length; windowEnd++) {
            uniqueFruitsMap.put(chars[windowEnd], uniqueFruitsMap.getOrDefault(chars[windowEnd], 0) + 1);
            //if there are already 2 or more unique chars - try to shrink the window from the left
            while (uniqueFruitsMap.size() > 2) {
                char leftChar = chars[windowStart];
                uniqueFruitsMap.put(leftChar, uniqueFruitsMap.get(leftChar) - 1);
                if (uniqueFruitsMap.get(leftChar) == 0) {
                    uniqueFruitsMap.remove(leftChar);
                    windowStart++; //get out the left element
                }
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    // equal to 'Longest Substring with at most 2 distinct characters'
    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                FruitsIntoBaskets.findMaxFruitsIn2Baskets(new char[] { 'A', 'B', 'C', 'A', 'C'}) +
                " should be 3");
        System.out.println("Maximum number of fruits: " +
                FruitsIntoBaskets.findMaxFruitsIn2Baskets(new char[] { 'A', 'B', 'C', 'B', 'B', 'C'}) +
                " should be 5");
    }
}

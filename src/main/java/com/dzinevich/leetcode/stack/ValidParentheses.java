package com.dzinevich.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 *
 * Examples:
 *
 * Input: s = "()"
 * Output: true
 *
 * Input: s = "()[]{}"
 * Output: true
 *
 * Input: s = "(]"
 * Output: false
 *
 * Input: s = "([)]"
 * Output: false
 *
 * Input: s = "{[]}"
 * Output: true
 */
public class ValidParentheses {

    public static boolean isValid(String input) {
        Stack<Character> stack = new Stack<>();
        //define 3 types of parentheses that match each other
        //for every closing parentheses there is a matching opening one
        Map<Character, Character> closeToOpen = new HashMap<>(3);
        closeToOpen.put('}', '{');
        closeToOpen.put(']', '[');
        closeToOpen.put(')', '(');

        for (int i = 0; i < input.length(); i++) {

            // if we found a closing parentheses
            if (closeToOpen.containsKey(input.charAt(i))) {
                //make sure our stack is not empty meaning that we actually have something to close
                if (!stack.empty() && stack.peek() == closeToOpen.get(input.charAt(i))) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(input.charAt(i));
            }
        }

        return stack.empty();
    }


    // repeat
    public static boolean repeatIsValid(String s) {
        Stack<Character> stack = new Stack<>();

        Map<Character, Character> closeToOpen = new HashMap<>(3);
        closeToOpen.put('}', '{');
        closeToOpen.put(']', '[');
        closeToOpen.put(')', '(');

        for (int i = 0; i < s.length(); i++) {

            if (!stack.empty() && closeToOpen.containsKey(s.charAt(i))) {
                if (stack.peek() != closeToOpen.get(s.charAt(i))) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.empty();
    }


    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.validParentheses_repeat("()") + " Should be true");
        System.out.println(vp.validParentheses_repeat("()[]{}") + " Should be true");
        System.out.println(vp.validParentheses_repeat("(]") + " Should be false");
        System.out.println(vp.validParentheses_repeat("([)]") + " Should be false");
        System.out.println(vp.validParentheses_repeat("{[]}") + " Should be true");
    }

    public boolean validParentheses_repeat(String s) {

        Stack<Character> stack = new Stack<>();

        Map<Character, Character> closeToOpen = new HashMap<>(3);
        closeToOpen.put('}', '{');
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');

        for (int i = 0; i < s.length(); i++) {

            if (!stack.empty() && closeToOpen.containsKey(s.charAt(i))) {

                if (stack.peek() != closeToOpen.get(s.charAt(i))) {
                    return false;

                } else {
                    stack.pop();
                }

            } else {
                stack.push(s.charAt(i));
            }
        }

        return true;
    }
}

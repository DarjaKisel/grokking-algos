package com.dzinevich.leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParentheses {

    //
    // 1. if open count < n - add open (
    // 2. if closed < open - you can add closed )
    // 3. valid if open == closed == 3
    //

    Stack<String> stack;
    List<String> result;

    public List<String> generateParentheses(int n) {

        stack = new Stack<>();
        result = new ArrayList<>();

        backTrack(0, 0, n);
        return result;
    }

    public void backTrack(int open, int closed, int n) {
        // base case
        if(open == closed && closed == n) {
            result.add(stack.stream().reduce((s1, s2) -> s1 + s2).get());
        }

        // open < n - can add more (
        if (open < n) {
            stack.push("(");
            backTrack(open +1, closed, n);
            stack.pop(); // ????
        }

        // some of open are not closed, so we may add )
        if (closed < open) {
            stack.push(")");
            backTrack(open, closed+1, n);
            stack.pop(); // ??????
        }

    }

    public static void main(String[] args) {
        GenerateParentheses ge = new GenerateParentheses();
        System.out.println(ge.generateParentheses(3));
    }
}

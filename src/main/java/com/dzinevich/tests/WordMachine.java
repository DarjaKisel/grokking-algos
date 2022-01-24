package com.dzinevich.tests;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.Consumer;

public class WordMachine {

    public static Consumer<Stack<Integer>> dupFn = ((stack) -> {
        if (stack.empty()) {
            throw new RuntimeException();
        }
        Integer pop = stack.pop();
        stack.push(pop);
        stack.push(pop);
    });

    public static Consumer<Stack<Integer>> popFn = ((stack) -> {
        if (stack.empty()) {
            throw new RuntimeException();
        }
        stack.pop();
    });

    public static Consumer<Stack<Integer>> sumFn = ((stack) -> {
        if (stack.empty()) {
            throw new RuntimeException();
        }
        Integer one = stack.pop();
        Integer two = stack.pop();
        stack.push(one + two);
    });

    public static Consumer<Stack<Integer>> subFn = ((stack) -> {
        if (stack.empty()) {
            throw new RuntimeException();
        }
        Integer one = stack.pop();
        Integer two = stack.pop();
        stack.push(one - two);
    });

    enum Operation{
        DUP("DUP", st -> dupFn.accept(st)),
        POP("POP", st -> popFn.accept(st)),
        SUM("+", st -> sumFn.accept(st)),
        SUB("-", st -> subFn.accept(st));

        Operation(String val, Consumer<Stack<Integer>> fn) {
            this.val = val;
            this.fn = fn;
        }

        public String val;
        public Consumer<Stack<Integer>> fn;
    }

    public int solution(String S) {

        Stack<Integer> stack = new Stack<Integer>();

        String[] operations = S.split(" ");
        for (String s : operations) {
            if (isNumeric(s)) {
                int value = Integer.parseInt(s);
                if (isValidNumeric(value)) {
                    stack.push(value);
                } else {
                    return -1;
                }
            } else if (isOperation(s)) {
                try {
                    Operation op = getOperation(s);
                    op.fn.accept(stack);
                } catch (RuntimeException e) {
                    return -1;
                }
            }
        }

        if (!stack.empty()) {
            return stack.pop();
        } else {
            return -1;
        }
    }

    public boolean isOperation(String s) {
        for (Operation o : Operation.values()) {
            if (o.val.equals(s)) {
                return true;
            }
        }

        return false;
    }

    public Operation getOperation(String s) {
        return Arrays.stream(Operation.values())
                .filter(op -> op.val.equals(s))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    public boolean isValidNumeric(Integer integer) {
        return integer >= 0 && integer <= (2 ^ 20 - 1);
    }


    public static void main(String[] args) {
        System.out.println(new WordMachine().solution("4 5 6 - 7 +") + " : should be 8");
        System.out.println(new WordMachine().solution("13 DUP 4 POP 5 DUP + DUP + -") + " : should be 7");
        System.out.println(new WordMachine().solution("5 6 + -") + " : should be -1");
        System.out.println(new WordMachine().solution("3 DUP 5 - -") + " : should be -1");
        System.out.println(new WordMachine().solution("1048575 DUP +") + " : should be -1");
    }

}

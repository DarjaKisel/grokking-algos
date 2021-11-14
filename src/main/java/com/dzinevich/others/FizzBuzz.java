package com.dzinevich.others;

import java.util.stream.IntStream;

/**
 * You have to print integers from 1 up to N inclusive.
 * But replace integer by Fizz if it divides by 3.
 * And replace it by Buzz if it divides by 5.
 * And replace by FizzBuzz if it both divides by 3 and by 5.
 */
public class FizzBuzz {

    public static void main(String[] args) {
        doFizzBuzzMagic(31);
    }

    private static void doFizzBuzzMagic(int number) {
        IntStream.rangeClosed(0, number)
                .mapToObj(FizzBuzz::getNrOrFizzBuzz)
                .forEach(System.out::println);
    }

    private static String getNrOrFizzBuzz(int number) {
        var result = Integer.toString(number);
        if (number % 3 == 0 && number % 5 == 0) {
            result = "FizzBuzz";
        } else if (number % 3 == 0) {
            result = "Fizz";
        } else if (number % 5 == 0) {
            result = "Buzz";
        }

        return result;
    }
}

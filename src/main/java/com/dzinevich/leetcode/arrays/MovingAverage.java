package com.dzinevich.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

public class MovingAverage {

    int size;
    int pointer;
    List<Integer> nums = new ArrayList<>();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        nums.add(val);

        if (nums.size() <= size) {
            pointer = 0;
        } else {
            pointer = nums.size() - size;
        }

        double sum = 0.0;

        for (int i = pointer; i < nums.size(); i++) {
            sum += nums.get(i);
        }

        if (size > nums.size()) {
            return sum / nums.size();
        } else {
            return sum / size;
        }
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(1) + " : ex 1.0");
        System.out.println(ma.next(10) + " : ex 5.5");
        System.out.println(ma.next(3) + " : ex 4.66667");
        System.out.println(ma.next(5) + " : ex 6.0");
    }
}

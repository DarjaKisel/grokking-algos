package com.dzinevich.grokking_algos_book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Part4_Recursive {

    /*
     * 4.1 - write a recursive sum
     */
    public static int sum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] newArr = new int[nums.length - 1];
        System.arraycopy(nums, 1, newArr, 0, nums.length - 1);

        return nums[0] + sum(newArr);
    }

    /*
     * 4.2 - write a fun to count nr of elements in array
     */
    public static int count(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int[] newArr = new int[nums.length - 1];
        System.arraycopy(nums, 1, newArr, 0, nums.length - 1);

        return 1 + count(newArr);
    }

    /*
     * 4.3 - find a biggest num in array
     */
    public static int max(int[] nums) {
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] newArr = new int[nums.length - 1];
        System.arraycopy(nums, 1, newArr, 0, nums.length - 1);

        int max = max(newArr);

        return Math.max(nums[0], max);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(" sum " + sum(new int[]{1, 2, 2, 2, 5}) + " = 12");
        System.out.println(" count " + count(new int[]{1, 2, 2, 2, 1, 0, 5}) + " = 7");
        System.out.println(" max " + max(new int[]{1, 2, 2, 2, 1, 0, 5}) + " = 5");
        System.out.println(" max " + max(new int[]{1, -2, -2, -2, 1, 0, -5}) + " = 1");

        findMultipliers();
    }

    public static void findMultipliers() throws IOException {
        List<String> readAllLines = Files.readAllLines(Paths.get("example.txt")); //replace with abs path

        List<List<Integer>> resultingList = new ArrayList<>(readAllLines.size());

        Path resultFile = Files.createFile(Path.of("result.txt")); //replace with abs path

        for (String line : readAllLines) {
            List<Integer> nums = Arrays.stream(line.split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            Integer limit = nums.get(nums.size() - 1);
            nums.remove(nums.size() - 1);

            resultingList.add(findMultipliers(nums, limit));
        }

        resultingList.stream()
                .map(integers -> integers.stream()
                        .sorted()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" ")))
                .sorted(Comparator.comparing(String::length))
                .forEach(str -> {
                    System.out.println(str);
                    try {
                        Files.writeString(resultFile, str, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static List<Integer> findMultipliers(List<Integer> nums, int limit) {
        List<Integer> result = new ArrayList<>(nums.size());

        nums.forEach(n -> {
            int count = 1;
            while (n * count < limit) {
                result.add(n * count);
                count++;
            }
        });

        return result;
    }
}

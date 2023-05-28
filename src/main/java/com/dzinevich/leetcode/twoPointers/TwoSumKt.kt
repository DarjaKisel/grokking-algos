package com.dzinevich.leetcode.twoPointers

class TwoSumKt {
    fun twoSumWithSort(nums: IntArray, target: Int): IntArray {
        nums.sort()
        var left = 0
        var right = nums.size - 1
        var sum = nums[left] + nums[right]

        while (left < right && sum != target) {
            if (left >= 0 && sum < target) {
                left++
                sum = nums[left] + nums[right]
            }

            if (right < nums.size && right > left && sum > target) {
                right--
                sum = nums[left] + nums[right]
            }
        }

        return intArrayOf(left, right)
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (left in nums) {
            val valueToAddUpToTarget = target - left

            for (right in 1 until nums.size) {
                if (nums[right] == valueToAddUpToTarget) {
                    return intArrayOf(nums.indexOf(left), right)
                }
            }
        }
        return intArrayOf()
    }

    fun twoSumWithHashMap(nums: IntArray, target: Int): IntArray {
        val complementToIdxMap = hashMapOf<Int, Int>()

        for (i in nums.indices) {
            val valueToAddUpToTarget = target - nums[i]
            if (complementToIdxMap[valueToAddUpToTarget] != null) {
                return intArrayOf(complementToIdxMap[valueToAddUpToTarget]!!, i)
            } else {
                complementToIdxMap[nums[i]] = i
            }
        }

        return intArrayOf()
    }
}

fun main() {
//    println(TwoSumKt().twoSumWithSort(intArrayOf(2, 7, 11, 15), 9).contentToString())
//    println(TwoSumKt().twoSum(intArrayOf(1, 2, 11, 7, 15), 9).contentToString())
    println(TwoSumKt().twoSumWithHashMap(intArrayOf(1, 2, 11, 7, 15), 9).contentToString())
    println("^ Should be [1, 3]")
}
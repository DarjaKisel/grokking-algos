package com.dzinevich.leetcode.fastAndSlowPointers;

import java.util.ArrayList;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 */
public class PalindromeLinkedListArraySolution {

    /**
     * ARRAY SOLUTION
     */
    private static boolean isPalindrome(ListNode listNode) {
        ListNode currentNode = new ListNode();
        currentNode.next = listNode;

        ArrayList<Integer> arrayList = new ArrayList<>();

        while (currentNode.next != null) {
            arrayList.add(currentNode.next.val);
            currentNode.next = currentNode.next.next;
        }

        //now we can do an array algorithm to check if it's a palindrome or not

        int left = 0;
        int right = arrayList.size() - 1;

        while (left <= right) {
            if (arrayList.get(left) != arrayList.get(right)){
                return false;
            } else {
                left++;
                right--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("IS IT A PALINDROME? -- " + isPalindrome(
                new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(3, new ListNode(2, new ListNode(1)))))))
        );
    }
}

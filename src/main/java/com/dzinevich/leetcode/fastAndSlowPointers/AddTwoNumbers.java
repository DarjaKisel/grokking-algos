package com.dzinevich.leetcode.fastAndSlowPointers;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order,
 * and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * example 345 + 5679
 * will be 5->4->3 + 9->7->6->5
 * result will be also reversed
 */
public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode current = new ListNode();
        ListNode dummy = current;

        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            // sum and compute a new val
            int sum = val1 + val2 + carry;
            // what if it's bigger than 9? - then it has a carry
            carry = sum / 10;
            sum = sum % 10;

            current.next = new ListNode(sum);

            // update all pointers
            current = current.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        // return next because first one was just a dummy
        return dummy.next;
    }

    public static void main(String[] args) {
        // 7 + 8 = 15 (will be 5 -> 1)
        ListNode result = addTwoNumbers(
                new ListNode(7),
                new ListNode(8));

        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }

        System.out.println();

        // 345 + 5679 = 6024 (will be 4 -> 2 -> 0 -> 6)
        ListNode result2 = repeatTheSolution(
                new ListNode(5, new ListNode(4, new ListNode(3))),
                new ListNode(9, new ListNode(7, new ListNode(6, new ListNode(5)))));

        while (result2 != null) {
            System.out.print(result2.val);
            result2 = result2.next;
        }
    }

    public static ListNode repeatTheSolution(ListNode l1, ListNode l2) {
        ListNode current = new ListNode();
        ListNode dummy = current;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            int sum = val1 + val2 + carry;

            carry = sum / 10;
            sum = sum % 10;

            current.next = new ListNode(sum);

            current = current.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        // we need next because the first one was empty
        return dummy.next;
    }
}

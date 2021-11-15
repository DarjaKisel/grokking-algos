package com.dzinevich.educative._03_fastAndSlowPointers;

/**
 * Given the head of a LinkedList with a cycle, find the length of the cycle.
 */
public class LinkedListCycleLength {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycleLength.findCycleLength(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycleLength.findCycleLength(head));
    }

    /**
     * O(N) time complexity and O(1) space complexity.
     */
    private static int findCycleLength(ListNode node) {
        var slow = node;
        var fast = node;

        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return calculateNodeLength(slow);
            }
        }

        return 0;
    }

    /**
     * O(N) time complexity and O(1) space complexity.
     */
    private static int calculateNodeLength(ListNode node) {
        var start = node;
        int length = 0;

        do {
            start = start.next;
            length++;
        } while (start != node);

        return length;
    }
}

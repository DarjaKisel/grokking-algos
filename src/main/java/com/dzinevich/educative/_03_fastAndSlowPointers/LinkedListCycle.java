package com.dzinevich.educative._03_fastAndSlowPointers;

/**
 * Given the <b>head of a . S i n g l y .. L i n k e d L i s t</b>,
 * write a function to determine if the LinkedList has a cycle in it or not.
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        //           ^_____________/
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));
    }

    /**
     * The algorithm runs in constant space O(1).
     * If there's a cycle, the fast pointer will meet the slow pointer in the same loop.
     * Therefore, the time complexity is O(N) where ‘N’ is the total number of nodes.
     */
    private static boolean hasCycle(ListNode node) {
        var slow = node;
        var fast = node;

        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; //found a cycle
            }
        }

        return false;
    }

}

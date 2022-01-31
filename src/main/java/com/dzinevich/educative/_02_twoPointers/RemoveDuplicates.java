package com.dzinevich.educative._02_twoPointers;

/**
 * Given an array of sorted numbers, remove all duplicates from it
 */
public class RemoveDuplicates {

    // time complexity is O(N) where N - number of elements in the array
    public static int remove(int[] arr) {
        int uniquePositionAndCounter = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[uniquePositionAndCounter - 1] != arr[i]) {
                arr[uniquePositionAndCounter] = arr[i];
                uniquePositionAndCounter++;
            }
        }
        return uniquePositionAndCounter;
    }

//    public static void main(String[] args) {
//        int[] arr1 = new int[]{2, 3, 3, 3, 6, 9, 9};
//        System.out.println(remove(arr1) + " Should be 4");
//
//        int[] arr2 = new int[]{2, 2, 2, 11};
//        System.out.println(remove(arr2) + " Should be 2");
//    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * leetcode 83 - remove duplicates from sorted list
     *
     * Time - O(n), Memory - O(1)
     * even with nested loops time is O(n) because for example:
     * 1 > 1 > 1 > 3 > 3
     * external loop is at idx 1,
     * internal is at 2 and 3
     * then internal removes 2 and 3
     * internal jumps to 4
     * .. so we visit each node only once even though there're two loops
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            // there could be [1,1,1,2], curr is at first element,
            // we should move pointer to next, then next.next, and dont change the curr until we find all duplicates
            // that's why we use 'while' and not 'if'
            while (curr.next != null && curr.val == curr.next.val) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }

        return head; // curr is at the end of the list, don't return it :)
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        printListNode(head1);

        System.out.println();
        deleteDuplicates(head1);

        printListNode(head1);
        // // //
        System.out.println("\n------------------");
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(2)));
        printListNode(head);

        System.out.println();
        deleteDuplicates(head);

        printListNode(head);
        // // //
        System.out.println("\n------------------");
        ListNode head2 = new ListNode(1, new ListNode(1, new ListNode(1)));
        printListNode(head2);

        System.out.println();
        deleteDuplicates(head2);

        printListNode(head2);
    }

    private static void printListNode(ListNode head) {
        ListNode print = head;
        while (print != null) {
            System.out.print(print.val + " ");
            print = print.next;
        }
    }
}

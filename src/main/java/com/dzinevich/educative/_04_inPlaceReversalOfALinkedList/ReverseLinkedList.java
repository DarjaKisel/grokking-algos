package com.dzinevich.educative._04_inPlaceReversalOfALinkedList;

public class ReverseLinkedList {

    /**
     * Think of it as we reverse arrows!
     * We literally just reverse an arrow node by node.
     *
     * We define 3 additional variables to store prev, curr, and next!
     */
    private static ListNode reverse(ListNode node) {
        // we have null -> 2 -> 3 -> 4
        ListNode current = node; // current is 2
        ListNode previous = null; // previous is null
        ListNode next = null; // just a storage, we need to know which one to process next

        while (current != null) {
            next = current.next; // before any manipulations store (3) as next to visit
            current.next = previous; // REVERT ARROW HERE!! .. null <- 2  3 -> 4
            previous = current; // we processed (2), it becomes previous
            current = next; // move on to the next one
        }

        // after the loop current will be pointing to 'null' and 'previous' will become the new head
        return previous;
    }

    private static void printNodes(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.value + " ");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        System.out.print("Nodes of the LinkedList are: ");
        printNodes(head);
        ListNode result = ReverseLinkedList.reverse(head);
        System.out.print("Nodes of the reversed LinkedList are: ");
        printNodes(result);

    }
}

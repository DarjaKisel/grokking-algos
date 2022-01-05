package com.dzinevich.leetcode.fastAndSlowPointers;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Input: head = [1], n = 1
 * Output: []
 */
public class RemoveNthNodeFromEndOfList {

    private static class Node{
        int val;
        Node next;
        Node(){}
        Node(int val) {
            this.val = val;
        }
        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    //we'll have two pointers, left at the beginning, right at exactly left + n position
    //so when the right one reach the end, the left one is exactly at the end - n which we have to delete :)
    //
    // let's say we have 1-2-3-4-5 and n=2 (should remove 4)
    //
    // (1)->(2)->(3)->(4)->(5)->null
    //  |         |             |
    //  L<-  2  ->R             |
    //      L<-  2  ->R         |
    //           L<-  2  ->R    |
    //                L<-  2  ->R
    //
    //ADD A DUMMY NODE IN FRONT!!!
    //(0)->(1)->(2)->(3)->(4)->(5)->null
    //
    public static Node removeNthFromEnd(Node head, int n) {

        Node rightPosition = head; // first set the pointer at HEAD

        Node dummy = new Node(-1, head); // then insert the dummy in front of HEAD!
        Node leftPosition = dummy;

        for(int i = 0; i < n; i++) {
            rightPosition = rightPosition.next;
        }

        while (rightPosition != null) {
            leftPosition = leftPosition.next;
            rightPosition = rightPosition.next;
        }

        leftPosition.next = leftPosition.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5)))));
        removeNthFromEnd(head, 2);

        var curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }
}
